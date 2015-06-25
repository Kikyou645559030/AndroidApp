package com.kikyou.domain;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * 炸弹实体类
 * Created by Liu_Zhichao on 2014/12/11 0011.
 */
public class Bomb extends Spirit {

	private int speed = 5;
	private int dx;// x坐标变化
	private int dy;// y坐标变化

	/**
	 * 初始化精灵
	 *  @param bitmap 精灵的图片
	 * @param position 精灵的初始位置
	 * @param targetPosition
	 */
	public Bomb(Bitmap bitmap, Point position, Point targetPosition) {
		super(bitmap, position);
		int x = targetPosition.x - position.x;
		int y = targetPosition.y - position.y;
		int d = (int) Math.sqrt(x * x + y * y);

		dx = speed * x / d;
		dy = speed * y / d;
	}

	/**
	 * 移动的方法
	 */
	public void move(){
		position.x += dx;
		position.y += dy;
	}

	/**
	 * 返回当前炸弹的位置
	 * @return 坐标点
	 */
	public Point getPosition(){
		return position;
	}
}
