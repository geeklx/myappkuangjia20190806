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
 * @version 2.1
 */
package com.example.slbappcomm.xclchart.chart;


/**
 * @ClassName Funnel2Data
 * @Description 漏斗图数据类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class Funnel2Data implements Comparable<Funnel2Data> {
	
	private String mLabel;
	//private String mKey;
	
	//private float mData;
	
	

	
	private float mBaseData,mPercentData;
	private int mColor;
	
	
	
	//透明度
  	private int mAlpha = -1;  
  	
	
	public Funnel2Data()
	{
		
	}
	
	public Funnel2Data(String label,float base,float percent,int color) 
	{
		setLabel(label);	
		//setKey(key);
		setBaseData(base);
		setPercentData(percent);
		setColor(color);				
	}
	
	public void setAlpha(int alpha) 
	{
		mAlpha = alpha;
	}
	
	public int getAlpha() 
	{
		return mAlpha;
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
	 * 设置Key值
	 * @param value Key值
	 */
	//public void setKey(String value) 
	//{
	//	mKey = value;
	//}
	
	/**
	 * 设置颜色
	 * @param value 颜色
	 */
	public void setColor(int value) 
	{
		mColor = value;
	}
	
	public int getColor()
	{
		return mColor;
	}

	/**
	 * 设置基数数据源
	 * @param data 初始值
	 */
	public void setBaseData(float data) 
	{
		mBaseData = data;
	}
	
	/**
	 * 设置百分比数据源,即基数在最后所占的百分比
	 * @param percent 所占百分比(0-1)
	 */
	public void setPercentData(float percent) 
	{
		mPercentData = percent;
	}
	
	
	/**
	 * 返回基数集合
	 * @return 基数
	 */
	public float  getBaseData() { 
		return mBaseData;
	}
	
	/**
	 * 返回百分比数据
	 * @return 所占比数据(0-1)
	 */
	public float  getPercentData() {
		return mPercentData;
	}



	@Override
    public int compareTo(Funnel2Data arg0) {
		 Float a = this.getBaseData();
		 Float b = arg0.getBaseData();
		 return Float.compare(a, b);
    }

}
