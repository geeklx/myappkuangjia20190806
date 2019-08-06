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
 * v1.3 2014-8-30 xcl 增加平滑区域面积图
 */
package com.example.slbappcomm.xclchart.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;

import com.example.slbappcomm.xclchart.common.CurveHelper;
import com.example.slbappcomm.xclchart.common.DrawHelper;
import com.example.slbappcomm.xclchart.common.PointHelper;
import com.example.slbappcomm.xclchart.renderer.LnChart;
import com.example.slbappcomm.xclchart.renderer.XEnum;
import com.example.slbappcomm.xclchart.renderer.line.DotInfo;
import com.example.slbappcomm.xclchart.renderer.line.PlotDot;
import com.example.slbappcomm.xclchart.renderer.line.PlotDotRender;
import com.example.slbappcomm.xclchart.renderer.line.PlotLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AreaChart
 * @Description 面积图基类
 * @author XiongChuanLiang<br/>
 *         (xcl_168@aliyun.com)
 */

public class AreaChart extends LnChart {

	private static final String TAG = "AreaChart";

	// 画点分类的画笔
	protected Paint mPaintAreaFill = null;

	// 数据源
	protected List<AreaData> mDataSet;

	// 透明度
	private int mAreaAlpha = 100;

	// path area
	private List<PointF> mLstPathPoints = new ArrayList<PointF>();
	private Path mPathArea = null;
	private PointF[] mBezierControls = new PointF[2];

	// key
	private List<LnData> mLstKey = new ArrayList<LnData>();

	// line
	private List<PointF> mLstPoints = new ArrayList<PointF>();

	// dots
	private List<DotInfo> mLstDotInfo = new ArrayList<DotInfo>();

	// 平滑曲线
	private XEnum.CrurveLineStyle mCrurveLineStyle = XEnum.CrurveLineStyle.BEZIERCURVE;

	private final int Y_MIN = 0;
	private final int Y_MAX = 1;

	public AreaChart() {
		categoryAxisDefaultSetting();
		dataAxisDefaultSetting();
	}

	@Override
	public XEnum.ChartType getType() {
		return XEnum.ChartType.AREA;
	}
		
	public Paint getAreaFillPaint(){
		if (null == mPaintAreaFill) {
			mPaintAreaFill = new Paint();
			mPaintAreaFill.setStyle(Style.FILL);
			mPaintAreaFill.setAntiAlias(true);
			mPaintAreaFill.setColor(Color.rgb(73, 172, 72));
		}		
		return mPaintAreaFill;
	}

	@Override
	protected void categoryAxisDefaultSetting() {
		if (null != categoryAxis)
			categoryAxis.setHorizontalTickAlign(Align.CENTER);
	}

	@Override
	protected void dataAxisDefaultSetting() {
		if (null != dataAxis)
			dataAxis.setHorizontalTickAlign(Align.LEFT);
	}

	/**
	 * 分类轴的数据源
	 * 
	 * @param categories
	 *            分类集
	 */
	public void setCategories(List<String> categories) {
		if (null != categoryAxis)
			categoryAxis.setDataBuilding(categories);
	}

	/**
	 * 设置数据轴的数据源
	 * 
	 * @param dataset
	 *            数据源
	 */
	public void setDataSource(List<AreaData> dataset) {
		this.mDataSet = dataset;
	}

	/**
	 * 返回数据轴的数据源
	 * @return 数据源
	 */
	public List<AreaData> getDataSource()
	{
		return this.mDataSet;
	}
	
	/**
	 * 设置透明度,默认为100
	 * 
	 * @param alpha
	 *            透明度
	 */
	public void setAreaAlpha(int alpha) {
		mAreaAlpha = alpha;
	}

	/**
	 * 设置曲线显示风格:直线(NORMAL)或平滑曲线(BEZIERCURVE)
	 * 
	 * @param style
	 */
	public void setCrurveLineStyle(XEnum.CrurveLineStyle style) {
		mCrurveLineStyle = style;
	}

	/**
	 * 返回曲线显示风格
	 * 
	 * @return 显示风格
	 */
	public XEnum.CrurveLineStyle getCrurveLineStyle() {
		return mCrurveLineStyle;
	}

