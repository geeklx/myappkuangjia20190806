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
 * @version 1.0
 */
package com.example.slbappcomm.xclchart.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.renderer.XEnum;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoseChart
 * @Description  南丁格尔图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class RoseChart extends PieChart {
	
	private  static final  String TAG="PieChart";
	
	private Paint mPaintInner = null;
	private boolean mShowInner = true;
	
	private int mIntervalAngle = 0;
	
	private Paint mPaintBg = null;
	private boolean mShowBgLines = false;
	private boolean mShowBgCircle = false;
	private Map<Float,Integer> mListBgSeg = null; 
	private int mShowBgLineColor = Color.BLACK;
	
	private boolean mShowOuterLabels = false ;
	private int mBgLines = 0;

	public RoseChart() {
		// TODO Auto-generated constructor stub
			
		initChart();
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.ROSE;
	}
	
	
	private void initChart()
	{														
		//白色标签
		if(null != getLabelPaint())
		{
			getLabelPaint().setColor(Color.WHITE);
			getLabelPaint().setTextSize(22);
			getLabelPaint().setTextAlign(Align.CENTER);	
		}
	}
	
	/**
	 * 开放内部背景画笔
	 * @return 画笔
	 */
	public Paint getInnerPaint()
	{		
		//深色内环
		if(null == mPaintInner)
		{
			mPaintInner = new Paint();
			//mPaintInner.setColor(Color.DKGRAY);
			mPaintInner.setColor(Color.rgb(68, 68, 68));
			mPaintInner.setStyle(Style.FILL);		
			mPaintInner.setAntiAlias(true);		
		}
				
		return mPaintInner;
	}
	
	/**
	 * 设置各扇区间隔角度
	 * @param angle	角度
	 */
	public void setIntervalAngle(int angle)
	{
		mIntervalAngle = angle;
	}
	
	/**
	 * 显示背景环
	 */
	public void showInner()
	{
		mShowInner = true;
	}
	
	/**
	 * 隐藏背景环
	 */
	public void hideInner()
	{
		mShowInner = false;
	}
	
	/**
	 * 标签显示在外环上
	 */
	public void showOuterLabels()
	{
		mShowOuterLabels = true;
	}
	
	/**
	 * 标签不显示在外环
	 */
	public void hideOuterLabels()
	{
		mShowOuterLabels = false;
	}
	
	/**
	 * 用于绘制背景线，圈的画笔
	 * @return 画笔
	 */
	public Paint getBgPaint()
	{
		 if(mPaintBg == null)
		 {
			 mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);	
			 mPaintBg.setStyle(Style.STROKE);
			 mPaintBg.setAntiAlias(true);
		 }
		return mPaintBg;
	}
	
	/**
	 * 显示背景线，并指定线的颜色
	 * @param color 颜色
	 */
	public void showBgLines(int color)
	{
		mShowBgLines = true;
		mShowBgLineColor = color;
		
	}
	
	/**
	 * 依map传入的比例来设定显示几个圈,并指定各自的颜色
	 * 
	 * @param bgSeg  比例,线颜色
	 */
	public void showBgCircle(Map<Float,Integer>  bgSeg)
	{
		mShowBgCircle = true;
		mListBgSeg = bgSeg;
	}
	
	/**
	 * 显示背景直线
	 */
	public void hideBgLines()
	{
		mShowBgLines = false;
	}
	
	/**
	 * 不显示背景直线
	 */
	public void hideBgCircle()
	{
		mShowBgCircle = true;
	}	
	
	/**
	 * 指定要显示的线个数
	 * @param count 总个数
	 */
	public void setBgLines(int count)
	{
		mBgLines = count;
	}
	
	@Override 
	protected boolean validateParams()
	{
		return true;
	}
	
	/**
	 * 绘制背景直线与圈
	 * @param canvas 画布
	 */
	private void drawBGCircle(Canvas canvas)
	{
		if(!mShowBgCircle)return;
		if(mListBgSeg == null)return ;
	
		float radius = getRadius();		
		for(Map.Entry<Float,Integer> entry:mListBgSeg.entrySet()){    
			 float newRadius =  mul(radius, entry.getKey());
			 if(Float.compare(newRadius, 0.0f) == 0 
					 || Float.compare(newRadius, 0.0f) == -1)
			 {
				 continue;
			 }
			
			 getBgPaint().setColor(entry.getValue());
			 canvas.drawCircle(plotArea.getCenterX(), plotArea.getCenterY(), 
					 		   newRadius, getBgPaint());
		}   
	}
	
	private void drawBGLines(Canvas canvas)
	{
		if(!mShowBgLines)return;
		if(0 == mBgLines)return ;
		
		int totalAngle =  360 - mIntervalAngle * mBgLines;
 		
 		float currAngle = totalAngle / mBgLines;
 		float radius = getRadius();
 		float offsetAngle = mOffsetAngle;
 		
		for(int i=0;i < mBgLines;i++)
		{			
			   PointF pointbg = MathHelper.getInstance().calcArcEndPointXY(
					   plotArea.getCenterX(), plotArea.getCenterY(),
					   radius, offsetAngle+ mIntervalAngle + currAngle/2); 
         
            	getBgPaint().setColor(mShowBgLineColor);
            	canvas.drawLine(plotArea.getCenterX(), plotArea.getCenterY(),
            				pointbg.x, pointbg.y, getBgPaint());
	          			   
			   offsetAngle = add(add(offsetAngle,currAngle),mIntervalAngle);			
		}
	}
	
	private float getLabelRadius()
	{
		float labelRadius = 0.f;
		float radius = getRadius();
		if(mShowOuterLabels)
    	{
			labelRadius = radius + DrawHelper.getInstance().getPaintFontHeight(this.getLabelPaint());
    	}else{        		
    		labelRadius = radius - radius/2/2;
    	}
		return labelRadius;
	}
	
	
	/**
	 * 绘制图
	 */
	@Override
	protected boolean renderPlot(Canvas canvas)
	{			 							
			//计算中心点坐标
			float cirX = plotArea.getCenterX();
		    float cirY = plotArea.getCenterY();
	        float radius = getRadius();
	    	        	      	        	       	    
	        float arcAngle = 0.0f;		 		
	 		float newRaidus = 0.0f;		
	 		
	 		//数据源
	 		List<PieData> chartDataSource = this.getDataSource();
	 		if(null == chartDataSource || chartDataSource.size() == 0)
	 		{
	 			Log.e(TAG,"数据源为空.");
	 			return false;
	 		}
	 		
	 		 //内环
			if(mShowInner)canvas.drawCircle(cirX,cirY,radius,getInnerPaint()); 
			
			 //画背景
	        drawBGCircle(canvas);
	        //画背景直线
	        drawBGLines(canvas);
			
			//依参数个数，算出总个要算多少个扇区的角度	 		
	 		int totalAngle =  360 - mIntervalAngle * chartDataSource.size();
	 		
	 		arcAngle = totalAngle / chartDataSource.size();
	 		
			//percentage = 360 / chartDataSource.size();
			//percentage = (float)(Math.round(percentage *100))/100; 		
	 		arcAngle = div(mul(arcAngle,100),100);			
			if(!validateAngle(arcAngle))
			{
				Log.e(TAG,"计算出来的扇区角度小于等于0度,不能绘制.");
	 			return false;
			}						
			float labelRadius = getLabelRadius();
			
	        for(PieData cData : chartDataSource)
			{
	        	geArcPaint().setColor(cData.getSliceColor());	
				
				//将百分比转换为新扇区的半径    
				double p = cData.getPercentage()/ 100;					
				newRaidus = mul(radius,(float) (p));				
				newRaidus = div(mul(newRaidus,100),100);
				
				//newRaidus = (float) (radius * (cData.getPercentage()/ 100));  
	            //newRaidus = (float)(Math.round(newRaidus *100))/100;    
	     		        				
	            //在饼图中显示所占比例   
	            RectF nRF = new RectF(sub(cirX , newRaidus),sub(cirY , newRaidus),
	            					  add(cirX , newRaidus),add(cirY , newRaidus));  
	            canvas.drawArc(nRF, mOffsetAngle + mIntervalAngle, arcAngle, true, geArcPaint());       
					       	            
	            //标识  
	            String label = cData.getLabel();	            
	            if("" != label)
            	{            			            
	            	//计算百分比标签 
		            PointF point = MathHelper.getInstance().calcArcEndPointXY(
		            			cirX, cirY, labelRadius, mOffsetAngle + mIntervalAngle + arcAngle/2); 
		            
            		//请自行在回调函数中处理显示格式
                    DrawHelper.getInstance().drawRotateText( label,
                    		point.x, point.y, cData.getItemLabelRotateAngle(), 
                    		canvas, getLabelPaint());
            	}               
	         
	          //下次的起始角度  
	            mOffsetAngle = add(add(mOffsetAngle,arcAngle),mIntervalAngle);
			}
	        return true;
	}
		

}
