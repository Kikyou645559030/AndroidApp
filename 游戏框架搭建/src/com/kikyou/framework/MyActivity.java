package com.kikyou.framework;

import android.app.Activity;
import android.os.Bundle;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

/**
 * cocos2d的基本元素、结构，CCDirector的生命周期，常用动作
 * CCDirector的生命周期和Activity的生命周期同步，基本相同
 */
public class MyActivity extends Activity {

	private CCGLSurfaceView surfaceView;
	private CCDirector director;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		surfaceView = new CCGLSurfaceView(this);
		setContentView(surfaceView);

		director = CCDirector.sharedDirector();
		director.attachInView(surfaceView);
		director.setDisplayFPS(true);
		director.setAnimationInterval(1 / 30);
		director.setScreenSize(480, 320);//设置屏幕分辨率(开发时分辨率)，适配用，在不同分辨率屏幕上等比例缩放
		director.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);//设置游戏屏幕方向，当前设置横屏

		CCScene scene = CCScene.node();
		scene.addChild(new GameLayer());

		director.runWithScene(scene);
	}

	/**
	 * 继续
	 */
	@Override
	protected void onResume() {
		super.onResume();
		//director控制游戏继续
		director.onResume();
	}

	/**
	 * 暂停
	 */
	@Override
	protected void onPause() {
		super.onPause();
		//director控制游戏继续
		director.onPause();
	}

	/**
	 * 销毁
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//director控制游戏结束
		director.end();
	}
}
