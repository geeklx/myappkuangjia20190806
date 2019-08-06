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
 * @ClassName FunnelData
 * @Description 漏斗图数据类
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 *  
 */
public class FunnelData implements Comparable<FunnelData> {
	
	private String mLabel;
	//private String mKey;
	
	private float mData;
	private int mColor;
	
	//透明度
  	private int mAlpha = -1;  
  	
	
	public FunnelData()
	{
		
	}
	
	public FunnelData(String label,float percent,int color) 
	{
		setLabel(label);	
		//setKey(key);
		setData(percent);
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
	 * 设置数据源
	 * @param percent 百分比
	 */
	public void setData(float percent) 
	{
		mData = percent;
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
	 * 返回数据集合序列
	 * @return 集合序列
	 */
	public float  getData() { //List<float>
		return mData;
	}


	/**
	 * 返回Key值
	 * @return Key值
	 */
	//public String getKey() {
	//	return mKey;
	//}

	@Override
    public int compareTo(FunnelData arg0) {
		 Float a = this.getData();
		 Float b = arg0.getData();
		 return Float.compare(a, b);
    }

}
