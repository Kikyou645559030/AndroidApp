package com.kikyou.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * 控制人物下移按钮
 * Created by Liu_Zhichao on 2014/12/11 0011.
 */
public class DownButton extends Spirit {

	private Bitmap pressBitMap;// 点击后的图片
	private boolean isClick;// 标记是否点击
	private OnClickListener onClickListener;// 点击监听

	/**
	 * 初始化精灵
	 *
	 * @param bitmap   精灵的图片
	 * @param position 精灵的初始位置
	 */
	public DownButton(Bitmap bitmap,Bitmap pressBitmap, Point position) {
		super(bitmap, position);
		this.pressBitMap = pressBitmap;
	}

	/**
	 * 绘制自身
	 * @param canvas
	 */
	@Override
	public void drawSelf(Canvas canvas) {
		if (isClick) {
			canvas.drawBitmap(pressBitMap,position.x,position.y,null);
		} else {
			super.drawSelf(canvas);
		}
	}

	/**
	 * 判断是否被点击
	 * @return 是否被点击
	 */
	public boolean isClick(Point point) {
		// 创建按钮所在的矩形区域
		Rect rect = new Rect(position.x,position.y,position.x + bitmap.getWidth(),position.y + bitmap.getHeight());
		isClick = rect.contains(point.x,point.y);// 判断矩形区域是否包含点击的坐标点
		if (isClick && onClickListener != null) {
			onClickListener.onClick();// 如果点击, 调用点击监听
		}
		return isClick;
	}

	/**
	 * 设置点击状态
	 * @param isClick 点击状态
	 */
	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}

	/**
	 * 点击事件的监听器
	 */
	public interface OnClickListener{
		public void onClick();
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}
}
