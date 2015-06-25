package com.kikyou.domain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import com.kikyou.bullet.R;

/**
 * 小男孩实体类
 * Created by Liu_Zhichao on 2014/12/11 0011.
 */
public class Boy extends Spirit {

	/**
	 * 操作的动作
	 */
	public static final int MOVE_DOWN = 10;//下移
	public static final int MOVE_UP = 20;//上移
	public static final int MOVE_LEFT = 30;//左移
	public static final int MOVE_RIGHT = 40;//右移

	/**
	 * 初始化精灵
	 *
	 * @param bitmap 精灵的图片
	 * @param position 精灵的初始位置
	 */
	public Boy(Bitmap bitmap, Point position) {
		super(bitmap, position);
	}

	/**
	 * 生产炸弹的方法
	 * @return 生产出来的炸弹
	 */
	public Bomb createBomb(Point targetPosition,Context context){
		Bomb bomb = new Bomb(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_bomb),
				new Point(position.x + 50, position.y + 50),targetPosition);
		return bomb;
	}

	/**
	 * 移动的动作
	 */
	public void move(int direction){
		switch (direction){
			case MOVE_DOWN:
				position.y += 20;
				break;
			case MOVE_UP:
				break;
			case MOVE_LEFT:
				break;
			case MOVE_RIGHT:
				break;
		}
	}
}
