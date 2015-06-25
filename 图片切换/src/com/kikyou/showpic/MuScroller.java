package com.kikyou.showpic;

/**
 * 处理慢慢移动
 * Created by Liu_Zhichao on 2014/11/28 0028.
 */
public class MuScroller {
	private int startX;
	private int startY;
	private int distanceX;
	private int distanceY;
	private long totalTime = 500; // 总共移动持续的时间
	private long startTime;
	private int currX; // 当前的x坐标
	private int currY;// 当前y坐标
	private boolean flag;// 是否正在移动 true移动完成

	/**
	 * @param startX    开始移动前 x的坐标
	 * @param startY    开始移动前 y的坐标
	 * @param distanceX x移动的总距离
	 * @param distanceY y移动的总距离
	 */
	public void startScroll(int startX, int startY, int distanceX, int distanceY) {
		this.startX = startX;
		this.startY = startY;
		this.distanceX = distanceX;
		this.distanceY = distanceY;
		startTime = System.currentTimeMillis();
	}

	/**
	 * 应该永不停止的调用该方法 直到返回值为true
	 * @return true 代表移动完成
	 */
	public boolean finishScroll() {
		System.out.println("aaaa");
		long currentTime = System.currentTimeMillis();
		if (flag) {
			return true;
		}
		// int speedX=(int) ();
		long dTime = currentTime - startTime; // 移动的时间
		if (dTime > totalTime) {
			flag = true;
			currX = startX + distanceX;
		} else {
			int xOffset = (int) (dTime * distanceX / totalTime);// 获取到了当前x的偏移量
			currX = startX + xOffset; // 记录当前的x的位置
		}
		return false;
	}

	/**
	 * 获取到了当前的x的位置
	 *
	 * @return
	 */
	public int getCurrX() {
		return currX;
	}
}
