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
 * @version 1.5
 */
package com.example.slbappcomm.xclchart.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.renderer.XChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.plot.PlotAttrInfo;
import com.example.slbappcomm.xclchart.renderer.plot.PlotAttrInfoRender;

import java.util.List;


/**
 * @ClassName RoundBarChart
 * @Description  弧线比较图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class ArcLineChart  extends XChart {
	
	private  static final  String TAG = "ArcLineChart";

	
	//初始偏移角度
	private static final int OFFSET_ANGLE = 270;	
	 
	//开放标签和线画笔让用户设置
	private Paint mPaintLabel = null;	
	private Paint mPaintLine = null;
			
	//数据源
	private List<ArcLineData> mDataset;
	
	//柱形间距所占比例
	private float mBarInnerMargin = 0.5f;
	//标签偏移
	private float mLabelOffsetX = 0.0f;	
	
	//内环填充颜色
	private Paint mPaintFill = null;	
	
	//半径
	private float mRadius=0.0f;	
	//内环半径	
	private float mInnerRaius = 0.6f;
	
	//附加信息类
	private PlotAttrInfoRender plotAttrInfoRender = null;
		

	public ArcLineChart()
	{	
		int fillColor = Color.BLACK;
		if( null != plotArea)
			 fillColor =  this.plotArea.getBackgroundPaint().getColor();		
		
		if(null == mPaintFill)
		{
			mPaintFill = new Paint();
			mPaintFill.setColor(fillColor); 
			mPaintFill.setAntiAlias(true);
		}
		
		if(null == plotAttrInfoRender)plotAttrInfoRender = new PlotAttrInfoRender();
		
		//初始化图例
		if(null != plotLegend)
		{
			plotLegend.show();
			plotLegend.setType(XEnum.LegendType.ROW);
			plotLegend.setHorizontalAlign(XEnum.HorizontalAlign.CENTER);
			plotLegend.setVerticalAlign(XEnum.VerticalAlign.BOTTOM);
			plotLegend.showBox();
			plotLegend.hideBackground();
		}
		
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.ARCLINE;
	}
	
	@Override
	protected void calcPlotRange()
	{
		super.calcPlotRange();		
		this.mRadius = Math.min( div(this.plotArea.getWidth() ,2f) , 
				 				 div(this.plotArea.getHeight(),2f) );	
	}
	
	/**
	 * 返回半径
	 * @return 半径
	 */
	public float getRadius()
	{
		return mRadius;
	}
	
	
	/**
	 * 设置标签向左偏移位置多长
	 * @param offset 横移多少位
	 */
	public void setLabelOffsetX(float offset)
	{
		mLabelOffsetX = offset;
	}

		
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getLabelPaint()
	{
		if(null == mPaintLabel)
		{
			mPaintLabel = new Paint();
			mPaintLabel.setColor(Color.BLACK);
			mPaintLabel.setTextSize(18);
			mPaintLabel.setAntiAlias(true);
			mPaintLabel.setTextAlign(Align.RIGHT);
		}
		return mPaintLabel;
	}
	
	/**
	 * 开放画网线的画笔
	 * @return 画笔
	 */
	public Paint getLinePaint()
	{
		if(null == mPaintLine)
		{
			mPaintLine = new Paint();
			mPaintLine.setColor(Color.rgb(180, 205, 230));
			mPaintLine.setAntiAlias(true);
			//mPaintLine.setStyle(Style.STROKE);
			mPaintLine.setStrokeWidth(3);	
			mPaintLine.setStyle(Style.FILL);
		}		
		return mPaintLine;
	}
	
	/**
	 * 环内部填充画笔
	 * @return 画笔
	 */
	public Paint getInnerPaint()
	{
		return mPaintFill;
	}
		
	
	
	/**
	 * 设置图表的数据源
	 * @params piedata 来源数据集
	 */
	public void setDataSource( List<ArcLineData> bardata)
	{		
		this.mDataset = bardata; 
	}
	
	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	public List<ArcLineData> getDataSource()
	{
		return mDataset;
	}
	
	
	/**
	 * 附加信息绘制处理类
	 * @return 信息基类
	 */
	public PlotAttrInfo getPlotAttrInfo()
	{
		return plotAttrInfoRender; 
	}
	
	
	
	/**
	 * 设置柱形间空白所占的百分比
	 * @param percentage 百分比
	 */
	public boolean setBarInnerMargin(float percentage)
	{		
		if(Double.compare(percentage, 0d) == -1)
		{
			Log.e(TAG, "此比例不能为负数噢!");
			return false;
		}if( Double.compare(percentage, 0.9d) ==  1 
				|| Double.compare(percentage, 0.9d) ==  0 ){  
			Log.e(TAG, "此比例不能大于等于0.9,要给柱形留下点显示空间!");
			return false;
		}else{
			this.mBarInnerMargin = percentage;
		}
		return true;
	}
	
	/**
	 * 得到柱形间空白所占的百分比
	 * @return 百分比
	 */
	public float getInnerMargin()
	{
		return mBarInnerMargin;
	}
	
	/**
	 * 设置环内部填充相对于环所占的比例
	 * @param precentage 环所占比例
	 */
	public void setInnerRaius(float precentage)
	{
		mInnerRaius = precentage;
	}
		
	/**
	 * 检查角度的合法性
	 * @param Angle 角度
	 * @return 是否正常
	 */
	protected boolean validateAngle(float Angle)
	{
		if(Float.compare(Angle, 0.0f) == 0 
				|| Float.compare(Angle, 0.0f) == -1)
		{
			Log.e(TAG, "扇区圆心角小于等于0度. 当前圆心角为:"+ Angle);
			return false;
		}
		return true;
	}
		

	
	private boolean renderCap(Canvas canvas,
								float radius,
								PointF[] arrCapPoint,
								int[] arrCapColor )
	{
		getLinePaint().setColor( Color.RED);
	
		for(int i=0;i<arrCapPoint.length;i++)
		{
			 getLinePaint().setColor( arrCapColor[i]);
			 canvas.drawCircle(arrCapPoint[i].x, arrCapPoint[i].y, 
					 			radius , getLinePaint());  
		}		
		return true;
	}
	
	
	private boolean renderLabels(Canvas canvas,float radius,PointF[] arrPoint)
	{
		int i = 0;
		float currentAngle = 0.0f;		
		float txtHeight = DrawHelper.getInstance().getPaintFontHeight(getLabelPaint()) / 3;
		
		for(ArcLineData cData : mDataset)
		{						
			currentAngle = cData.getSliceAngle();							
			if(!validateAngle(currentAngle)) continue; 
							
			getLabelPaint().setColor(cData.getBarColor());	
			
			//标识
			canvas.drawText(cData.getLabel(),arrPoint[i].x - mLabelOffsetX, 
								arrPoint[i].y + txtHeight,getLabelPaint());     
			
            i++;
		}	
		
		return true;
	}
	

	private boolean  renderPlot(Canvas canvas)
	{
		if(null == mDataset)
		{
 			Log.e(TAG,"数据源为空.");
 			return false;
		}
		//中心点坐标
		float cirX =  plotArea.getCenterX();
	    float cirY = plotArea.getCenterY();		     
        float radius =  getRadius();
        
        try{
        	
       //用于存放当前百分比的圆心角度
		float currentAngle = 0.0f;				
		float offsetAngle = OFFSET_ANGLE;		
		int i = 0;
		
		int dataCount = mDataset.size();
		float barTotalSize = sub(radius , mul(radius  , mInnerRaius ));
		float mulBarSize = div(barTotalSize , dataCount);
		float spaceSize = mul(mulBarSize , mBarInnerMargin);
		float barSize  =  sub(mulBarSize , spaceSize);
						
		//标签
		float labelRadius = 0.0f;
		PointF[] arrLabPoint = new PointF[dataCount];
		
		//箭头
		PointF[] arrCapPoint = new PointF[dataCount];
		int[] arrCapColor = new int[dataCount];
	
		//绘制底盘
		canvas.drawCircle(cirX, cirY, radius, mPaintFill);
		
		//绘制柱形
		for(ArcLineData cData : mDataset)
		{							
			currentAngle = cData.getSliceAngle();							
			if(!validateAngle(currentAngle)) continue; 
			
			getLinePaint().setColor(cData.getBarColor());	
			DrawHelper.getInstance().drawPercent(canvas,
					getLinePaint(), cirX, cirY, radius, offsetAngle, currentAngle, true);
			
			 //箭头
	    	PointF point = MathHelper.getInstance().calcArcEndPointXY(cirX,cirY,
	    							radius - barSize/2,
	    							add(offsetAngle , currentAngle)); 	
	    	arrCapPoint[i] = new PointF(point.x,point.y);
	    	arrCapColor[i] = cData.getBarColor();
	     	
	    	//标签
			labelRadius = radius  - barSize/2;							    	
	    	point = MathHelper.getInstance().calcArcEndPointXY(cirX,cirY,
					labelRadius,
					add(offsetAngle , 0)); 		    		    	
	    	arrLabPoint[i] = new PointF(point.x,point.y);
	    	
	    	//底
			canvas.drawCircle(cirX, cirY, radius  - barSize, mPaintFill);
                       
            radius = sub(radius , mulBarSize) ;
            i++;
		}		
		
		renderCap(canvas,barSize * 0.8f,arrCapPoint,arrCapColor);	
				
		//绘制Label		
		renderLabels(canvas,radius,arrLabPoint);	
		
		//图KEY
		plotLegend.renderRoundBarKey(canvas,this.mDataset);
		
		arrLabPoint = null;
		arrCapPoint = null;
		arrCapColor = null;					
	 }catch( Exception e){
		 Log.e(TAG,"error:"+e.toString());
		 return false;
	 }
		return true;
	}
	
	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{	
		try {
			super.postRender(canvas);			
			calcPlotRange();	
			
			//绘制图表
			if(renderPlot(canvas))
			{			
				//绘制附加信息
				 plotAttrInfoRender.renderAttrInfo(canvas, 
						 plotArea.getCenterX(), plotArea.getCenterY(), this.getRadius());
				 
				//绘制标题
				renderTitle(canvas);
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public boolean render(Canvas canvas) throws Exception {
		// TODO Auto-generated method stubcalcPlotRange
		try {
				if (null == canvas)
						return false;
				
				if(getPanModeStatus())
				{											
					canvas.save();
					//设置原点位置					
					switch(this.getPlotPanMode())
					{
					case HORIZONTAL:
						canvas.translate(mTranslateXY[0],0);		
						break;
					case VERTICAL:
						canvas.translate(0,mTranslateXY[1]);		
						break;
					default:
						canvas.translate(mTranslateXY[0],mTranslateXY[1]);
						break;
					}
					
					//绘制图表
					super.render(canvas);
						
					//还原								
					canvas.restore();			
				}else{
					//绘制图表
					super.render(canvas);
				}
						
				return true;				
		} catch (Exception e) {
			throw e;
		}
	}
	

}
