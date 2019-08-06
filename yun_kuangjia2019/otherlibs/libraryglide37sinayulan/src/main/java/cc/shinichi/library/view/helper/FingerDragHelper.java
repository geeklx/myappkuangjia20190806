package cc.shinichi.library.view.helper;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
//import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.R;
import cc.shinichi.library.view.nine.ViewHelper;

/**
 * @author 工藤
 * @email gougou@16fan.com
 * com.fan16.cn.widget.ninegrid
 * create at 2018/10/19  11:22
 * description:辅助下拉关闭图片
 */
public class FingerDragHelper extends LinearLayout {

	private static final String TAG = FingerDragHelper.class.getSimpleName();

	private SubsamplingScaleImageViewDragClose imageViewDragClose;

	private float mDownY;
	private float mTranslationY;
	private float mLastTranslationY;
	private static int MAX_TRANSLATE_Y = 500;
	private final static int MAX_EXIT_Y = 300;
	private final static long DURATION = 150;
	private boolean isAnimate = false;
	private int fadeIn = R.anim.fade_in_150;
	private int fadeOut = R.anim.fade_out_150;
	private int mTouchslop;
	private onAlphaChangedListener mOnAlphaChangedListener;

	public FingerDragHelper(Context context) {
		this(context, null);
	}

	public FingerDragHelper(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FingerDragHelper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews();
	}

	private void initViews() {
		mTouchslop = ViewConfiguration.getTouchSlop();
	}

	@Override protected void onFinishInflate() {
		super.onFinishInflate();
		imageViewDragClose = (SubsamplingScaleImageViewDragClose) getChildAt(0);
	}

	@Override public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean isIntercept = false;
		int action = ev.getAction() & ev.getActionMasked();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mDownY = ev.getRawY();
			case MotionEvent.ACTION_MOVE:
				if (null != imageViewDragClose && ImagePreview.getInstance().isEnableDragClose()) {
					isIntercept = (imageViewDragClose.getScale() <= (imageViewDragClose.getMinScale() + 0.001F))
						&& (imageViewDragClose.getMaxTouchCount() == 0
						|| imageViewDragClose.getMaxTouchCount() == 1)
						&& Math.abs(ev.getRawY() - mDownY) > 2 * mTouchslop
						&& imageViewDragClose.atYEdge;
				}
				break;
			default:
				break;
		}
		return isIntercept;
	}

	@Override public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction() & event.getActionMasked();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mDownY = event.getRawY();
			case MotionEvent.ACTION_MOVE:
				if (null != imageViewDragClose && ImagePreview.getInstance().isEnableDragClose()) {
					onOneFingerPanActionMove(event);
				}
				break;
			case MotionEvent.ACTION_UP:
				onActionUp();
				break;
			default:
				break;
		}
		return true;
	}

	private void onOneFingerPanActionMove(MotionEvent event) {
		float moveY = event.getRawY();
		mTranslationY = moveY - mDownY + mLastTranslationY;
		//触发回调 根据距离处理其他控件的透明度 显示或者隐藏角标，文字信息等
		if (null != mOnAlphaChangedListener) {
			mOnAlphaChangedListener.onTranslationYChanged(event, mTranslationY);
		}
		ViewHelper.setScrollY(this, -(int) mTranslationY);
	}

	private void onActionUp() {
		if (Math.abs(mTranslationY) > MAX_EXIT_Y) {
			exitWithTranslation(mTranslationY);
		} else {
			resetCallBackAnimation();
		}
	}

	public void exitWithTranslation(float currentY) {
		if (currentY > 0) {
			ValueAnimator animDown = ValueAnimator.ofFloat(mTranslationY, getHeight());
			animDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override public void onAnimationUpdate(ValueAnimator animation) {
					float fraction = (float) animation.getAnimatedValue();
					ViewHelper.setScrollY(FingerDragHelper.this, -(int) fraction);
				}
			});
			animDown.addListener(new Animator.AnimatorListener() {
				@Override public void onAnimationStart(Animator animation) {

				}

				@Override public void onAnimationEnd(Animator animation) {
					reset();
					Activity activity = ((Activity) getContext());
					activity.finish();
					activity.overridePendingTransition(fadeIn, fadeOut);
				}

				@Override public void onAnimationCancel(Animator animation) {

				}

				@Override public void onAnimationRepeat(Animator animation) {

				}
			});
			animDown.setDuration(DURATION);
			animDown.setInterpolator(new LinearInterpolator());
			animDown.start();
		} else {
			ValueAnimator animUp = ValueAnimator.ofFloat(mTranslationY, -getHeight());
			animUp.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				@Override public void onAnimationUpdate(ValueAnimator animation) {
					float fraction = (float) animation.getAnimatedValue();
					ViewHelper.setScrollY(FingerDragHelper.this, -(int) fraction);
				}
			});
			animUp.addListener(new Animator.AnimatorListener() {
				@Override public void onAnimationStart(Animator animation) {

				}

				@Override public void onAnimationEnd(Animator animation) {
					reset();
					((Activity) getContext()).finish();
					((Activity) getContext()).overridePendingTransition(fadeIn, fadeOut);
				}

				@Override public void onAnimationCancel(Animator animation) {

				}

				@Override public void onAnimationRepeat(Animator animation) {

				}
			});
			animUp.setDuration(DURATION);
			animUp.setInterpolator(new LinearInterpolator());
			animUp.start();
		}
	}

	private void resetCallBackAnimation() {
		ValueAnimator animatorY = ValueAnimator.ofFloat(mTranslationY, 0);
		animatorY.setDuration(DURATION);
		animatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
				if (isAnimate) {
					mTranslationY = (float) valueAnimator.getAnimatedValue();
					mLastTranslationY = mTranslationY;
					ViewHelper.setScrollY(FingerDragHelper.this, -(int) mTranslationY);
				}
			}
		});
		animatorY.addListener(new Animator.AnimatorListener() {
			@Override public void onAnimationStart(Animator animation) {
				isAnimate = true;
			}

			@Override public void onAnimationEnd(Animator animation) {
				if (isAnimate) {
					mTranslationY = 0;
					invalidate();
					reset();
				}
				isAnimate = false;
			}

			@Override public void onAnimationCancel(Animator animation) {

			}

			@Override public void onAnimationRepeat(Animator animation) {

			}
		});
		animatorY.start();
	}

	public interface onAlphaChangedListener {

		void onTranslationYChanged(MotionEvent event, float translationY);
	}

	/**
	 * 暴露的回调方法（可根据位移距离或者alpha来改变主UI控件的透明度等
	 * @param alphaChangeListener
	 */
	public void setOnAlphaChangeListener(onAlphaChangedListener alphaChangeListener) {
		mOnAlphaChangedListener = alphaChangeListener;
	}

	private void reset() {
		if (null != mOnAlphaChangedListener) {
			mOnAlphaChangedListener.onTranslationYChanged(null, mTranslationY);
		}
	}
}