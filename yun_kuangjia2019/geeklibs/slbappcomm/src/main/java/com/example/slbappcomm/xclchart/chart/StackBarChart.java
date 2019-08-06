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

import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.bar.Bar;
import com.example.slbappcomm.xclchart.renderer.bar.FlatBar;

import java.util.List;

/**
 * @ClassName StackBarChart
 * @Description  堆叠图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class StackBarChart  extends BarChart {

	private FlatBar flatBar = null;
	private boolean mTotalLabelVisible = true;

	public StackBarChart()
	{
		if(null == flatBar)flatBar = new FlatBar();
	}

	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.STACKBAR;
	}

	/**
	 * 是否在柱形的最上方，显示汇总标签
	 * @param visible 是否显示
	 */
	public void setTotalLabelVisible(boolean visible)
	{
		mTotalLabelVisible = visible;
	}

	@Override
	public Bar getBar()
	{
		return flatBar;
	}

	private float getHBarHeight(float YSteps)
	{
		float barHeight = MathHelper.getInstance().round(mul(YSteps,0.5f),2);
		float maxHeight = flatBar.getBarMaxPxHeight();

		if( Float.compare(maxHeight, 0.0f) ==  1 &&
				Float.compare(barHeight, maxHeight) == 1 )
		{
			barHeight = maxHeight;
		}
		return barHeight;
	}

	@Override
	protected boolean renderHorizontalBar(Canvas canvas)
	{
		 if(null == categoryAxis.getDataSet()) return false;

		 float axisScreenWidth  =  getPlotScreenWidth();
		 float axisDataRange = dataAxis.getAxisRange();
		 float valueWidth = axisDataRange;

		//步长
		 float YSteps = getVerticalYSteps(getCateTickCount());
		 float barHeight = getHBarHeight(YSteps);

		 int cateSize = categoryAxis.getDataSet().size();
		 int dataSize = 0;
		 float labelY = 0.f;

		//分类轴
		 for(int r=0;r < cateSize ;r++)
		 {
			 	 float currentX = plotArea.getLeft();
				 float currentY = sub(plotArea.getBottom() , mul((r+1) , YSteps));
				 double total = 0d;

				 labelY = currentY;
				 if(XEnum.BarCenterStyle.SPACE == this.getBarCenterStyle())
				 {
					 labelY = add(currentY ,div(YSteps,2));
					 currentY = labelY;
				 }

				//得到数据源
				List<BarData> chartDataSource = this.getDataSource();
				if(null==chartDataSource||chartDataSource.size() == 0)continue;

				 dataSize = chartDataSource.size();
				 for(int i=0;i<dataSize;i++) //轴上的每个标签各自所占的高度
				 {
					 BarData bd = chartDataSource.get(i);
					 if(null == bd.getDataSet()) continue ;
					 flatBar.getBarPaint().setColor(bd.getColor());
					 if(bd.getDataSet().size() < r+1) continue;

					//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来
					double bv = bd.getDataSet().get(r);
					total = MathHelper.getInstance().add(total, bv);

					float valuePostion = 0.0f;
					if(i == 0 )
					{
						double t = MathHelper.getInstance().sub( bv , dataAxis.getAxisMin() );
						valuePostion = mul( axisScreenWidth,div((float) (t),valueWidth) );
					}else{
						float t2 =  div((float) bv , valueWidth) ;
						valuePostion =  mul( axisScreenWidth , t2);
					}

				   	//宽度
					float topY = sub(currentY , barHeight/2);
					float rightX = add(currentX , valuePostion);
					float bottomY = add(currentY , barHeight/2);
				    flatBar.renderBar(currentX ,topY,rightX,bottomY,canvas);

				    //保存位置
				    saveBarRectFRecord(i,r,currentX + mMoveX, topY + mMoveY,rightX + mMoveX, bottomY+ mMoveY);

				    //显示焦点框
	           		drawFocusRect(canvas,i,r,currentX ,topY,rightX,bottomY);

	           		float labelLeftX = add(currentX , valuePostion/2);

	           		//在柱形的顶端显示上批注形状
					drawAnchor(this.getAnchorDataPoint(),i,r,canvas,labelLeftX,labelY,0.0f);

					//柱形的当前值
					flatBar.renderBarItemLabel(getFormatterItemLabel(bv),labelLeftX, labelY , canvas);
					currentX = add(currentX,valuePostion);
				 }

				 //合计
				 if(mTotalLabelVisible)
				 {
					 double t = MathHelper.getInstance().sub(total , dataAxis.getAxisMin());
					 float totalPostion = mul(div(axisScreenWidth,axisDataRange),(float) (t));
					 flatBar.renderBarItemLabel(getFormatterItemLabel(total),
							 					add(plotArea.getLeft() , totalPostion), currentY, canvas);
				 }
		 }
		return true;
	}

	private float getVBarWidth(float XSteps)
	{
		float barWidth = mul(XSteps,0.5f);
		float maxWidth = flatBar.getBarMaxPxWidth();

		if( Float.compare(maxWidth, 0.0f) ==  1 &&
				Float.compare(barWidth, maxWidth) == 1 )
		{
			barWidth = maxWidth;
		}
		return barWidth;
	}


	@Override
	protected boolean renderVerticalBar(Canvas canvas)
	{
			//得到分类轴数据集
			List<String> dataSet =  categoryAxis.getDataSet();
			if(null == dataSet) return false;

			//得到数据源
			List<BarData> chartDataSource = this.getDataSource();
			if(null == chartDataSource) return false;	
					
			float XSteps = getVerticalXSteps(dataSet.size() + 1 );			 
			float axisScreenHeight  =  getAxisScreenHeight(); 
			float axisDataHeight =  dataAxis.getAxisRange(); 			
			float barWidth = getVBarWidth(XSteps); // mul(XSteps,0.5f);	
			
			 float currentX = 0.0f,currentY = 0.0f,labelX = 0.f;
			
			 int dataSize = dataSet.size();	
			 int sourceSize = 0;
			 for(int r=0;r<dataSize;r++) //轴上的每个标签
			 {				 				 		
				 	 currentX = add(plotArea.getLeft() , mul( (r+1) , XSteps));				 	 
					 currentY = plotArea.getBottom() ;
					 Double total = 0d;
					 
					 labelX = currentX;
					 if(XEnum.BarCenterStyle.SPACE == this.getBarCenterStyle())
					 {
						 labelX = sub(currentX ,div(XSteps,2)); 
						 currentX = labelX;
					 }
					 
					 sourceSize = chartDataSource.size();
					 for(int i=0; i < sourceSize;i++) //各自所占的高度
					 {
						 BarData bd = chartDataSource.get(i);
						 if(null == bd.getDataSet()) continue ;
						 
						 flatBar.getBarPaint().setColor(bd.getColor());						 
						 if(bd.getDataSet().size() < r+1) continue; 						 
						 
						//参数值与最大值的比例  照搬到 y轴高度与矩形高度的比例上来	
						Double bv = bd.getDataSet().get(r);												
						total = MathHelper.getInstance().add(total, bv);
					
						float valuePostion = 0.0f;
						if(i == 0 )
						{													
							double t = MathHelper.getInstance().sub( bv , dataAxis.getAxisMin() );
							valuePostion = mul( axisScreenHeight,div( (float) (t),axisDataHeight) );							 								 							 
						}else{												
							double t2 =  MathHelper.getInstance().div(bv, axisDataHeight) ;
							valuePostion =  mul( axisScreenHeight , (float) (t2));		
						}
						
						//柱形
						float leftX = sub(currentX , barWidth/2);
						float topY =  sub(currentY , valuePostion);
						float rightX = add(currentX , barWidth/2);						
						flatBar.renderBar(leftX, topY, rightX, currentY, canvas);
						//保存位置
						saveBarRectFRecord(i,r,leftX + mMoveX, topY + mMoveY,rightX + mMoveX, currentY + mMoveY); 
						
						//显示焦点框
		           		drawFocusRect(canvas,i,r,leftX, topY, rightX, currentY);
						
		           		float labelLeftY = sub(currentY , valuePostion/2);
		           		
		           		//在柱形的顶端显示上批注形状
						drawAnchor(this.getAnchorDataPoint(),i,r,canvas,labelX,labelLeftY,0.0f);
						
						//柱形的当前值
						flatBar.renderBarItemLabel(getFormatterItemLabel(bv), 
								labelX,labelLeftY, canvas);	
						
						currentY = sub(currentY,valuePostion);
					 }
					 
					//合计		
				 if(mTotalLabelVisible)
				 {			 					 					  					 
					 double per =  MathHelper.getInstance().sub(total , dataAxis.getAxisMin());
					 float totalPostion =  MathHelper.getInstance().mul(div(axisScreenHeight,axisDataHeight) , (float) (per));
					 flatBar.renderBarItemLabel(getFormatterItemLabel(total), 
							 					currentX, sub(plotArea.getBottom() , totalPostion), canvas);
				 }
			 }			 
		
			 return true;
	}
	
	
	
}
