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
import android.graphics.Paint.Align;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.event.click.BarPosition;
import com.example.slbappcomm.xclchart.renderer.AxesChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.bar.Bar;
import com.example.slbappcomm.xclchart.renderer.bar.FlatBar;
import com.example.slbappcomm.xclchart.renderer.info.PlotAxisTick;

import java.util.List;


/**
 * @ClassName RangeBarChart
 * @Description  范围柱形图的基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class RangeBarChart  extends AxesChart {
	
	private static final String TAG = "RangeBarChart";

	// 柱形基类
	private FlatBar mFlatBar = new FlatBar();
	// 数据源
	private List<RangeBarData> mDataSet;

	private String mKey = "";
	private float mBarWidth = 50f;
	private boolean mLabelVisible = true;


	//分类轴的最大，最小值
	private double mMaxValue = 0d;
	private double mMinValue = 0d;


	public RangeBarChart() {

		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
	}

	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.RANGEBAR;
	}

	/**
	 * 开放柱形绘制类
	 * @return 柱形绘制类
	 */
	public Bar getBar() {
		return mFlatBar;
	}

	/**
	 * 分类轴的数据源
	 *
	 * @param categories
	 *            分类集
	 */
	public void setCategories( List<String> categories) {
		if(null != categoryAxis)categoryAxis.setDataBuilding(categories);
	}

	/**
	 * 设置数据轴的数据源
	 *
	 * @param dataSeries
	 *            数据源
	 */
	public void setDataSource( List<RangeBarData> dataSeries) {
		this.mDataSet = dataSeries;
	}

	/**
	 * 返回数据库的数据源
	 * @return 数据源
	 */
	public List<RangeBarData> getDataSource() {
		return mDataSet;
	}
	
	/**
	 * 设置柱形宽度
	 * @param width 宽度
	 */
	public void setBarWidth(float width)
	{
		mBarWidth = width;
	}
	
	/**
	 * 返回柱形宽度
	 * @return 柱形宽度
	 */
	public float getBarWidth()
	{
		return mBarWidth;
	}
	
	/**
	 * 设置图例
	 * @param key 图例
	 */
	public void setKey(String key)
	{
		mKey = key;
	}
	
	/**
	 * 返回图例 
	 * @return 图例
	 */
	public String getKey()
	{
		return mKey;
	}
	
	/**
	 * 设置是否在线上显示标签
	 * @param visible 是否显示
	 */
	public void setLabelVisible(boolean visible) 
	{
		mLabelVisible = visible;
	}
	
	/**
	 * 返回是否在线上显示标签
	 * @return 是否显示
	 */
	public boolean getLabelVisible()
	{
		return mLabelVisible;
	}

	@Override
	protected void categoryAxisDefaultSetting()
	{		
		if(null == categoryAxis) return;
		
		switch (mDirection) {
			case HORIZONTAL:					
					categoryAxis.setHorizontalTickAlign(Align.LEFT);		
					categoryAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);
					categoryAxis.setVerticalTickPosition(XEnum.VerticalAlign.MIDDLE);
					
					setCategoryAxisLocation(XEnum.AxisLocation.LEFT);
				break;			
			 case VERTICAL: 					
					categoryAxis.setHorizontalTickAlign(Align.CENTER);			
					categoryAxis.getTickLabelPaint().setTextAlign(Align.CENTER);					
					categoryAxis.setVerticalTickPosition(XEnum.VerticalAlign.BOTTOM);
					
					setCategoryAxisLocation(XEnum.AxisLocation.BOTTOM);
				break;		
		}
	}
	

	@Override
	protected void dataAxisDefaultSetting()
	{		
		if(null == dataAxis) return;
			
		switch (mDirection) {
			case HORIZONTAL:					
					dataAxis.setHorizontalTickAlign(Align.CENTER);
					dataAxis.getTickLabelPaint().setTextAlign(Align.CENTER);
					dataAxis.setVerticalTickPosition(XEnum.VerticalAlign.BOTTOM);
					
					setDataAxisLocation(XEnum.AxisLocation.BOTTOM);
				break;
			case VERTICAL: 					
					dataAxis.setHorizontalTickAlign(Align.LEFT);
					dataAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);	
					dataAxis.setVerticalTickPosition(XEnum.VerticalAlign.MIDDLE);
					
					setDataAxisLocation(XEnum.AxisLocation.LEFT);
				break;
		}
	}
	

	private float[] cataPosition(double min,double max)
	{
		float[] pos = new float[2];
		float axisDataHeight = dataAxis.getAxisRange();	
		float scrHeight = getAxisScreenHeight();
		
		double t = MathHelper.getInstance().sub( min , dataAxis.getAxisMin() );
		pos[0] = mul( scrHeight,div( (float) (t),axisDataHeight) );
				
		t = MathHelper.getInstance().sub( max , dataAxis.getAxisMin() );
		pos[1] = mul( scrHeight,div( (float) (t),axisDataHeight) );
				
		return pos;
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
	 * 绘制竖向柱形图
	 */
	protected boolean renderVerticalBar(Canvas canvas) {
		
		//检查是否有设置分类轴的最大最小值		
		if(mMaxValue == mMinValue && 0 == mMaxValue)
		{
			Log.e(TAG,"请检查是否有设置分类轴的最大最小值。");
			return false;
		}
		
		if(null == mDataSet)
		{
			Log.e(TAG,"数据轴数据源为空");
			return false;
		}
		
		// 得到分类轴数据集
		List<String> dataSet = categoryAxis.getDataSet();
		if(null == dataSet)
		{
			Log.e(TAG,"分类轴数据集为空.");
			return false;
		}	
		
				
		if(null == mDataSet) return false;	
		// 得到分类轴数据集
		List<String> cateDataSet = categoryAxis.getDataSet();
		if(null == cateDataSet) return false;
		
		//float XSteps = getVerticalXSteps(cateDataSet.size() + 1 );	
		float currentX = 0.0f,barMaxPos = 0.0f,barMinPos = 0.0f;
		 		 				
		float barWidthHalf = mBarWidth/2;
	
		float axisScreenWidth = getPlotScreenWidth(); 
		float fontHeight = DrawHelper.getInstance().getPaintFontHeight(
												mFlatBar.getItemLabelPaint());

		// X 轴 即分类轴
		int dataSetSize = mDataSet.size();	
		for (int i = 0; i < dataSetSize; i++) {
			// 得到分类对应的值数据集
			RangeBarData bd = mDataSet.get(i);
			//currentX = add(plotArea.getLeft() , mul( (i+1) , XSteps));
			//
			currentX = (float) (axisScreenWidth * ( (bd.getX() - mMinValue ) / (mMaxValue - mMinValue))) ;  
			currentX =  add(plotArea.getLeft(),currentX);
			
			float[] pos = cataPosition(bd.getMin(), bd.getMax());
			
			barMaxPos = sub(plotArea.getBottom(),pos[0]);
			barMinPos = sub(plotArea.getBottom(),pos[1]);
			
			mFlatBar.renderBar(currentX - barWidthHalf ,barMaxPos,
							   currentX + barWidthHalf ,barMinPos,
							   canvas);
			//保存位置
			saveBarRectFRecord(i,0,currentX - barWidthHalf + mMoveX, barMaxPos + mMoveY,
								   currentX + barWidthHalf + mMoveX, barMinPos + mMoveY );
		
			 //显示焦点框
       		drawFocusRect(canvas,i,0,currentX - barWidthHalf ,barMaxPos,
					   currentX + barWidthHalf ,barMinPos);
       					
			if(getLabelVisible())
			{
				//柱形标签
				mFlatBar.renderBarItemLabel(getFormatterItemLabel(bd.getMax()), 
										currentX, barMinPos - fontHeight/2, canvas);	
			
				//柱形标签
				mFlatBar.renderBarItemLabel( getFormatterItemLabel(bd.getMin()), 
										currentX, barMaxPos + fontHeight + fontHeight/2, canvas);
			}
		}
		return true;
	}
	
	
	//轴刻度
	/*
	private void renderAxesTick(Canvas canvas)
	{				
		drawCategoryAxisLabels(canvas,mLstCateTick);		
		mLstCateTick.clear();
		
		drawDataAxisLabels(canvas,mLstDataTick);		//XEnum.Direction.VERTICAL
		mLstDataTick.clear();
	}
	
	*/
	
	/**
	 * 返回当前点击点的信息
	 * @param x 点击点X坐标
	 * @param y	点击点Y坐标
	 * @return 返回对应的位置记录
	 */
	public BarPosition getPositionRecord(float x, float y)
	{		
		return getBarRecord(x,y);
	}
		
	@Override
	protected void drawClipDataAxisGridlines(Canvas canvas) 
	{						
		// 与柱形图不同，无须多弄一个
		float XSteps = 0.0f,YSteps = 0.0f;
		
		// 数据轴数据刻度总个数
		int tickCount = dataAxis.getAixTickCount();
		int labeltickCount = tickCount;
		
		if( 0 == tickCount)
		{
			Log.e(TAG,"数据库数据源为0!");
			return ;
		}else if (1 == tickCount)  //label仅一个时右移
			    labeltickCount = tickCount - 1 ;
					
		// 标签轴(X 轴)		
		float axisX = 0.0f,axisY = 0.0f,currentX = 0.0f,currentY = 0.0f;
		// 标签
		double currentTickLabel = 0d;
		// 轴位置
		XEnum.AxisLocation pos = getDataAxisLocation();
				
		//步长
		switch(pos)
		{			 
			case LEFT: //Y
			case RIGHT:		
			case VERTICAL_CENTER:	
				YSteps = getVerticalYSteps(labeltickCount) ;
				currentX = axisX =getAxisXPos(pos);
				currentY = axisY = plotArea.getBottom();
				break;						
			case TOP: //X
			case BOTTOM:
			case HORIZONTAL_CENTER:	
				XSteps = getVerticalXSteps(labeltickCount);	
				currentY = axisY = getAxisYPos(pos);
				currentX = axisX = plotArea.getLeft();
				break;			
		}
		
		mLstDataTick.clear();			
		//绘制
		for (int i = 0; i < tickCount; i++)
		{					
			switch(pos)
			{				 
				case LEFT: //Y
				case RIGHT:								
				case VERTICAL_CENTER:	
					// 依起始数据坐标与数据刻度间距算出上移高度
					currentY = sub(plotArea.getBottom(), mul(i,YSteps));
					
					// 从左到右的横向网格线
					drawHorizontalGridLines(canvas,plotArea.getLeft(),plotArea.getRight(),
																i,tickCount,YSteps,currentY);
																			
					//if(!dataAxis.isShowAxisLabels())continue;
					 
					// 标签					
					currentTickLabel = MathHelper.getInstance().add(
										dataAxis.getAxisMin(),i * dataAxis.getAxisSteps());	
																							
					mLstDataTick.add(new PlotAxisTick(i, axisX ,
												currentY, Double.toString(currentTickLabel)));										
					break;							
				case TOP: //X
				case BOTTOM:	
				case HORIZONTAL_CENTER:		
					//bar
					// 依起始数据坐标与数据刻度间距算出上移高度
					currentX = add(axisX , mul(i , XSteps));
					
								
					 //绘制竖向网格线
					 drawVerticalGridLines(canvas,plotArea.getTop(),plotArea.getBottom(),
													i ,tickCount,XSteps,currentX);
				
					// if(!dataAxis.isShowAxisLabels())continue;
					 
					// 画上标签/刻度线	
					currentTickLabel = MathHelper.getInstance().add(
											dataAxis.getAxisMin(),i * dataAxis.getAxisSteps());	
					
					mLstDataTick.add(new PlotAxisTick(i,currentX, axisY,
														Double.toString(currentTickLabel)));
														
					break;	
			} //switch end						
		} //end for	
	}
	
	
	/**
	 * 绘制底部标签轴
	 */
	@Override
	protected void drawClipCategoryAxisGridlines(Canvas canvas) 
	{				
		// 得到标签轴数据集
		List<String> dataSet = categoryAxis.getDataSet();
		// 与柱形图不同，无须多弄一个
		float XSteps = 0.0f,YSteps = 0.0f;		
	
		int tickCount = dataSet.size() ;
		int labeltickCount = tickCount + 1;
		if( 0 == tickCount)
		{
			Log.e(TAG,"分类轴数据源为0!");
			return ;
		}
			
		// 标签轴(X 轴)
		float axisX = 0.0f,axisY = 0.0f,currentX = 0.0f,currentY = 0.0f;
		
		XEnum.AxisLocation pos = getCategoryAxisLocation();
								
		if( XEnum.AxisLocation.LEFT == pos ||
				XEnum.AxisLocation.RIGHT == pos||
				XEnum.AxisLocation.VERTICAL_CENTER == pos)
		{		
			//line
			YSteps = getVerticalYSteps( labeltickCount) ;
			currentX = axisX = getAxisXPos(pos);
			currentY = axisY = plotArea.getBottom();										
		}else{ //TOP BOTTOM																	
			// 依传入的分类个数与轴总宽度算出要画的分类间距数是多少
			// 总宽度 / 分类个数 = 间距长度    //getAxisScreenWidth() 			
			XSteps = getVerticalXSteps(labeltickCount);
			currentY = axisY = getAxisYPos(pos);
			currentX = axisX = plotArea.getLeft();
		}
					
		mLstCateTick.clear();	
		
		//绘制
		for (int i = 0; i < tickCount; i++) 
		{			
			switch(pos)
			{				 
				case LEFT: //Y
				case RIGHT:			
				case VERTICAL_CENTER:						
					// 依初超始Y坐标与分类间距算出当前刻度的Y坐标
					currentY = sub(axisY, mul((i + 1) , YSteps));										
																							
					// 从左到右的横向网格线
					drawHorizontalGridLines(canvas,plotArea.getLeft(),plotArea.getRight(),
																i,tickCount,YSteps,currentY);
					
					if(!categoryAxis.isShowAxisLabels()) continue;	
										 
					// 分类
					mLstCateTick.add(new PlotAxisTick(	axisX,
														currentY, categoryAxis.getDataSet().get(i) ));					
					break;							
				case TOP: //X
				case BOTTOM:			
				case HORIZONTAL_CENTER:	
					 // 依初超始X坐标与分类间距算出当前刻度的X坐标
					 currentX = add(plotArea.getLeft(),mul((i + 1) , XSteps)); 
															
					 //绘制竖向网格线
					 drawVerticalGridLines(canvas,plotArea.getTop(),plotArea.getBottom(),
													i ,tickCount,XSteps,currentX);
				
					 if(!categoryAxis.isShowAxisLabels()) continue;	
					 						 
					mLstCateTick.add(new PlotAxisTick( currentX,axisY, dataSet.get(i)));
																
					break;			
			} //switch end
		
		} //end for
	
	}
	
	@Override
	protected void drawClipPlot(Canvas canvas)
	{		
		switch (mDirection) 
		{
		case HORIZONTAL: 
			//renderHorizontalBar(canvas);
			break;				
		case VERTICAL: 
			renderVerticalBar(canvas);
			break;				
		}
	}
	
	
	@Override
	protected void drawClipLegend(Canvas canvas)
	{
		// 绘制柱形图例
		plotLegend.renderRangeBarKey(canvas,getKey(),mFlatBar.getBarPaint().getColor());
	}
	////////////////////////

}