	private boolean calcAllPoints(AreaData bd, List<DotInfo> lstDotInfo,
                                  List<PointF> lstPoints, List<PointF> lstPathPoints) {

		if (null == bd) {
			Log.w(TAG, "传入的数据序列参数为空.");
			return false;
		}
		// 数据源
		List<Double> chartValues = bd.getLinePoint();
		if (null == chartValues) {
			Log.w(TAG, "线数据集合为空.");
			return false;
		}

		float lineStartX = plotArea.getLeft(), lineStartY = plotArea
				.getBottom();
		float lineStopX = 0.0f, lineStopY = 0.0f;
		float currLablesSteps = div(getPlotScreenWidth(), (categoryAxis
				.getDataSet().size() - 1));

		int count = chartValues.size();
		if (count <= 0)
			return false;

		for (int i = 0; i < count; i++) {
			double bv = chartValues.get(i);

			// 首尾为0,path不能闭合，改成 0.001都可以闭合?
			lineStopX = add(plotArea.getLeft(), mul(i , currLablesSteps));
			lineStopY = getVPValPosition(bv);

			if (0 == i) {
				lineStartX = lineStopX;
				lineStartY = lineStopY;

				if (2 < count) {
					if (Double.compare(bv, dataAxis.getAxisMin()) != 0)
						lstPathPoints.add(new PointF(plotArea.getLeft(),
								plotArea.getBottom()));
				}

				lstPathPoints.add(new PointF(lineStartX, lineStartY));
				lstPoints.add(new PointF(lineStartX, lineStartY));
			}

			// path
			lstPathPoints.add(new PointF(lineStopX, lineStopY));

			// line
			lstPoints.add(new PointF(lineStopX, lineStopY));

			// dot
			lstDotInfo.add(new DotInfo(bv, lineStopX, lineStopY));

			lineStartX = lineStopX;
			lineStartY = lineStopY;
		}

		if (count > 2) {
			lstPathPoints.add(new PointF(lineStartX, lineStartY));

			if (Double.compare(chartValues.get(count - 1),
					dataAxis.getAxisMin()) != 0) {
				lstPathPoints.add(new PointF(lineStartX, plotArea.getBottom()));
			}
		}
		return true;
	}

