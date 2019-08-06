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

import com.example.slbappcomm.xclchart.renderer.XEnum;

/**
 * @ClassName PieData
 * @Description 数据类, 饼图,rose图,环形图等都用这个传数据
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class PieData {
	
	//private final String TAG ="PieData";

	private String mPieKey = "";
	private String mPieLabel = "";
	private double mPieValue = 0.0f;
	private int mPieColor = 0 ;
	//private int mSliceAngle = 0;
	
	//是否突出饼图
	private boolean mSelected = false;
	
	//标签文字旋转角度
	private float mItemLabelRotateAngle = 0.0f;	
	
	//标签注释显示位置 [隐藏,Default,Inside,Ouside,Line]
	private XEnum.SliceLabelStyle mLabelStyle  = XEnum.SliceLabelStyle.INSIDE;
	private boolean mCustLabelStyle = false;	
	private int mCustLabelColor = 0 ;		
	
	public PieData() {
		// TODO Auto-generated constructor stub

	}
		
	/**
	 * 
	 * @param label		标签
	 * @param percent	比例(0-100)
	 * @param color		显示颜色
	 */
	public PieData(String label,double percent,int color)
	{
		setLabel(label);
		setPercentage(percent);
		setSliceColor(color);
	}
	
	/**
	 * 
	 * @param label		标签
	 * @param percent	比例(0-100)
	 * @param color		显示颜色
	 * @param selected	是否突出显示
	 */
	public PieData(String label,double percent,int color,boolean selected)
	{
		setLabel(label);
		setPercentage(percent);
		setSliceColor(color);
		setSelected(selected);
	}
	
	/**
	 * 
	 * @param key		键值
	 * @param label		标签
	 * @param percent	比例(0-100)
	 * @param color		显示颜色
	 */
	public PieData(String key,String label,double percent,int color)
	{
		setLabel(label);
		setPercentage(percent);
		setSliceColor(color);
		setKey(key);
	}
	
	/**
	 * 
	 * @param key		键值
	 * @param label		标签
	 * @param percent	比例(0-100)
	 * @param color		显示颜色
	 * @param selected	是否突出显示
	 */
	public PieData(String key,String label,double percent,int color,boolean selected)
	{
		setLabel(label);
		setPercentage(percent);
		setSliceColor(color);
		setKey(key);
		setSelected(selected);
	}
	
	/**
	 * 设置Key值
	 * @param key Key值
	 */
	public void setKey(String key)
	{
		mPieKey = key;
	}	
	
	/**
	 * 返回Key值
	 * @return Key值
	 */
	public String getKey()
	{
		return mPieKey;
	}
	
	/**
	 * 设置标签
	 * @param label 标签
	 */
	public void setLabel(String label)
	{
		mPieLabel = label;
	}	
		
	/**
	 * 设置比例 (0-1),绘制时，会将其转换为对应的圆心角
	 * @param value 比例 
	 */
	public void setPercentage(double value)
	{
		mPieValue = value;
	}
	
	/**
	 * 设置扇区颜色
	 * @param color 颜色
	 */
	public void setSliceColor(int color)
	{
		mPieColor = color;
	}
	/**
	 * 设置当前块是否突出，true为突出
	 * @param selected 是否突出
	 */
	public void setSelected(boolean selected)
	{
		mSelected = selected;
	}

	/**
	 * 返回标签
	 * @return 标签
	 */
	public String getLabel()
	{
		return mPieLabel;
	}
	
	/**
	 * 返回当前比例(0-100)
	 * @return 比例 
	 */
	public double getPercentage()
	{
		return mPieValue;
	}

	/**
	 * 是否突出显示
	 * @return 是否突出
	 */
	public boolean getSelected()
	{
		return mSelected;
	}
	
	/**
	 * 返回扇区颜色
	 * @return 颜色
	 */
	public int getSliceColor()
	{
		return mPieColor;
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
	
	/**
	 * 可用于定制记录的标签显示类型
	 * @param style	标签风格
	 * @param color 标签颜色
	 */
	public void setCustLabelStyle(XEnum.SliceLabelStyle style, int color)
	{
		mLabelStyle = style;
		mCustLabelStyle = true;
		mCustLabelColor = color;
	}
	
	/**
	 * 返回标签显示类型
	 * @return 类型
	 */
	public XEnum.SliceLabelStyle getLabelStyle()
	{
		return mLabelStyle;
	}
	
	/**
	 * 返回是否有定制过标签类型
	 * @return 状态
	 */
	public boolean getCustLabelStyleStatus()
	{
		return mCustLabelStyle;
	}
	
	/**
	 * 返回标签定制颜色
	 * @return 颜色
	 */
	public int getCustLabelColor()
	{
		return mCustLabelColor;
	}
	
}
