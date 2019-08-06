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
import android.graphics.RectF;
import android.util.Log;
import android.util.Pair;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.renderer.CirChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;

import java.util.List;

/**
 * @ClassName GaugeChart
 * @Description  刻度盘基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class GaugeChart extends CirChart {
		
	private  static final String TAG = "GaugeChart";
	//刻度步长
	private double mTickSteps = 10d;
	//标签
	private List<String> mLabels = null;
	
	//刻度
	private Paint mPaintTick = null;
	
	//指针
	private float mPointerAngle = 20f;		
	private Paint mPaintPointerLine = null;
	private Paint mPaintPinterCircle  = null;
	
	//分区填充色(比如绿，黄，红),以使区域更清楚
	private Paint mPaintPartitionFill = null;
	//环
	private Paint mPaintDount = null;

	//分区填充数据源(角色(0-180)，颜色)
	private List<Pair> mPartitionDataset = null;
	
	//180度
	private static final int mStartAngle = 180;
	

	public GaugeChart()
	{
		initPaint();
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.GAUGE;
	}
	
	private void initPaint()
	{
		getLabelPaint().setTextSize(18);
		getLabelPaint().setColor(Color.BLUE);
		
		if(null == mPaintTick)
		{
			mPaintTick = new Paint();
			mPaintTick.setStyle(Style.FILL);
			mPaintTick.setAntiAlias(true);	
			mPaintTick.setColor( Color.rgb(50, 149, 222) ); 
			mPaintTick.setStrokeWidth(1);
		}
		
		if(null == mPaintPointerLine)
		{
			mPaintPointerLine = new Paint();
			mPaintPointerLine.setStyle(Style.FILL);
			mPaintPointerLine.setAntiAlias(true);	
			mPaintPointerLine.setColor(Color.BLACK);
			mPaintPointerLine.setStrokeWidth(3);
		}
		
		if(null == mPaintPinterCircle)
		{
			mPaintPinterCircle = new Paint();
			mPaintPinterCircle.setStyle(Style.FILL);
			mPaintPinterCircle.setAntiAlias(true);	
			mPaintPinterCircle.setColor(Color.BLACK);
			mPaintPinterCircle.setStrokeWidth(8);
		}
		
		if(null == mPaintPartitionFill)
		{
			mPaintPartitionFill = new Paint();
			mPaintPartitionFill.setStyle(Style.FILL);
			mPaintPartitionFill.setAntiAlias(true);	
		}
		
		if(null == mPaintDount)
		{
			mPaintDount = new Paint();		
			mPaintDount.setStyle(Style.STROKE);
			mPaintDount.setColor(Color.rgb(50, 149, 222));
			mPaintDount.setAntiAlias(true);
			mPaintDount.setStrokeWidth(2);
		}
		
	}
	
	/**
	 * 开放刻度画笔
	 * @return 画笔
	 */
	public Paint getTickPaint()
	{
		return mPaintTick;
	}
	
	/**
	 * 开放指针画笔
	 * @return 画笔
	 */
	public Paint getPinterCirclePaint()
	{
		return mPaintPinterCircle;
	}
	
	/**
	 * 开放指针底部圆画笔
	 * @return 画笔
	 */
	public Paint getPointerLinePaint()
	{
		return mPaintPointerLine;
	}

	/**
	 * 开放用来绘制颜色分区的画笔
	 * @return 画笔
	 */
	public Paint getPartitionFillPaint()
	{
		return mPaintPartitionFill;
	}
	
	/**
	 * 开放用来绘制外围环的画笔
	 * @return 画笔
	 */
	public Paint getDountPaint()
	{
		
		return mPaintDount;
	}
	
	/**
	 * 绘制饼图中显示所占比例 的扇区
	 * @param paintArc	画笔
	 * @param cirX	x坐标
	 * @param cirY	y坐标
	 * @param radius	半径
	 * @param offsetAngle	偏移角度
	 * @param curretAngle	当前角度
	 * @throws Exception	例外
	 */
	protected void drawPercent(Canvas canvas, Paint paintArc,
			final float cirX,
			final float cirY,
			final float radius,
			final float offsetAngle,
			final float curretAngle) throws Exception
	{
		try{		
			float arcLeft = sub(cirX , radius);  
	        float arcTop  = sub(cirY , radius) ;  
	        float arcRight = add(cirX , radius) ;  
	        float arcBottom = add(cirY , radius) ;  
	        RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);    
			
			//在饼图中显示所占比例  
			canvas.drawArc(arcRF0, offsetAngle, curretAngle, true, paintArc);		
		}catch( Exception e){
			throw e;
		}
	}	
			
	/**
	 * 设置刻度步长
	 * @param step 步长
	 */
	public void setTickSteps(double step)
	{
		mTickSteps = step;
	}		
	
	/**
	 * 设置标签集合,即显示在最外圈的那个文字标签。(标签和步长分开，步长即刻度可以密点，标签可以松点)
	 * @param categories 标签集合
	 */
	public void setCategories(List<String> categories)
	{
		mLabels = categories;
	}
	
	
	/**
	 * 设置分区填充数据集，[角度(0-mStartAngle)，颜色]，用以决定绘制多少个颜色区分，其所显示大小是多少.
	 * @param dataSet 角度,颜色
	 */
	public void setPartition(List<Pair> dataSet)
	{
		mPartitionDataset = dataSet;
	}	
	

	/**
	 * 设置指针指向的角度(0-180).
	 * @param Angle 角度
	 */
	public void setCurrentAngle(float Angle)
	{
		mPointerAngle = Angle;
	}
	
	private void renderLabels(Canvas canvas)
	{		
		if(null == mLabels) return ;
		
		float stepsAngle = Math.round(180/(mLabels.size() - 1 ));
		
		float radius = getRadius();
		//float calcRadius = radius  + radius/10f;				
		// getRadius()
        float calcRadius =  add(radius, div(radius,10f) );
				 
		
		float cirX = plotArea.getCenterX();
		//float cirY = plotArea.getCenterY();
		float cirY = getCirY() ; 
		getLabelPaint().setTextAlign(Align.CENTER);	
		int i = 0;
		
        
		for(String label : mLabels)
		{							
			if(0 == i) //开头
			{			
				canvas.drawText(label,
						cirX - calcRadius, cirY ,this.getLabelPaint());   			
			}else if(i == mLabels.size() -1 ){ //结尾
                canvas.drawText(label,
						cirX + calcRadius, cirY ,this.getLabelPaint());   				
			}else{				
				//计算百分比标签
				MathHelper.getInstance().calcArcEndPointXY(
								cirX, cirY, calcRadius, 180f + i *stepsAngle); 
				//标识
                canvas.drawText(label,
                		MathHelper.getInstance().getPosX(),
                		MathHelper.getInstance().getPosY() ,this.getLabelPaint());
				
			}
			i++;
		}
	}
	
	/**
	 * 绘制刻度
	 */
	private void renderTicks(Canvas canvas)
	{
		//步长角度		
		Double fd = new Double(mTickSteps);
		float stepsAngle = div(180.0f,fd.floatValue());	
		float cirX = plotArea.getCenterX();
		float cirY = getCirY(); //plotArea.getCenterY();
		float tickRadius = mul(getRadius(),0.9f); 
				
		for(int i=0;i<mTickSteps;i++)
		{
			if(0 == i)continue;			
			//float Angle =  (float) (180d + i *stepsAngle) ;					
			float Angle = (float) MathHelper.getInstance().add(180d, i *stepsAngle);
			MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, getRadius(), Angle);
			
			float startX = MathHelper.getInstance().getPosX();
			float startY = MathHelper.getInstance().getPosY();
			MathHelper.getInstance().calcArcEndPointXY(cirX, cirY,tickRadius, Angle);
			
			canvas.drawLine(startX, startY, MathHelper.getInstance().getPosX(),
											MathHelper.getInstance().getPosY(), mPaintTick);
		}
	}	
	
	/**
	 * 绘制指针
	 */
	private void renderPointerLine(Canvas canvas)
	{		
		float currentRadius = mul(getRadius() , 0.9f); 
		float cirX = plotArea.getCenterX();
		float cirY = getCirY(); //plotArea.getCenterY();
						
		if(Float.compare(mPointerAngle, 180f) == 0 
				|| Float.compare(mPointerAngle, 180f) == 1 ) //爆表了 
		{
			 //Log.e(TAG,"爆表了 !!!");
			 canvas.drawLine(cirX, cirY, cirX + currentRadius,cirY , mPaintPointerLine);			 			 					
		}else if(Float.compare(mPointerAngle, 0.0f) == 0 
				|| Float.compare(mPointerAngle, 0.0f) == -1){
			//Log.e(TAG,"负角度???!!!");
			canvas.drawLine(cirX, cirY, cirX - currentRadius,cirY , mPaintPointerLine);
		}else{				
			float calcAngle =  add( mPointerAngle , mStartAngle );									
			MathHelper.getInstance().calcArcEndPointXY(cirX, cirY, currentRadius, calcAngle);
			float endX = MathHelper.getInstance().getPosX();
			float endY = MathHelper.getInstance().getPosY();
			if(Float.compare( endY, cirY ) == 1 ) endY = cirY;
            canvas.drawLine(cirX, cirY, endX,endY, mPaintPointerLine);	          						
		}		
	}
	
	/**
	 * 绘制指针底部的圆
	 */
	private void renderPinterCircle(Canvas canvas)
	{
		float cirX = plotArea.getCenterX();
		float cirY = getCirY(); //plotArea.getCenterY();
		canvas.drawCircle(cirX, cirY, mul(this.getRadius() ,0.05f), mPaintPinterCircle);
	}
	
		
	/**
	 * 绘制内部颜色分区填充
	 * @throws Exception
	 */
	private boolean renderPartitionFill(Canvas canvas) throws Exception
	{		
		 if(null == mPartitionDataset||mPartitionDataset.size() == 0)
	     {
	    	 Log.e(TAG,"数据源为空.");
	    	 return false;
	     }
		
		 float totalAngle = 0.0f;		
		 float newRadius = mul(getRadius() , 0.8f);
		 
		 float cy = getCirY();
						 
	     RectF rect =new RectF();
	     rect.left  = sub(plotArea.getCenterX() , newRadius);
	     rect.top   = sub(cy , newRadius);
	     rect.right = add(plotArea.getCenterX() , newRadius);
	     rect.bottom= add(cy , newRadius);  
	     	     
		 for(Pair pr : mPartitionDataset)
		 {			
			 Float AngleValue = (Float) pr.first;	
			 float total = add(totalAngle , AngleValue);			 
			 
			 if(Float.compare(AngleValue, 0.0f) < 0){
					Log.e(TAG,"负角度???!!!");
			 }else if(Float.compare(total, 180.0f) == 1)   //(totalAngle + AngleValue) > 180)
		     {
		    	 Log.e(TAG,"输入的角度总计大于180度");
		    	 return false;
		     }			 			 
			 mPaintPartitionFill.setColor((Integer) pr.second);				 
			 canvas.drawArc(rect, add(totalAngle,180.0f), AngleValue, true, mPaintPartitionFill);		     
		     totalAngle = add(totalAngle,AngleValue);
		 }
		 rect = null;
		 return false;		 
	}
	
	private float getCirY()
	{		
		float cirY = this.getBottom();
		if(this.isShowBorder())
        {
        	cirY  -= this.getBorderWidth() / 2;
        }  
		
		cirY -=  mul(getRadius() ,0.05f); //底圆		
		return cirY;
	}
	
	@Override
	public float getRadius()
	{
		  //半圆， 宽应当是高的两倍
        float r =  getWidth() / 2.f; 
                                
        if(this.isShowBorder())
        {
        	r -= this.getBorderWidth() ;        	
        }  
                       		
		//找第一和最后一个标签
		if(null != mLabels && mLabels.size() > 0)
		{
			int e = mLabels.size() - 1;		
			float left = DrawHelper.getInstance().getTextWidth(getLabelPaint(), mLabels.get(0) );
			float right = DrawHelper.getInstance().getTextWidth(getLabelPaint(), mLabels.get(e) );
			float spadding = Math.max(left, right);			
			r = sub(r, spadding);						
			r = sub(r, this.getBorderWidth()/2);			
		}	
		
		 r -=  mul(r ,0.05f); //底圆		 
		return r;
	}

	/**
	 * 绘制环
	 * @throws Exception
	 */
	private void renderDount(Canvas canvas) throws Exception
	{		
		 drawPercent(canvas, mPaintDount,plotArea.getCenterX(),getCirY(),//plotArea.getCenterY(),
				 			getRadius(),180, 180);
		 		 
	}
	
	/**
	 * 绘制图
	 */
	protected void renderPlot(Canvas canvas)
	{
		try{				
			 //外环
			 renderDount(canvas);
		     //依角度画好刻度线
			// 计算出坐标点,从圆心到点间画线
			 renderTicks(canvas);
			//画上用于标识分区的扇区
			 renderPartitionFill(canvas) ;
			 //画上外围标签
			 renderLabels(canvas);
			//最后再画指针
			 renderPointerLine(canvas);
			 //画上指针尾部的白色圆心
			 renderPinterCircle(canvas);
							
		}catch( Exception e){
			Log.e(TAG,e.toString());
		}
		
	}

	
	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{
		try {
			super.postRender(canvas);
			
			//绘制图表
			renderPlot(canvas);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
}