	private boolean renderBezierArea(Canvas canvas, Paint paintAreaFill,
                                     Path bezierPath, AreaData areaData, List<PointF> lstPathPoints) {
		int count = lstPathPoints.size();

		if (count < 3)
			return false; // 没有或仅一个点就不需要了

		// 设置当前填充色
		paintAreaFill.setColor(areaData.getAreaFillColor());

		// 仅两点
		if (count == 3) {
			if (null == bezierPath)
				bezierPath = new Path();

			bezierPath.moveTo(lstPathPoints.get(0).x, plotArea.getBottom());
			bezierPath.lineTo(lstPathPoints.get(0).x, lstPathPoints.get(0).y);

			PointF ctl3 = PointHelper.percent(lstPathPoints.get(1), 0.5f,
					lstPathPoints.get(2), 0.8f);
			bezierPath.quadTo(ctl3.x, ctl3.y, lstPathPoints.get(2).x,
					lstPathPoints.get(2).y);

			// canvas.drawCircle(ctl3.x, ctl3.y, 10.f, paintAreaFill); //显示控制点

			bezierPath.lineTo(lstPathPoints.get(2).x, plotArea.getBottom());
			bezierPath.close();

			if (areaData.getApplayGradient()) {
				LinearGradient linearGradient;
				if (areaData.getGradientDirection() == XEnum.Direction.VERTICAL) {
					float lineMaxY = getLineMaxMinY(Y_MAX, lstPathPoints);
					linearGradient = new LinearGradient(0,
							0,
							0,
							plotArea.getBottom() - lineMaxY,// 2 * h,
							// plotArea.getLeft(),plotArea.getBottom(),
							// lstPathPoints.get(count -1).x,lineMinY,
							areaData.getAreaBeginColor(),
							areaData.getAreaEndColor(),
							areaData.getGradientMode());
				} else {
					float lineMinY = getLineMaxMinY(Y_MIN, lstPathPoints);
					linearGradient = new LinearGradient(plotArea.getLeft(),
							plotArea.getBottom(), lstPathPoints.get(2).x,
							lineMinY, areaData.getAreaBeginColor(),
							areaData.getAreaEndColor(),
							areaData.getGradientMode());
				}
				paintAreaFill.setShader(linearGradient);

			} else {
				paintAreaFill.setShader(null);
			}
			canvas.drawPath(bezierPath, paintAreaFill);
			bezierPath.reset();
			return true;
		}

		// 透明度
		paintAreaFill.setAlpha(this.mAreaAlpha);

		// start point
		bezierPath.moveTo(plotArea.getLeft(), plotArea.getBottom());

		float axisMinValue = plotArea.getBottom();

		for (int i = 0; i < count; i++) {
			if (i < 3)
				continue;

			// 连续两个值都为0,控制点有可能会显示在轴以下，则此种情况下，将其处理为直线
			if (lstPathPoints.get(i - 1).y >= axisMinValue
					&& lstPathPoints.get(i).y >= axisMinValue) {
				// 如果最后两点为0时此处调用了两次，最后一次跳过不做处理,原因是数组的最后一个点的y值必定为0
				if (i == count - 1) {
					continue;
				}
				
				if (null == bezierPath) {
					bezierPath = new Path();
					bezierPath.moveTo(lstPathPoints.get(i - 2).x,
							lstPathPoints.get(i - 2).y);
				} else {
					bezierPath.lineTo(lstPathPoints.get(i - 2).x,
							lstPathPoints.get(i - 2).y);
				}
				// change by chenqiang
				if (lstPathPoints.get(i - 2).y>=axisMinValue) {//连续3个点为0
					bezierPath.moveTo(lstPathPoints.get(i - 1).x,
							lstPathPoints.get(i - 1).y);
				}else{
					CurveHelper.curve3(lstPathPoints.get(i - 2),
							lstPathPoints.get(i - 1), lstPathPoints.get(i - 3),
							lstPathPoints.get(i), mBezierControls);
					bezierPath.quadTo(mBezierControls[0].x, mBezierControls[0].y,
							lstPathPoints.get(i - 1).x, lstPathPoints.get(i - 1).y);
				}
				bezierPath.close();

				if (areaData.getApplayGradient()) {
					LinearGradient linearGradient;
					if (areaData.getGradientDirection() == XEnum.Direction.VERTICAL) {
						float lineMaxY = getLineMaxMinY(Y_MAX, lstPathPoints);
						linearGradient = new LinearGradient(0, 0, 0, lineMaxY,
								areaData.getAreaBeginColor(),
								areaData.getAreaEndColor(),
								areaData.getGradientMode());
					} else {
						float lineMinY = getLineMaxMinY(Y_MIN, lstPathPoints);
						linearGradient = new LinearGradient(plotArea.getLeft(),
								plotArea.getBottom(),
								lstPathPoints.get(i - 1).x, lineMinY,
								areaData.getAreaBeginColor(),
								areaData.getAreaEndColor(),
								areaData.getGradientMode());
					}
					paintAreaFill.setShader(linearGradient);

				} else {
					paintAreaFill.setShader(null);
				}

				canvas.drawPath(bezierPath, paintAreaFill);
				bezierPath.reset();

				bezierPath.moveTo(lstPathPoints.get(i).x,
						lstPathPoints.get(i).y);
				continue;
			}

			CurveHelper.curve3(lstPathPoints.get(i - 2),
					lstPathPoints.get(i - 1), lstPathPoints.get(i - 3),
					lstPathPoints.get(i), mBezierControls);

			// change by chenqiang
			bezierCurvePathAxisMinValue(bezierPath, lstPathPoints.get(i - 2),
					lstPathPoints.get(i - 1), mBezierControls);
		}

		// 最后两点间的区域
		PointF stop = lstPathPoints.get(count - 1);// 通过calcAllPoints函数，stop的y值一定是0
		if (lstPathPoints.get(count - 2).y >= axisMinValue) {// 最后一点是0
			// change by chenqiang
			CurveHelper.curve3(lstPathPoints.get(count - 3), stop,
					lstPathPoints.get(count - 4), stop, mBezierControls);
			bezierCurvePathAxisMinValue(bezierPath, lstPathPoints.get(count - 3),
					stop, mBezierControls);
		} else {
			CurveHelper.curve3(lstPathPoints.get(count - 2), stop,
					lstPathPoints.get(count - 3), stop, mBezierControls);
			// change by chenqiang
			bezierCurvePathAxisMinValue(bezierPath, lstPathPoints.get(count - 2),
					lstPathPoints.get(count - 1), mBezierControls);
		}

		bezierPath.close();

		if (areaData.getApplayGradient()) {

			LinearGradient linearGradient;
			if (areaData.getGradientDirection() == XEnum.Direction.VERTICAL) {
				float lineMaxY = getLineMaxMinY(Y_MAX, lstPathPoints);
				linearGradient = new LinearGradient(0, 0, 0, lineMaxY,
						areaData.getAreaBeginColor(),
						areaData.getAreaEndColor(), areaData.getGradientMode());
			} else {
				float lineMinY = getLineMaxMinY(Y_MIN, lstPathPoints);
				linearGradient = new LinearGradient(plotArea.getLeft(),
						plotArea.getBottom(), stop.x, lineMinY,
						areaData.getAreaBeginColor(),
						areaData.getAreaEndColor(), areaData.getGradientMode());
			}
			paintAreaFill.setShader(linearGradient);

		} else {
			paintAreaFill.setShader(null);
		}

		canvas.drawPath(bezierPath, paintAreaFill);
		bezierPath.reset();

		return true;
	}

