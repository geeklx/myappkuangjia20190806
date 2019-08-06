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

import android.graphics.Paint;

import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.line.PlotDot;

import java.util.List;

/**
 * @ClassName ScatterData
 * @Description 散点图数据类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */

public class ScatterData {
	
	//标签轴用的到值
	private String mLabel = "";
	//是否在点上显示标签
	private boolean mLabelVisible = false;

	//线上每个点的值
	//private LinkedHashMap<Double,Double> mPointMap ;
	
	//线上每个点的值
	private List<PointD> mPointMap;


	//标签画笔
	private Paint mLabelPaint = null;
	//点画笔
	private PlotDot mPlotDot = null;

	//标签文字旋转角度
	private float mItemLabelRotateAngle = 0.0f;


	public ScatterData(){}

	/**
	 * 构成一条完整的线条
	 * @param key 	 对应的键值
	 * @param dataSeries  对应的数据序列
	 * @param color  线条颜色
	 */
	public ScatterData(String key,
						List<PointD> dataSeries,
						int color,
						XEnum.DotStyle  dotStyle) {
		// TODO Auto-generated constructor stub

		setKey(key);
		setDataSet(dataSeries);

		getPlotDot().setColor(color);
		getPlotDot().setDotStyle(dotStyle);
	}

	/**
	 * 设置绘制线的数据序列,由x与y坐标构建
	 * @param dataSeries <X坐标值，Y坐标值>
	 */
	public void setDataSet( List<PointD>  dataSeries)
	{
		mPointMap = dataSeries;
	}
	
	/**
	 * 返回绘制线的数据序列
	 * @return 线的数据序列<X坐标值，Y坐标值>
	 */
	public List<PointD> getDataSet() {
		return mPointMap;
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
	
	/**
	 * 设置标签
	 * @param value 标签内容
	 */
	public void setLabel(String value) 
	{
		mLabel = value;
	}
	
	/**
	 * 返回标签
	 * @return 标签
	 */
	public String getLabel() {
		return mLabel;
	}

	
	/**
	 * 设置当前记录的Key值
	 * @param value Key值
	 */
	public void setKey(String value) 
	{
		mLabel = value;
	}
	
	/**
	 * 返回Key值
	 * @return Key值
	 */
	public String getKey() {
		return mLabel;
	}
	
	/**
	 * 开放标签画笔
	 * @return 画笔
	 */
	public Paint getDotLabelPaint()
	{
		if(null == mLabelPaint) 
			mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		return mLabelPaint;
	}
	
	/*
	 * 点的绘制类	
	 */
	public PlotDot getPlotDot()
	{
		if(null == mPlotDot)
		{
			mPlotDot = new PlotDot();
			mPlotDot.setDotStyle(XEnum.DotStyle.DOT);
		}
		return mPlotDot;
	}
	
	
	/**
	 * 设置点的显示风格
	 * @param style 显示风格
	 */
	public void setDotStyle(XEnum.DotStyle style)
	{
		getPlotDot().setDotStyle(style);
	}
	
	/**
	 * 返回点的显示风格
	 * @return 显示风格
	 */
	public XEnum.DotStyle getDotStyle()
	{
		return getPlotDot().getDotStyle();
	}
	
	/**
	 * 返回标签在显示时的旋转角度
	 * @return 旋转角度
	 */
	public float getItemLabelRotateAngle() {
		return mItemLabelRotateAngle;
	}

	/**
	 * 设置标签在显示时的旋转角度
	 * @param rotateAngle 旋转角度
	 */
	public void setItemLabelRotateAngle(float rotateAngle) {
		this.mItemLabelRotateAngle = rotateAngle;
	}

	
}
