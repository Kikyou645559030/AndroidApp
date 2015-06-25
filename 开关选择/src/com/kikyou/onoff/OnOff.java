package com.kikyou.onoff;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 开关、true/false两种状态的控件
 * Created by Liu_Zhichao on 2014/11/27 0027.
 */
public class OnOff extends View {

	private Bitmap background;
	private Bitmap button;
	private boolean isChecked = true;//是否允许响应点击事件
	private int maxDistance;//最大滑动的距离
	private boolean flag = false;//记录控件开关的状态
	private int slideLeft;//滑块距离左边的距离
	private OnStateChangedListener listener;//对外提供的接口，状态改变时回调里面的方法
	private int startX;//触摸事件开始按下的x坐标
	private int distance;//滑动的距离

	public OnOff(Context context) {
		super(context);
		init();
	}

	/**
	 * 布局中使用的构造方法
	 * @param context
	 * @param attrs
	 */
	public OnOff(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		//加载自定义属性
		flag = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/res/com.kikyou.onoff","slide_state",false);
		int id = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/com.kikyou.onoff","slide_drawable",0);
		if (id != 0) {// 确实在布局中引用了图片
			//  就用布局文件中定义的图片
			button = BitmapFactory.decodeResource(getResources(),id);
		}
		flushState();
	}

	public OnOff(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * 对外暴露的状态改变事件的接口
	 */
	public interface OnStateChangedListener{
		void changed(boolean isOpen);
	}

	public void setListener(OnStateChangedListener listener) {
		this.listener = listener;
	}

	/**
	 * 初始化控件的方法
	 */
	private void init() {
		background = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
		button = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
		maxDistance = background.getWidth() - button.getWidth();
		//设置条目的点击事件
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//修改滑块的状态
				if (isChecked) {
					if (flag) {
						flag = false;
					} else {
						flag = true;
					}
					//刷新界面
					flushState();
				}
				isChecked = true;//保证下次默认执行点击事件
			}
		});
	}

	/**
	 * 触摸事件
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:// 按下的操作
				// 步骤1 记录开始的位置
				startX = (int) event.getX();
				break;
			case MotionEvent.ACTION_MOVE:// 手指移动调用
				// 步骤2 记录新的位置
				int newX = (int) event.getX();
				// 步骤3 记录坐标的改变
				int dX = newX - startX;// 不是移动的总距离 而是 每次滑动距离
				distance += Math.abs(dX);// 把每次移动距离全部加起来
				// 步骤4 修改位置
				slideLeft += dX;
				flushView();
				// 步骤5
				startX = newX;
				break;
			case MotionEvent.ACTION_UP:// 手指抬起的事件
				// int endX=(int) event.getX(); // 手指按下的坐标
				if (Math.abs(distance) > 20){// 如果发现滑动的总距离 大于20像素 认为处理了触摸事件
					// 不要处理点击事件
					isChecked = false;// 屏蔽了点击
					distance = 0;// 重置滑动总距离
				}
				if (!isChecked){// 如果点击事件能执行 不会执行触摸事件 如果触摸事件执行 就不会执行点击事件
					if (slideLeft > maxDistance / 2) {
						flag = true;
					} else {
						flag = false;
					}
					flushState();
				}
				break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 刷新状态
	 */
	public void flushState(){
		//坐标修正
		if (flag) {
			slideLeft = maxDistance;
		} else {
			slideLeft = 0;
		}
		if (listener  != null){
			listener.changed(flag);// 把当前的选中状态 赋值给接口
		}
		invalidate();// 刷新界面 重新调用onDraw方法
	}

	/**
	 * 刷新界面
	 */
	public void flushView(){
		//坐标修正
		if (slideLeft < 0) {
			slideLeft = 0;
		} else if (slideLeft > maxDistance){
			slideLeft = maxDistance;
		}
		invalidate();
	}

	/**
	 * 测量控件的方法 在onDraw方法之前执行，最后显示的大小就是测量时的大小，用来测量控件的宽和高
	 *
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);//测量当前控件的宽和高
		setMeasuredDimension(background.getWidth(),background.getHeight());
	}

	/**
	 * 画控件
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		Paint paint = new Paint();
		canvas.drawBitmap(background, 0, 0, null);
		canvas.drawBitmap(button, slideLeft, 0, null);
	}
}