	private boolean renderArea(Canvas canvas, Paint paintAreaFill,
                               Path pathArea, AreaData areaData, List<PointF> lstPathPoints) {
		int count = lstPathPoints.size();
		if (count < 3)
			return false; // 没有或仅一个点就不需要了
		// 设置当前填充色
		paintAreaFill.setColor(areaData.getAreaFillColor());

		if (areaData.getApplayGradient()) {
			LinearGradient linearGradient;
			if (areaData.getGradientDirection() == XEnum.Direction.VERTICAL) {
				float lineMaxY = getLineMaxMinY(Y_MAX, lstPathPoints);
				linearGradient = new LinearGradient(0, 0, 0, lineMaxY,
						areaData.getAreaBeginColor(),
						areaData.getAreaEndColor(), areaData.getGradientMode());
			} else {
				float lineMinY = getLineMaxMinY(Y_MIN, lstPathPoints);
				linearGradient = new LinearGradient(plotArea.getLeft(),
						plotArea.getBottom(), lstPathPoints.get(count - 1).x,
						lineMinY, areaData.getAreaBeginColor(),
						areaData.getAreaEndColor(), areaData.getGradientMode());
			}
			paintAreaFill.setShader(linearGradient);
		} else {
			paintAreaFill.setShader(null);
		}
		// 透明度
		paintAreaFill.setAlpha(this.mAreaAlpha);

		// 仅两点
		if (count == 3) {
			if (null == pathArea)
				pathArea = new Path();

			pathArea.moveTo(lstPathPoints.get(0).x, plotArea.getBottom());
			pathArea.lineTo(lstPathPoints.get(0).x, lstPathPoints.get(0).y);
			pathArea.lineTo(lstPathPoints.get(1).x, lstPathPoints.get(1).y);
			pathArea.lineTo(lstPathPoints.get(2).x, lstPathPoints.get(2).y);
			pathArea.lineTo(lstPathPoints.get(2).x, plotArea.getBottom());
			pathArea.close();
			// 绘制area
			canvas.drawPath(pathArea, paintAreaFill);
			pathArea.reset();
			return true;
		}

		for (int i = 0; i < count; i++) {
			PointF point = lstPathPoints.get(i);
			if (0 == i) {
				pathArea.moveTo(point.x, point.y);
			} else {
				pathArea.lineTo(point.x, point.y);
			}
		}
		pathArea.close();

		// 绘制area
		canvas.drawPath(pathArea, paintAreaFill);
		pathArea.reset();
		return true;
	}

	private boolean renderLine(Canvas canvas, AreaData areaData,
			List<PointF> lstPoints) {
		int count = lstPoints.size();
		for (int i = 0; i < count; i++) {
			if (0 == i)
				continue;
			PointF pointStart = lstPoints.get(i - 1);
			PointF pointStop = lstPoints.get(i);

			DrawHelper.getInstance().drawLine(areaData.getLineStyle(),
					pointStart.x, pointStart.y, pointStop.x, pointStop.y,
					canvas, areaData.getLinePaint());
		}

		return true;
	}

	private boolean renderBezierCurveLine(Canvas canvas, Path bezierPath,
                                          AreaData areaData, List<PointF> lstPoints) {
		renderBezierCurveLine(canvas, areaData.getLinePaint(), bezierPath,
				lstPoints);
		return true;
	}

