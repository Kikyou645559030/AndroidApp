package com.kikyou.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * 精灵实体类，提供一些公共方法供子类继承,基类
 * Created by Liu_Zhichao on 2014/12/11 0011.
 */
public class Spirit {

	public Bitmap bitmap;// 默认图片
	public Point position;// 显示位置

	/**
	 * 初始化精灵
	 * @param bitmap 精灵的图片
	 * @param position 精灵的初始位置
	 */
	public Spirit(Bitmap bitmap,Point position) {
		this.bitmap = bitmap;
		this.position = position;
	}

	/**
	 * 绘制自身
	 * @param canvas 画布
	 */
	public void drawSelf(Canvas canvas){
		if (bitmap != null){
			canvas.drawBitmap(bitmap,position.x,position.y,null);
		}
	}
}
