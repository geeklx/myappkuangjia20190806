/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 3.2
 */
package com.example.slbappcomm.xclchart.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;


import com.example.slbappcomm.xclchart.common.PointHelper;
import com.example.slbappcomm.xclchart.renderer.EventChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FunnelChart
 * @Description  漏斗图另一种风格，
 *      很久没画图了，在知乎上看了一个图(http://www.zhihu.com/question/31894603)，匆匆搞的.
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class FunnelChart2 extends EventChart {
	    //private  static final String TAG = "FunnelChart2";
		
		private Paint mPaint = null;
		private Paint mPaintLabel = null;		
		private List<Funnel2Data> mDataSet;
		private int mBgColor = Color.WHITE;


	public FunnelChart2() {

	}

	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.FUNNEL;
	}

	/**
	 * 返回图的数据源
	 * @return 数据源
	 */
	public List<Funnel2Data> getDataSource() {
		return mDataSet;
	}
	
	/**
	 * 设置数据源
	 * @param dataSet 数据集
	 */
	public void setDataSource(List<Funnel2Data> dataSet)
	{
		mDataSet = dataSet;
	}
	
	public Paint getPaint()
	{
		if(null == mPaint)mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		return mPaint;
	}
	
	
	public Paint getPaintLabel()
	{
		if(null == mPaintLabel)
		{
			mPaintLabel = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaintLabel.setColor(Color.BLACK);
		}
		return mPaintLabel;
	}
	
	public void setBgColor(int color)
	{
		mBgColor = color;
	}
	
	protected void renderPlot(Canvas canvas)
	{
		if(mDataSet.size() ==0 )return;
		 		
		 float fHalfTopWidth  =  div(plotArea.getPlotWidth() , 5) ;		
		 float funnelTopWidth  = mul(fHalfTopWidth , 2) ; 		 		 			 
		 float funnelTopStartX =sub( plotArea.getCenterX() , fHalfTopWidth) ;
		 float funnelTopStopX = add( plotArea.getCenterX() , fHalfTopWidth) ;	
		 
		 //打底
		 Path cPath =new Path();	
		 cPath.moveTo(plotArea.getLeft(),plotArea.getBottom());
		 cPath.lineTo(plotArea.getRight(), plotArea.getBottom());
		 cPath.lineTo(funnelTopStopX, plotArea.getTop());
		 cPath.lineTo(funnelTopStartX, plotArea.getTop());				 		 
		 cPath.close();		 
		 getPaint().setStyle(Style.FILL);
		 getPaint().setColor(mDataSet.get(0).getColor());
		 canvas.drawPath(cPath, getPaint());

		 List<PointF> lstTop = new ArrayList<PointF>();
		 List<PointF> lstCenter = new ArrayList<PointF>();
		 List<PointF> lstRight = new ArrayList<PointF>();
		 		
		 int dataSize = mDataSet.size();
		 float fx = funnelTopStartX ; //sub(plotArea.getCenterX() , fHalfTopWidth );
		 float fy = plotArea.getTop() ;
		 
		 // 依chartPercentData 得到上面的线的比例
		 for(int i=0;i<dataSize;i++) 
		 {
			 fx = add(fx,mul( funnelTopWidth , mDataSet.get(i).getPercentData()));
			 lstTop.add(new PointF(fx, fy));
		 }
		 
		 // 中间控制点位置
		 fy = plotArea.getBottom();
		 float fSpaddWidth = div(sub(plotArea.getPlotWidth() , funnelTopWidth ) , 2);	
		 float rx,leftX,rightX,cx;
		 for(int i=0;i<dataSize;i++) 
		 {	 
			 fy = sub(fy, mul(plotArea.getPlotHeight(), mDataSet.get(i).getBaseData())  );
			 rx = mul(fSpaddWidth, sub(1,div(fy,plotArea.getPlotHeight()))); 
			 leftX = plotArea.getLeft() + rx ;
			 rightX = plotArea.getRight() - rx ;					 			 	 
			 cx = leftX + ((rightX - leftX) * mDataSet.get(i).getPercentData() );					 
			 lstCenter.add(new PointF(cx,fy));	
		 }		 
		 
		 // 右边结束位置及标签位置
		 List<Float> lstLabel = new ArrayList<Float>();		 
		 float labelY = 0.f;		 
		 float labelX =  add(funnelTopStopX, div(fSpaddWidth,2) );
		 
		 fy = plotArea.getBottom();		 	 		 
		 for(int i=0;i<dataSize;i++) 
		 {			 
			 fy = sub(fy, mul( plotArea.getPlotHeight() , mDataSet.get(i).getPercentData())) ;	
			 lstRight.add(new PointF(plotArea.getRight(),fy));	 							 
			 if(i == 0){
				lstLabel.add(  sub(plotArea.getBottom() , div( sub( plotArea.getBottom() , fy) ,2)) );					
			}else {					
				labelY = div( sub(fy,lstRight.get(i - 1).y) ,2) ;
				labelY = sub(fy, labelY);
				lstLabel.add(  labelY );
			}			 
		 }	
		 ////////////////////////////	
		 
		 //填充区域
		 Path bezierPath = new Path();
		 for(int i=0;i<lstTop.size();i++)
		 {						 
				if( i + 1 >= lstTop.size() )continue;
				getPaint().setColor(mDataSet.get(i+1).getColor());
				bezierPath.moveTo(lstTop.get(i).x, lstTop.get(i).y);
				PointF ctl2 = PointHelper.percent(lstTop.get(i), 0.7f,lstCenter.get(i), 0.5f);
				bezierPath.quadTo(ctl2.x, ctl2.y,lstCenter.get(i).x, lstCenter.get(i).y);
				
				PointF ctl3 = PointHelper.percent(lstCenter.get(i), 0.1f,lstRight.get(i), 0.9f);
				bezierPath.quadTo(ctl3.x, ctl3.y,lstRight.get(i).x, lstRight.get(i).y);

				bezierPath.lineTo(funnelTopStopX, plotArea.getTop());												
				bezierPath.close();
				canvas.drawPath(bezierPath, getPaint());
				bezierPath.reset();												
		 }
		 
		 // 盖掉多出的区域.(本来有更好的算位置的方法，不小心删了，没心情再搞了)
		 //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		 cPath.reset();
		 cPath.moveTo(plotArea.getRight(),plotArea.getBottom());
		 cPath.lineTo(plotArea.getRight(), plotArea.getTop());
		 cPath.lineTo(funnelTopStopX, plotArea.getTop());
		 cPath.close();
		 getPaint().setColor(mBgColor);
		 canvas.drawPath(cPath,getPaint());		 
		 cPath.reset();
		 
		 cPath.moveTo(plotArea.getLeft(),plotArea.getTop());
		 cPath.lineTo(funnelTopStartX, plotArea.getTop());
		 cPath.lineTo(plotArea.getLeft(), plotArea.getBottom());		 		 
		 cPath.close();
		 canvas.drawPath(cPath,getPaint());
		 //画上标签
		 getPaintLabel();	
		 for(int i=0 ;i<lstLabel.size();i++)
		 {
			 try{					
					canvas.drawText(mDataSet.get(i).getLabel(), labelX,  lstLabel.get(i), mPaintLabel);
				}catch( Exception ex){					
				}
		 }
		 ////////////////////////////
	}
	

	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{	
		try {
			super.postRender(canvas);
			
			//计算主图表区范围
			 calcPlotRange();
			 
			 canvas.drawColor(mBgColor);
			 
			//画Plot Area背景			
			 plotArea.render(canvas);	
			 
			 
			//绘制标题
			renderTitle(canvas);
			
			//绘制图表
			renderPlot(canvas);
			
			//显示焦点
			renderFocusShape(canvas);
		
			//响应提示
			renderToolTip(canvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
	/////////////////////
}
