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
 * @ClassName ScatterChart
 * @Description  散点图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class ScatterChart extends LnChart {
	
	private static  String TAG="ScatterChart";
	
	//数据源
	private List<ScatterData> mDataset;
	
	//分类轴的最大，最小值
	private double mMaxValue = 0d;
	private double mMinValue = 0d;
		
	//用于格式化标签的回调接口
	private IFormatterTextCallBack mLabelFormatter;
	//用于绘制点的画笔
	private Paint mPaintPoint = null;
	
	//四象限类
	private PlotQuadrantRender mPlotQuadrant = null;

	public ScatterChart()
	{
		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
		
		this.setAxesClosed(true);
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.SCATTER;
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
	public void setDataSource( List<ScatterData> dataSeries)
	{		
		this.mDataset = dataSeries;		
	}	
	
	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	public List<ScatterData> getDataSource()
	{
		return this.mDataset;
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
	 * 用于绘制点的画笔
	 * @return 画笔
	 */
	public Paint getPointPaint()
	{
		if(null == mPaintPoint)
		{
			mPaintPoint = new Paint(Paint.ANTI_ALIAS_FLAG);			
		}
		return mPaintPoint;
	}
	
	/**
	 * 绘制象限
	 * @param canvas 画布
	 */
	private void drawQuadrant(Canvas canvas )
	{
		if(!getPlotQuadrant().isShow())return;
		
		//float axisScreenWidth = getPlotScreenWidth(); 
    	//float axisScreenHeight = getPlotScreenHeight();
		//float axisDataHeight = dataAxis.getAxisRange(); 	
						
		Double xValue = getPlotQuadrant().getQuadrantXValue();
	    Double yValue = getPlotQuadrant().getQuadrantYValue();	    
	    
	    //对应的Y坐标
	    //float centerY = (float) (axisScreenHeight * ( (yValue - dataAxis.getAxisMin() ) / axisDataHeight)) ;  
	                	
    	//对应的X坐标	  	  
	    //float centerX = (float) (axisScreenWidth * ( (xValue - mMinValue ) / (mMaxValue - mMinValue))) ;  
	    
	    float centerX = getLnXValPosition(xValue,mMaxValue,mMinValue);						
	    float centerY = getVPValPosition(yValue);
    
	    mPlotQuadrant.drawQuadrant(canvas, centerX, centerY, 
			   plotArea.getLeft(), plotArea.getPlotTop(), plotArea.getPlotRight(), plotArea.getBottom());	    
	}
			
				
	private void renderPoints(Canvas canvas, ScatterData bd , int dataID)
	{			
		
		if(null == bd)
		{
			Log.w(TAG,"传入的数据序列参数为空.");
			return ;
		}
		
		if( Double.compare(mMaxValue, mMinValue) == -1)
		{
			Log.w(TAG,"轴最大值小于最小值.");
			return ;
		}
		
		if( Double.compare(mMaxValue, mMinValue) == 0)
		{
			Log.w(TAG,"轴最大值与最小值相等.");
			return ;
		}
	
    	float axisDataHeight = dataAxis.getAxisRange(); 		
		if( Float.compare(axisDataHeight, 0.0f) == 0 
				|| Float.compare(axisDataHeight, 0.0f) == -1)
		{
			Log.w(TAG,"数据轴高度小于或等于0.");
			return ;
		}
	
		//得到标签对应的值数据集		
		List<PointD> chartValues = bd.getDataSet();
		if(null == chartValues) return ;
															
	    //画出数据集对应的线条				
		float YvaluePos = 0.0f,XvaluePos =0.0f;
		float itemAngle = bd.getItemLabelRotateAngle();		
		PlotDot dot = bd.getPlotDot();
		float radius = dot.getDotRadius();
								
		int count = chartValues.size();
		for(int i=0;i<count;i++)
		{
			    PointD entry = chartValues.get(i);
			    XvaluePos = getLnXValPosition(entry.x,mMaxValue,mMinValue);						
			    YvaluePos = getVPValPosition(entry.y);
			   			  			                	            	           	       
            	if(!dot.getDotStyle().equals(XEnum.DotStyle.HIDE))
             	{          		            		
            		getPointPaint().setColor(dot.getColor());
            		getPointPaint().setAlpha(dot.getAlpha());
            		
            		PlotDotRender.getInstance().renderDot(
	            			canvas, dot,XvaluePos,YvaluePos,getPointPaint());
            		            		
	            	savePointRecord(dataID,i, XvaluePos + mMoveX,YvaluePos + mMoveY,
	            			XvaluePos - radius + mMoveX , YvaluePos - radius + mMoveY,
	            			XvaluePos + radius + mMoveX , YvaluePos + radius + mMoveY);	            
             	}
    			                    	
            	//显示批注形状
				drawAnchor(getAnchorDataPoint(),dataID,i,canvas,XvaluePos,YvaluePos,radius);
					    		
            	if(bd.getLabelVisible())
            	{            			
            		//请自行在回调函数中处理显示格式
            		DrawHelper.getInstance().drawRotateText(
                    		getFormatterDotLabel(
                                    entry.x +","+ entry.y),
                    				XvaluePos,YvaluePos, itemAngle,
                    				canvas,  bd.getDotLabelPaint());            		
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
			Log.w(TAG,"请检查是否有设置分类轴的最大最小值。");
			return false;
		}
		if(null == mDataset)
		{
			Log.w(TAG,"数据源为空.");
			return false;
		}
		
		//绘制四象限
		drawQuadrant(canvas);
							
		//开始处 X 轴 即分类轴              	
		int count = mDataset.size();
		for(int i=0;i<count;i++)
		{																	
			ScatterData bd =  mDataset.get(i);
			if(bd.getPlotDot().getDotStyle().equals(XEnum.DotStyle.HIDE) == true
					&& bd.getLabelVisible() == false )
			{
				continue;
			}			
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
		//图例
		plotLegend.renderPointKey(canvas,mDataset);				
	}
	/////////////////////////////////////////////
	
}
