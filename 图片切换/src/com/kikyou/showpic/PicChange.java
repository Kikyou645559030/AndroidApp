package com.kikyou.showpic;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义图片切换的控件
 * Created by Liu_Zhichao on 2014/11/28 0028.
 */
public class PicChange extends ViewGroup {

	private GestureDetector detector;
	private Scroller scroller;
	private int index = 0;
	private int startX;

	private OnPageChangedListener listener;

	public PicChange(Context context) {
		super(context);
		initView();
	}

	public PicChange(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public PicChange(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public interface OnPageChangedListener {
		void onChange(int index);
	}

	public void setListener(OnPageChangedListener listener) {
		this.listener = listener;
	}

	/**
	 * 中断事件传递
	 *
	 * @param ev 触发的事件
	 * @return
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int startX = 0;
		int startY = 0;
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				detector.onTouchEvent(ev);// 避免了中断事件 导致没有处理按下的操作
				startX = (int) ev.getX();
				startY = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:// 手指移动的事件
				int newX = (int) ev.getX();
				int newY = (int) ev.getY();
				int dX = newX - startX;// x轴的偏移量
				int dY = newY - startY;// y轴的偏移量
				if (Math.abs(dX) > Math.abs(dY)) {
					return true;
				}
				break;
		}
		// 如果是上下滑动 屏幕的时候 不中断事件
		//如果是左右滑动 中断事件
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * onMeasure  会在onLayout 之前调用
	 * 测量控件 会多次调用测量
	 * 要求父容器一定要测量子容器,如果不测量子容器 子容器宽和高都是0
	 * 子容器由于挂载到父容器可以正常显示,但是 孙子就不能显示
	 * 父容器先知道自己大小 还是子容器先知道,这个是不确定的
	 *
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		MeasureSpec.getMode(widthMeasureSpec);
		MeasureSpec.getSize(widthMeasureSpec);// 真正获取到了尺寸
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			view.measure(widthMeasureSpec, heightMeasureSpec);// 对每个孩子都测量
		}
	}

	/**
	 * 初始化手势识别器
	 */
	private void initView() {
		detector = new GestureDetector(getContext(), new MySimpleOnGestureListener());
	}

	private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
		/**
		 * 滑动事件
		 *
		 * @param e1
		 * @param e2
		 * @param distanceX x轴滑动的距离
		 * @param distanceY y轴滑动的距离
		 * @return
		 */
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			scrollBy((int) distanceX, 0);// 让viewGroup 移动多少距离
			//scrollBy  会自动调用invalidate() 该方法
			//invalidate();   自动调用onDraw
			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	/**
	 * 分配控件的布局
	 *
	 * @param changed
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			view.layout(getWidth() * i, 0, getWidth() + getWidth() * i, getHeight());
		}
	}

	/**
	 * 计算滑动
	 */
	@Override
	public void computeScroll() {
		if (scroller != null) {
			if (scroller.computeScrollOffset()) {
				scrollTo(scroller.getCurrX(), 0);// 由于 scrollTo这个方法一执行 会调用invalidate();
				invalidate();
			}
		}
		super.computeScroll();
	}

	/**
	 * 绘制控件
	 *
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	/**
	 * 中断事件传递
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		detector.onTouchEvent(event);// 把手势识别器注册到触摸事件中
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 当手指按下的时候 记录开始的坐标
				startX = (int) event.getX();
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			case MotionEvent.ACTION_UP:
				// 当手指抬起的时候 记录结束的坐标
				int endX = (int) event.getX();
				if ((startX - endX) > getWidth() / 2) {
					//进入下一个界面
					index++;
				} else if ((endX - startX) > getWidth() / 2) {
					// 进入上一个界面
					index--;
				}
				moveToIndex();
				break;
		}
		return true;
	}

	/**
	 * 移动到指定下标的图片
	 *
	 * @param index 下标位置
	 */
	public void moveToIndex(int index) {
		this.index = index;
		moveToIndex();
	}

	/**
	 * 移动到指定下标的图片
	 */
	private void moveToIndex() {
		if (index < 0) {
			index = 0;
		}
		if (index >= getChildCount()) {
			index = getChildCount() - 1;
		}
		//当界面切换的时候，回调接口对应实现类的方法，把当前的界面的位置给传递给回调方法
		if (listener != null) {
			listener.onChange(index);
		}
		//getScrollX()  返回当前x的位置
		scroller = new Scroller(getContext());
		scroller.startScroll(getScrollX(), getScrollY(), (int) (getWidth() * index - getScrollX()), 0);
		invalidate();//  computeScroll   onDraw
	}

	/**
	 * 分发事件
	 *
	 * @param ev
	 * @return
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
}
