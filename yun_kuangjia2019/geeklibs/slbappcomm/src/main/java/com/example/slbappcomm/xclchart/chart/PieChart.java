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
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.MathHelper;
import com.example.slbappcomm.xclchart.event.click.ArcPosition;
import com.example.slbappcomm.xclchart.renderer.CirChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.info.PlotArcLabelInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PieChart
 * @Description  饼图基类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class PieChart extends CirChart {
	
	private  static final  String TAG = "PieChart";
			
	//是否使用渲染来突出效果
	private boolean mGradient = true;
	//选中区偏移分离长度
	private float mSelectedOffset = 10.0f;

	//数据源
	private List<PieData> mDataset;
	private Paint mPaintArc = null;

	protected RectF mRectF = null;

	//扇形边框
	protected Paint mPaintArcBorder = null;

	//是否需要保存标签的位置
	private boolean mSaveLabelsPosition = false;
	private XEnum.LabelSaveType mLabelSaveType = XEnum.LabelSaveType.ONLYPOSITION;
	//保存标签的坐标信息
	protected ArrayList<PlotArcLabelInfo> mLstLabels = null;

	//总角度
	private float mTotalAngle = 360.f;

	public PieChart()
	{
		if(null == mLstLabels) mLstLabels = new ArrayList<PlotArcLabelInfo>();
	}

	@Override
	public XEnum.ChartType getType()
	{
		return XEnum.ChartType.PIE;
	}


	/**
	 * 开放饼图扇区的画笔
	 * @return 画笔
	 */
	public Paint geArcPaint()
	{
		if(null == mPaintArc) //画笔初始化
		{
			mPaintArc = new Paint();
			mPaintArc.setAntiAlias(true);
		}
		return mPaintArc;
	}


	/**
	 * 设置图表的数据源
	 * @param piedata 来源数据集
	 */
	public void setDataSource( List<PieData> piedata)
	{
		this.mDataset = piedata;
	}

	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	public List<PieData> getDataSource()
	{
		return mDataset;
	}

	/**
	 * 设置总圆心角度,默认360
	 * @param total 总角度
	 */
	public void setTotalAngle(float total)
	{
		mTotalAngle = total;
	}

	/**
	 * 返回当前总圆心角度
	 * @return 总角度
	 */
	public float getTotalAngle()
	{
		return mTotalAngle;
	}

	/**
	 *  是否保存标签显示位置并设置标签信息的保存类型 <br/>
	 * 	ONLYPOSITION : 保存坐标信息，但不显示标签 <br/>
	 *  ALL : 保存坐标信息，也显示标签 <br/>
	 *  用来在其上绘图片之类操作 <br/>
	 * @param type 类型
	 */
	public void saveLabelsPosition(XEnum.LabelSaveType type)
	{
		mLabelSaveType = type;
		mSaveLabelsPosition = XEnum.LabelSaveType.NONE != type;
	}

	/**
	 * 返回保存的标签坐标点,前提是有指定saveLabelsPosition()，否则返回空
	 * @return 标签坐标点集合
	 */
	public ArrayList<PlotArcLabelInfo> getLabelsPosition()
	{
		return mLstLabels;
	}

	/**
	 * 绘制扇形边框画笔
	 * @return	画笔
	 */
	public Paint getArcBorderPaint()
	{
		if(null == mPaintArcBorder)
		{
			mPaintArcBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
			mPaintArcBorder.setStyle(Style.STROKE);
			mPaintArcBorder.setColor(Color.WHITE);
		}
		return mPaintArcBorder;
	}

	/**
	 * 显示渲染效果(此函数对3D饼图无效)
	 */
	public void showGradient()
	{
		mGradient = true;
	}

	/**
	 * 隐藏渲染效果(此函数对3D饼图无效)
	 */
	public void hideGradient()
	{
		mGradient = false;
	}

	/**
	 * 确认是否可使用渲染效果(此函数对3D饼图无效)
	 * @return 是否使用渲染
	 */
	public boolean getGradient()
	{
		return mGradient;
	}

	/**
	 * 选中区偏移分离距离
	 * @param len 距离
	 */
	public void setSelectedOffset(float len)
	{
		mSelectedOffset = len;
	}

	/**
	 * 选中区偏移分离距离
	 * @return 距离
	 */
	public float getSelectedOffset()
	{
		return mSelectedOffset;
	}


	/**
	 * 给画笔设置渲染颜色和效果
	 * @param paintArc	画笔
	 * @param cirX		中心点X坐标
	 * @param cirY		中心点Y坐标
	 * @param radius	半径
	 * @return	返回渲染效果类
	 */
	private RadialGradient renderRadialGradient(Paint paintArc,
												 float cirX,
												 float cirY,
												 float radius)
	{
		float radialRadius = radius * 0.8f;
		int color = paintArc.getColor();
		int darkerColor = DrawHelper.getInstance().getDarkerColor(color);

		RadialGradient radialGradient = new RadialGradient(cirX, cirY, radialRadius,
				darkerColor,color,
				Shader.TileMode.MIRROR);

		//返回环形渐变
		return radialGradient;
	}

	/**
	 * 检查角度的合法性
	 * @param Angle 角度
	 * @return 是否正常
	 */
	protected boolean validateAngle(float Angle)
	{
		//Log.i(TAG, "扇区圆心角小于等于0度. 当前圆心角为:"+Float.toString(Angle));
		return Float.compare(Angle, 0.0f) != 0
				&& Float.compare(Angle, 0.0f) != -1;
	}

	protected void renderArcBorder(Canvas canvas,RectF rect,
								float offsetAngle,float currentAngle)
	{
		//边框
    	if(null != mPaintArcBorder)
    	{
    		canvas.drawArc(rect, offsetAngle, currentAngle, true, mPaintArcBorder);
    	}
	}

	protected void initRectF(float left,float top,float right,float bottom)
	{
		if(null == mRectF)
        {
			mRectF = new RectF(left ,top,right,bottom);
        }else{
        	mRectF.set(left ,top,right,bottom);
        }
	}

	protected boolean renderLabels(Canvas canvas)
	{
		if(null == mLstLabels) return false;

		boolean showLabel = true;

		if(mSaveLabelsPosition)
		{
			if( XEnum.LabelSaveType.ONLYPOSITION == mLabelSaveType ) showLabel = false;
		}

		int count = mLstLabels.size();
		for(int i=0 ;i<count; i++ )
		{
			 PlotArcLabelInfo info = mLstLabels.get(i);
			 renderLabel(canvas,mDataset.get(info.getID()),info,mSaveLabelsPosition,showLabel);
		}

		if(!mSaveLabelsPosition)mLstLabels.clear();

		return true;
	}

	/**
	 * 绘制图
	 */
	protected boolean renderPlot(Canvas canvas)
	{
		try{
			if(null == mDataset)
			{
	 			//Log.e(TAG,"数据源为空.");
	 			return false;
			}
			//中心点坐标
			float cirX = plotArea.getCenterX();
		    float cirY = plotArea.getCenterY();
	        float radius = getRadius();
	        if(Float.compare(radius, 0.0f) == 0 || Float.compare(radius, 0.0f) == -1)
	        {
	        	return false;
	        }

			//用于存放当前百分比的圆心角度
			float currentAngle = 0.0f;
			float offsetAngle = mOffsetAngle;

			mLstLabels.clear();
			float left = sub(cirX , radius) ;
	        float top = sub(cirY , radius) ;
			float right = add(cirX , radius) ;
			float bottom = add(cirY , radius);

			int count = mDataset.size();
			for(int i=0;i<count;i++)
			{
				PieData cData = mDataset.get(i);
				//currentAngle = cData.getSliceAngle();	
				currentAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) cData.getPercentage());
				if(!validateAngle(currentAngle))continue;			
				geArcPaint().setColor(cData.getSliceColor());	
								
				// 绘制环形渐变
    			if(getGradient())
    				geArcPaint().setShader(renderRadialGradient(geArcPaint(),cirX,cirY,radius));
    											
			    if(cData.getSelected()) //指定突出哪个块
	            {			    	   			    	
	            	//偏移圆心点位置(默认偏移半径的1/10)
	     	    	float newRadius = div(radius , mSelectedOffset);
	     	    	
	     	    	 //计算百分比标签
	     	    	PointF point = MathHelper.getInstance().calcArcEndPointXY(cirX,cirY,
	     	    							newRadius,
	     	    							add(offsetAngle , currentAngle/2f)); 	
	     	        	       
	     	        initRectF(sub(point.x , radius),sub(point.y , radius),
	     	        		  add(point.x , radius),add(point.y , radius)); 
	     	        
	     	        mLstLabels.add(new PlotArcLabelInfo(i,point.x, point.y,radius,offsetAngle, currentAngle));
	            }else{	            	
	            	initRectF(left,top,right,bottom); 	            	 
	    	        mLstLabels.add(new PlotArcLabelInfo(i,cirX,cirY,radius,offsetAngle, currentAngle));
	            }
			    
			    //在饼图中显示所占比例  
            	canvas.drawArc(mRectF, offsetAngle, currentAngle, true, geArcPaint());    
			    
			    //边框
            	renderArcBorder(canvas,mRectF, offsetAngle, currentAngle);
			    			    
			    //保存角度
			    saveArcRecord(i,cirX + this.mTranslateXY[0],cirY + this.mTranslateXY[1],
			    				radius,offsetAngle,currentAngle,
			    				mSelectedOffset,getOffsetAngle());			  
			
			    //下次的起始角度  
			    offsetAngle = add(offsetAngle, currentAngle);	    
			}				
			
			//绘制Label
			renderLabels(canvas);	
			
			//图KEY
			plotLegend.renderPieKey(canvas,this.mDataset);
								
		 }catch( Exception e){
			 Log.e(TAG,e.toString());
			 return false;
		 }
		 return true;
	}
	
	/**
	 * 检验传入参数,累加不能超过360度
	 * @return 是否通过效验
	 */
	protected boolean validateParams()
	{		
		if(null == mDataset)return false;
		float totalAngle = 0.0f,currentValue = 0.0f;	
				
		for(PieData cData : mDataset)
		{			
			//currentValue = cData.getSliceAngle();		
			currentValue = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) cData.getPercentage());
			totalAngle = add(totalAngle,currentValue);				
			//Log.e(TAG,"圆心角:"+Float.toString(currentValue)+" 合计:"+Float.toString(totalAngle));						
			if( Float.compare(totalAngle,0.0f) == -1)
			{
				Log.w(TAG,"传入参数不合理，圆心角总计小于等于0度. 现有圆心角合计:"
						+ totalAngle
						+" 当前圆心角:"+ currentValue
						+" 当前百分比:"+ cData.getPercentage());
				//return false;
			}else if( Float.compare(totalAngle, getTotalAngle() + 0.5f) == 1) 
			{
				//圆心角总计大于360度
				Log.w(TAG,"传入参数不合理，圆心角总计大于总角度. 现有圆心角合计:"
							+ totalAngle);
				//return false;
			}
		}				
		return true;
	}

	/**
	 * 返回当前点击点的信息
	 * @param x 点击点X坐标
	 * @param y	点击点Y坐标
	 * @return 返回对应的位置记录
	 */
	public ArcPosition getPositionRecord(float x, float y)
	{		
		return getArcRecord(x,y);
	}		
	
	@Override
	protected boolean postRender(Canvas canvas) throws Exception 
	{	
		try {
			super.postRender(canvas);
			
			//检查值是否合理
	        if(false == validateParams())return false;
			
			//绘制图表
			renderPlot(canvas);			
			
			//显示焦点
			renderFocusShape(canvas);
			//响应提示
			renderToolTip(canvas);
			
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
