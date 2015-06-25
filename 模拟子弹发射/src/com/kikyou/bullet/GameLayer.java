package com.kikyou.bullet;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.kikyou.domain.Bomb;
import com.kikyou.domain.Boy;
import com.kikyou.domain.DownButton;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 游戏逻辑处理类
 * Created by Liu_Zhichao on 2014/12/11 0011.
 */
public class GameLayer extends SurfaceView implements SurfaceHolder.Callback {

	private boolean isRender;//控制线程是否运行
	private Boy boy;
	//	private Bomb bomb;
//	private List<Bomb> bombs;
	private DownButton button;
	private CopyOnWriteArrayList<Bomb> bombList = new CopyOnWriteArrayList<Bomb>();

	public GameLayer(Context context) {
		super(context);
		getHolder().addCallback(this);// 增加回调
	}

	public GameLayer(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);// 增加回调
	}

	public GameLayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getHolder().addCallback(this);// 增加回调
	}

	/**
	 * 当surface创建的时候调用
	 * <p/>
	 * This is called immediately after the surface is first created.
	 * Implementations of this should start up whatever rendering code
	 * they desire.  Note that only one thread can ever draw into
	 * a {@link Surface}, so you should not draw into the Surface here
	 * if your normal rendering will be in another thread.
	 *
	 * @param holder The SurfaceHolder whose surface is being created.
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		isRender = true;
		boy = new Boy(BitmapFactory.decodeResource(getResources(), R.drawable.avatar_boy), new Point(0, 0));
		button = new DownButton(BitmapFactory.decodeResource(getResources(), R.drawable.bottom_default),
				BitmapFactory.decodeResource(getResources(), R.drawable.bottom_press), new Point(30,
				getHeight() - 100));
		button.setOnClickListener(new DownButton.OnClickListener() {
			@Override
			public void onClick() {
				boy.move(Boy.MOVE_DOWN);
			}
		});

		new RenderThread().start();// 启动绘制线程
	}

	/**
	 * 当surface改变的时候调用
	 * <p/>
	 * This is called immediately after any structural changes (format or
	 * size) have been made to the surface.  You should at this point update
	 * the imagery in the surface.  This method is always called at least
	 * once, after {@link #surfaceCreated}.
	 *
	 * @param holder The SurfaceHolder whose surface has changed.
	 * @param format The new PixelFormat of the surface.
	 * @param width  The new width of the surface.
	 * @param height The new height of the surface.
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	/**
	 * 当surface销毁的时候调用
	 * <p/>
	 * This is called immediately before a surface is being destroyed. After
	 * returning from this call, you should no longer try to access this
	 * surface.  If you have a rendering thread that directly accesses
	 * the surface, you must ensure that thread is no longer touching the
	 * Surface before returning from this function.
	 *
	 * @param holder The SurfaceHolder whose surface is being destroyed.
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		isRender = false;// 结束绘制线程
	}

	/**
	 * 渲染界面的线程
	 */
	class RenderThread extends Thread{
		@Override
		public void run() {
			while (isRender){
				drawUI();
			}
		}
	}

	/**
	 * 绘制界面
	 */
	private void drawUI() {
		Canvas canvas = getHolder().lockCanvas();//获取画布对象
		if (canvas != null){
			// 绘制一个和屏幕等大的矩形, 盖住以前的图像, 相当于清理屏幕
			Paint paint = new Paint();
			paint.setColor(Color.GRAY);
			canvas.drawRect(0,0,getWidth(),getHeight(),paint);

			boy.drawSelf(canvas);
			button.drawSelf(canvas);

			for (Bomb bomb : bombList){
				bomb.drawSelf(canvas);
				bomb.move();

				Point position = bomb.getPosition();

				// 当炸弹移除屏幕时, 回收炸弹, 避免内存溢出
				if (position.x < 0 || position.x > getWidth() || position.y < 0 || position.y > getHeight()){
					bombList.remove(bomb);
				}
			}
			getHolder().unlockCanvasAndPost(canvas);// 解锁画布并提交
		}
	}

	// 处理点击事件
	public void handleTouch(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 判断按钮是否点击
				if (!button.isClick(new Point(x, y))) {
					Bomb bomb = boy.createBomb(new Point(x, y), getContext());
					bombList.add(bomb);
				}
				break;
			case MotionEvent.ACTION_UP:
				button.setClick(false);// 手指抬起时, 将按钮图片置为默认状态
				break;
		}
	}
}
