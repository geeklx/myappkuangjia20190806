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
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.IFormatterTextCallBack;
import com.example.slbappcomm.xclchart.renderer.LnChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.line.PlotDot;
import com.example.slbappcomm.xclchart.renderer.line.PlotDotRender;
import com.example.slbappcomm.xclchart.renderer.plot.PlotQuadrant;
import com.example.slbappcomm.xclchart.renderer.plot.PlotQuadrantRender;

import java.util.List;

/**
 * @ClassName BubbleChart
 * @Description  气泡图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class BubbleChart extends LnChart {

	private static  String TAG="BubbleChart";
	
	//数据源
	private List<BubbleData> mDataset;
	
	//分类轴的最大，最小值
	private double mMaxValue = 0d;
	private double mMinValue = 0d;
		
	//用于格式化标签的回调接口
	private IFormatterTextCallBack mLabelFormatter;
	
	//指定气泡半径的最大大小
	private float mBubbleMaxSize = 0.0f;
	private float mBubbleMinSize = 0.0f;
	
	//指定最大气泡大小所表示的实际值，最大气泡大小由 BubbleMaxSize 设置。
	private float mBubbleScaleMax = 0.0f;
	private float mBubbleScaleMin = 0.0f;
	
	private Paint mPaintPoint = null;	
	private PlotDot mPlotDot = new PlotDot();
	
	private Paint mPaintBorderPoint = null;	
	
	//四象限类
	private PlotQuadrantRender mPlotQuadrant = null;

	public BubbleChart()
	{
		initChart();
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.BUBBLE;
	}
	
	private void initChart()
	{
		if(null != mPlotDot)
			mPlotDot.setDotStyle(XEnum.DotStyle.DOT);
		
		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
		
		this.setAxesClosed(true);
	}
	
	@Override
	protected void categoryAxisDefaultSetting()
	{		
		if(null != categoryAxis)
			categoryAxis.setHorizontalTickAlign(Align.CENTER);
	}
	
	@Override
	protected void dataAxisDefaultSetting()
	{		
		if(null != dataAxis)
			dataAxis.setHorizontalTickAlign(Align.LEFT);
	}
	
	
	/**
	 * 指定气泡半径的最大大小
	 * @param maxSize 最大气泡半径(px)
	 */
	public void setBubbleMaxSize(float maxSize)
	{
		mBubbleMaxSize = maxSize;
	}
		
	/**
	 * 指定气泡半径的最小大小
	 * @param minSize 最小气泡半径(px)
	 */
	public void setBubbleMinSize(float minSize)
	{
		mBubbleMinSize = minSize;
	}

	
	/**
	 * 指定最大气泡大小所表示的实际值
	 * @param scaleMax 最大气泡实际值
	 */
	public void setBubbleScaleMax(float scaleMax)
	{
		mBubbleScaleMax = scaleMax;
	}
	
	/**
	 * 指定最小气泡大小所表示的实际值
	 * @param scaleMin  最小气泡实际值
	 */
	public void setBubbleScaleMin(float scaleMin)
	{
		mBubbleScaleMin = scaleMin;
	}				
	
	/**
	 * 分类轴的数据源
	 * @param categories 标签集
	 */
	public void setCategories( List<String> categories)
	{
		if(null != categoryAxis)categoryAxis.setDataBuilding(categories);
	}
	
	/**
	 *  设置数据轴的数据源
	 * @param dataSeries 数据序列
	 */
	public void setDataSource( List<BubbleData> dataSeries)
	{		
		this.mDataset = dataSeries;		
	}	
	
	/**
	 *  显示数据的数据轴最大值
	 * @param value 数据轴最大值
	 */
	public void setCategoryAxisMax( double value)
	{
		mMaxValue = value;
	}	
	
	/**
	 * 设置分类轴最小值
	 * @param value 最小值
	 */
	public void setCategoryAxisMin( double value)
	{
		mMinValue = value;
	}	
	
	/**
	 * 设置标签的显示格式
	 * @param callBack 回调函数
	 */
	public void setDotLabelFormatter(IFormatterTextCallBack callBack) {
		this.mLabelFormatter = callBack;
	}
	
	/**
	 * 返回标签显示格式
	 * 
	 * @params value 传入当前值
	 * @return 显示格式
	 */
	protected String getFormatterDotLabel(String text) {
		String itemLabel = "";
		try {
			itemLabel = mLabelFormatter.textFormatter(text);
		} catch (Exception ex) {
			itemLabel = text;
		}
		return itemLabel;
	}
	

	private float calcRaidus(float scale,float scaleTotalSize,float bubbleRadius)
	{
		return mul(bubbleRadius,div(scale,scaleTotalSize));
	}
	
	
	/**
	 * 返回四象限绘制类
	 * @return
	 */
	public PlotQuadrant getPlotQuadrant()
	{
		if(null == mPlotQuadrant) mPlotQuadrant = new PlotQuadrantRender();
		return mPlotQuadrant;
	}
	
	
	
	/**
	 * 绘制点的画笔
	 * @return
	 */
	public Paint getPointPaint()
	{
		if(null == mPaintPoint)
		{
			mPaintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);			
		}
		return mPaintPoint;
	}
		
	public Paint getPointBorderPaint()
	{
		if(null == mPaintBorderPoint)
		{
			mPaintBorderPoint = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaintBorderPoint.setStyle(Style.STROKE);
			mPaintBorderPoint.setStrokeWidth(2);
		}
		return mPaintBorderPoint;
	}
	
	/**
	 * 绘制象限
	 * @param canvas 画布
	 */
	private void drawQuadrant(Canvas canvas )
	{
		if(!getPlotQuadrant().isShow())return;
		
		Double xValue = getPlotQuadrant().getQuadrantXValue();
	    Double yValue = getPlotQuadrant().getQuadrantYValue();	    
	   
	    float centerX = getLnXValPosition(xValue,mMaxValue,mMinValue);	
	    float centerY = getVPValPosition(yValue);	
	        	    	   
	    mPlotQuadrant.drawQuadrant(canvas, centerX, centerY, 
			   plotArea.getLeft(), plotArea.getPlotTop(), plotArea.getPlotRight(), plotArea.getBottom());	    
	}
			
				
	private void renderPoints(Canvas canvas, BubbleData bd , int dataID)
	{	
		//得到标签对应的值数据集		
		List<PointD> chartValues = bd.getDataSet();
		if(null == chartValues) return ;
															
	    //画出数据集对应的线条	
		float YvaluePos = 0.0f,XvaluePos = 0.0f;

		if(Float.compare(mBubbleScaleMax, mBubbleScaleMin)  == 0  ) 
		{
			Log.e(TAG,"没有指定用于决定气泡大小的最大最小实际数据值。");
			return;
		}
		
		if( Float.compare(mBubbleMaxSize, mBubbleMinSize)  == 0 ) 
		{
			Log.e(TAG,"没有指定气泡本身，最大最小半径。");
			return;
		}
		
		if( Double.compare(mMaxValue, mMinValue) == -1)
		{
			Log.e(TAG,"轴最大值小于最小值.");
			return ;
		}
		
		if( Double.compare(mMaxValue, mMinValue) == 0)
		{
			Log.e(TAG,"轴最大值与最小值相等.");
			return ;
		}
		//double xMM  = MathHelper.getInstance().sub(mMaxValue , mMinValue);
		
		float scale =  mBubbleScaleMax - mBubbleScaleMin;
		float size = mBubbleMaxSize - mBubbleMinSize;
		
		List<Double> lstBubble = bd.getBubble();
		int bubbleSize =  lstBubble.size() ;
		double bubble = 0;
		float curRadius = 0.0f;		
		//汽泡颜色
		getPointPaint().setColor(bd.getColor());	
		//边框颜色
		if(bd.getBorderColor() != -1) getPointBorderPaint().setColor(bd.getBorderColor());	
		
		float itemAngle = bd.getItemLabelRotateAngle();
				
		int count = chartValues.size();
		for(int i=0;i<count;i++)
		{
				PointD entry = chartValues.get(i);
			    XvaluePos = getLnXValPosition(entry.x,mMaxValue,mMinValue);	
			    YvaluePos = getVPValPosition(entry.y);			    
			                
        		if(i >= bubbleSize ) //j
        		{
        			continue;
        		}else{
        			bubble = lstBubble.get(i);//j
        		}
        		
        		curRadius = calcRaidus(scale, size, (float) bubble);
        		
        		if(Float.compare(curRadius, 0.0f) == 0 
        				|| Float.compare(curRadius, 0.0f) == -1) 
        		{
        			//Log.e(TAG,"当前气泡半径小于或等于0。");        	
        			continue;
        		}

        		mPlotDot.setDotRadius( curRadius); 
        		
        		PlotDotRender.getInstance().renderDot(
            			canvas, mPlotDot,XvaluePos,YvaluePos,getPointPaint());
        		            		
            	savePointRecord(dataID,i, XvaluePos + mMoveX,YvaluePos + mMoveY, //childID
            			XvaluePos - curRadius + mMoveX , YvaluePos - curRadius + mMoveY,
            			XvaluePos + curRadius + mMoveX , YvaluePos + curRadius + mMoveY);
        		
            	            	
            	if(bd.getBorderColor() != -1)
            	{
            		canvas.drawCircle(XvaluePos,YvaluePos, curRadius, getPointBorderPaint());
            	}          	
            	
            	
            	//显示批注形状
				drawAnchor(getAnchorDataPoint(),dataID,i,canvas,XvaluePos,YvaluePos,curRadius);
            	
            	if(bd.getLabelVisible())
            	{            			
            		//请自行在回调函数中处理显示格式
                    DrawHelper.getInstance().drawRotateText(getFormatterDotLabel(
							entry.x +","+ entry.y
                            +" : "+ bubble),
                            XvaluePos,YvaluePos, itemAngle, 
                            canvas, bd.getDotLabelPaint()); //lineStopX,lineStopY
            	}                								
		}								
	}
		

	/**
	 * 绘制图
	 */
	private boolean renderPlot(Canvas canvas)
	{
		//检查是否有设置分类轴的最大最小值		
		if(mMaxValue == mMinValue && 0 == mMaxValue)
		{
			Log.e(TAG,"请检查是否有设置分类轴的最大最小值。");
			return false;
		}
		if(null == mDataset)
		{
			Log.e(TAG,"数据源为空.");
			return false;
		}		
		
		//绘制四象限
		drawQuadrant(canvas);
				
		//开始处 X 轴 即分类轴              	
		int size = mDataset.size();
		for(int i=0;i<size;i++)
		{																	
			BubbleData bd =  mDataset.get(i);
			renderPoints( canvas, bd,i);	
		}	
		
		return true;
	}	

	/////////////////////////////////////////////
	@Override
	protected void drawClipPlot(Canvas canvas)
	{
		if(renderPlot(canvas) == true)
		{				
			//画横向定制线
			if(null != mCustomLine)
			{
				mCustomLine.setVerticalPlot(dataAxis, plotArea, getAxisScreenHeight());
				mCustomLine.renderVerticalCustomlinesDataAxis(canvas);		
			}
		}		
	}
	
	@Override
	protected void drawClipLegend(Canvas canvas)
	{
		plotLegend.renderBubbleKey(canvas,mDataset);		
	}
	/////////////////////////////////////////////

}
