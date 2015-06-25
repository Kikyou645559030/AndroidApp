package com.kikyou.bullet;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 模拟发射子弹、移动人物
 */
public class MyActivity extends Activity {

	private GameLayer layer;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		layer = new GameLayer(this);
		setContentView(layer);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		layer.handleTouch(event);
		return super.onTouchEvent(event);
	}
}
