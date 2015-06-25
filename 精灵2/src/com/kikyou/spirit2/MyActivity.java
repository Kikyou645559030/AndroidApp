package com.kikyou.spirit2;

import android.app.Activity;
import android.os.Bundle;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MyActivity extends Activity {

	private CCGLSurfaceView surfaceView;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		surfaceView = new CCGLSurfaceView(this);
		setContentView(surfaceView);
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(surfaceView);
		director.setDisplayFPS(true);
		director.setAnimationInterval(1/30);

		CCScene scene = CCScene.node();//背景层
		GameLayer gameLayer = new GameLayer();
		scene.addChild(gameLayer);
		director.runWithScene(scene);
	}
}
