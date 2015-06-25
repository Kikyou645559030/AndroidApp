package com.kikyou.spirit;

import android.app.Activity;
import android.os.Bundle;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

/**
 * 游戏进阶-向量计算
 * 向量的加减
 * 向量的乘除 除法就是乘以小数
 * 单位向量 就是距离圆点的距离，使用勾股定理计算单位向量
 *
 * 游戏的初始化和布局
 * 1.需要一个CCGLSurfaceView控件，类似于画布、背景，用来接收cocos2d的画图、渲染
 * 2.需要一个导演CCDirector对象，用来添加或者是设置游戏所使用的背景，就是上面的控件
 *    设置是否显示fps值，设置渲染一帧所需的时间。
 * 3.设置场景CCScene对象
 * 4.设置布景层GameLayer对象，布景层也是可以移动的
 * 5.将布景层添加到场景中
 * 6.让导演运行游戏场景
 */
public class MyActivity extends Activity {

	private CCGLSurfaceView view;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new CCGLSurfaceView(this);
		setContentView(view);
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(view);
		director.setDisplayFPS(true);
		director.setAnimationInterval(1/30);
		CCScene scene = CCScene.node();
		GameLayer gameLayer = new GameLayer();
		scene.addChild(gameLayer);
		director.runWithScene(scene);
	}
}