	/**
	 * 绘制区域
	 * 
	 * @param bd
	 *            数据序列
	 * @params type
	 *            绘制类型
	 * @params alpha
	 *            透明度
	 */
	private boolean renderDotAndLabel(Canvas canvas, AreaData bd, int dataID,
                                      List<DotInfo> lstDotInfo) {
		float itemAngle = bd.getItemLabelRotateAngle();
		PlotLine pLine = bd.getPlotLine();
		if (pLine.getDotStyle().equals(XEnum.DotStyle.HIDE) == true
				&& bd.getLabelVisible() == false) {
			return true;
		}

		PlotDot pDot = pLine.getPlotDot();
		float radius = pDot.getDotRadius();

		int count = lstDotInfo.size();
		for (int i = 0; i < count; i++) {
			DotInfo dotInfo = lstDotInfo.get(i);
			if (!pLine.getDotStyle().equals(XEnum.DotStyle.HIDE)) {
				PlotDotRender.getInstance().renderDot(canvas, pDot, dotInfo.mX,
						dotInfo.mY, pLine.getDotPaint());
				savePointRecord(dataID, i, dotInfo.mX + mMoveX, dotInfo.mY
						+ mMoveY, dotInfo.mX - radius + mMoveX, dotInfo.mY
						- radius + mMoveY, dotInfo.mX + radius + mMoveX,
						dotInfo.mY + radius + mMoveY);

			}

			// 显示批注形状
			drawAnchor(getAnchorDataPoint(), dataID, i, canvas, dotInfo.mX,
					dotInfo.mY,radius);

			if (bd.getLabelVisible()) {
				bd.getLabelOptions().drawLabel(canvas, pLine.getDotLabelPaint(),
						getFormatterItemLabel(dotInfo.mValue), dotInfo.mX,
						dotInfo.mY, itemAngle, bd.getLineColor());
			}
		}
		return true;
	}

	private float getLineMaxMinY(int type, List<PointF> lstPathPoints) {
		// 渲染高度
		float lineMaxY = 0.0f;

		float lineMinY = 0.0f;
		int count = lstPathPoints.size();

		for (int i = 0; i < count; i++) {
			if (Y_MAX == type) {
				if (lineMaxY < lstPathPoints.get(i).y)
					lineMaxY = lstPathPoints.get(i).y;
			} else if (Y_MIN == type) {
				if (0 == i) {
					lineMinY = lstPathPoints.get(0).y;
				} else {
					if (lineMinY > lstPathPoints.get(i).y)
						lineMinY = lstPathPoints.get(i).y;
				}
			}
		}
		if (Y_MAX == type) {
			return lineMaxY;
		} else { // if(Y_MIN == type){
			return lineMinY;
		}
	}

	private boolean renderVerticalPlot(Canvas canvas) {
		if (null == mDataSet) {
			Log.e(TAG, "数据源为空.");
			return false;
		}

		//this.initPaint();
		if (null == mPathArea)
			mPathArea = new Path();

		// 透明度。其取值范围是0---255,数值越小，越透明，颜色上表现越淡
		// mPaintAreaFill.setAlpha( mAreaAlpha );

		// 开始处 X 轴 即分类轴
		int count = mDataSet.size();
		for (int i = 0; i < count; i++) {
			AreaData areaData = mDataSet.get(i);

			calcAllPoints(areaData, mLstDotInfo, mLstPoints, mLstPathPoints);

			switch (getCrurveLineStyle()) {
			case BEZIERCURVE:
				renderBezierArea(canvas, getAreaFillPaint(), mPathArea, areaData,
						mLstPathPoints);
				renderBezierCurveLine(canvas, mPathArea, areaData, mLstPoints);
				break;
			case BEELINE:
				renderArea(canvas, getAreaFillPaint(), mPathArea, areaData,
						mLstPathPoints);
				renderLine(canvas, areaData, mLstPoints);
				break;
			default:
				Log.e(TAG, "未知的枚举类型.");
				continue;
			}
			renderDotAndLabel(canvas, areaData, i, mLstDotInfo);
			mLstKey.add(areaData);

			mLstDotInfo.clear();
			mLstPoints.clear();
			mLstPathPoints.clear();
		}
		return true;
	}

	// ///////////////////////////////////////////
	@Override
	protected void drawClipPlot(Canvas canvas) {
		if (renderVerticalPlot(canvas) == true) {
			// 画横向定制线
			if (null != mCustomLine) {
				mCustomLine.setVerticalPlot(dataAxis, plotArea,
						getAxisScreenHeight());
				mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
			}
		}
	}

	@Override
	protected void drawClipLegend(Canvas canvas){
		plotLegend.renderLineKey(canvas, mLstKey);
		mLstKey.clear();
	}
	// ///////////////////////////////////////////

}
