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
import android.graphics.PointF;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.info.PlotArcLabelInfo;

import java.util.List;

/**
 * @ClassName Pie3DChart
 * @Description  3D饼图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class PieChart3D extends PieChart {
	
	private static final String TAG="PieChart3D";
	
	//渲染层数
	private final int mRender3DLevel = 15; 
	
		
	public PieChart3D() {
		// TODO Auto-generated constructor stub
			 		
	}
	
	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.PIE3D;
	}
	
	private boolean render3D(Canvas canvas,
							 float initOffsetAngle,
							 List<PieData> chartDataSource,
							 float cirX, float cirY, float radius)
	{		
		if(null == chartDataSource) return false;
 		float offsetAngle = initOffsetAngle;		
        float currentAngle = 0.0f;	              
        float newRadius = 0.0f;	
        int size = 0;
     
		for(int i=0;i < mRender3DLevel;i++)
		{
//              canvas.save(Canvas.MATRIX_SAVE_FLAG);
              canvas.save();
              canvas.translate(0,mRender3DLevel - i );
              size = chartDataSource.size();
			  for(int j=0;j< size;j++)
			  {			  
				    PieData cData =  chartDataSource.get(j);
				    if(null == cData) continue;
					//currentAngle = cData.getSliceAngle();		
					currentAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) cData.getPercentage());
					if(!validateAngle(currentAngle)) continue;
					geArcPaint().setColor(cData.getSliceColor());	
					
				    if(cData.getSelected()) //指定突出哪个块
		            {				    			    	
				    	//偏移圆心点位置(默认偏移半径的1/10)
				    	newRadius = div(radius , getSelectedOffset());
				    	 //计算百分比标签
				    	PointF point = MathHelper.getInstance().calcArcEndPointXY(
				    								cirX,cirY,newRadius,
				    								add(offsetAngle, div(currentAngle,2f))); 	
				        			        
				        initRectF(sub(point.x, radius) ,sub(point.y , radius), 
				        		   add(point.x , radius),add(point.y , radius));
				        
	                    canvas.drawArc(mRectF, offsetAngle, currentAngle, true,geArcPaint());
		            }else{		       
		                //确定饼图范围       
		                initRectF(sub(cirX , radius) ,sub(cirY , radius),  
		                		  add(cirX , radius) ,add(cirY , radius)); 		            		                
	                    canvas.drawArc(mRectF, offsetAngle, currentAngle, true,geArcPaint()); 
		            }			    			    
		            //下次的起始角度  
				    offsetAngle = add(offsetAngle,currentAngle);  	            		           
				}
			  
	            canvas.restore();
	            offsetAngle = initOffsetAngle;
		}
		
		return true;
	}
	
	private boolean renderFlatArcAndLegend(Canvas canvas,
										float initOffsetAngle,
										List<PieData> chartDataSource,
										float cirX,float cirY,float radius)
	{
		
		if(null == chartDataSource) return false;
		
 		float offsetAngle = initOffsetAngle;				
        float currentAngle = 0.0f;	              
        float newRadius = 0.0f;	
        int size = chartDataSource.size();
        mLstLabels.clear();
        
        float left = sub(cirX , radius) ;
        float top = sub(cirY , radius) ;        
		float right = add(cirX , radius) ;
		float bottom = add(cirY , radius);		  
       
		for(int j=0;j< size;j++)
		{
		 	PieData cData = chartDataSource.get(j);
		 	if(null == cData) continue;
		 	//currentAngle = cData.getSliceAngle();
		 	currentAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) cData.getPercentage());
		 	if(!validateAngle(currentAngle)) continue;		  
		 	geArcPaint().setColor( DrawHelper.getInstance().getDarkerColor(
		 												cData.getSliceColor()) ); 						
		  	
		    if(cData.getSelected()) //指定突出哪个块
            {					    					    	
		    	//偏移圆心点位置(默认偏移半径的1/10)
		    	newRadius = div(radius , getSelectedOffset());
		    	 //计算百分比标签
		    	PointF point = MathHelper.getInstance().calcArcEndPointXY(
		    					cirX,cirY,newRadius,add(offsetAngle , div(currentAngle,2f))); 	
		          		        
		        initRectF(sub(point.x , radius) ,sub(point.y , radius ), 
		        		  add(point.x , radius ),add(point.y , radius));   
		        
                canvas.drawArc(mRectF, offsetAngle, currentAngle, true,geArcPaint());
                
		        mLstLabels.add(new PlotArcLabelInfo(j,point.x, point.y,radius,offsetAngle, currentAngle));
            }else{
            	//确定饼图范围       
                initRectF(left,top,right,bottom); 		
                
                canvas.drawArc(mRectF, offsetAngle, currentAngle, true, geArcPaint()); 
                
     	       mLstLabels.add(new PlotArcLabelInfo(j,cirX,cirY,radius,offsetAngle, currentAngle));
     	    }		
		
		    //保存角度
		    saveArcRecord(j,cirX+ this.mTranslateXY[0],cirY+ this.mTranslateXY[1],
		    					radius,offsetAngle,currentAngle,
		    					getSelectedOffset(),this.getOffsetAngle());
		    
           //下次的起始角度  
		    offsetAngle = add(offsetAngle,currentAngle);		        		  
		}		
		
		//绘制Label
		renderLabels(canvas);			
		
		//图例
		plotLegend.renderPieKey(canvas,chartDataSource);	
		
		return true;
	}


	@Override 
	protected boolean renderPlot(Canvas canvas)
	{				
		//数据源
 		List<PieData> chartDataSource = this.getDataSource();
 		if(null == chartDataSource)
		{
 			Log.e(TAG,"数据源为空.");
 			return false;
		}
 	 		
		//计算中心点坐标		
		float cirX = plotArea.getCenterX();
	    float cirY = plotArea.getCenterY();	     
        float radius = getRadius();
       
		if(render3D(canvas,mOffsetAngle,chartDataSource,cirX, cirY, radius))
		{
			return renderFlatArcAndLegend(canvas,mOffsetAngle,chartDataSource,
											cirX, cirY, radius);
		}else{
			return false;
		}
	}

}
