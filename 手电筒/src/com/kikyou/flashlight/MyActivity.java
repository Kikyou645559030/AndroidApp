package com.kikyou.flashlight;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 打开闪光灯和关闭闪光灯(需要权限)
 * 连按两次退出应用
 */
public class MyActivity extends Activity {

	private Camera camera;//相机
	private Camera.Parameters parameter;//相机参数
	private long mExitTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * 打开闪光灯
	 * @param v
	 */
	public void on(View v){
		//判断手机是否有闪光灯
		if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
			Toast.makeText(this, "你的手机没有闪光灯!", Toast.LENGTH_SHORT).show();
			return;
		}
		if (camera == null){
			camera= Camera.open();
			parameter = camera.getParameters();
		}
		parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameter);
	}

	/**
	 * 关闭闪光灯
	 * @param v
	 */
	public void off(View v){
		if (parameter != null && camera != null){
			parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(parameter);
		}
	}

	@Override
	public void onBackPressed() {
		long nowTime = System.currentTimeMillis();
		long diff = nowTime - mExitTime;
		if (diff >= 2000) {
			Toast.makeText(this, "再按一次退出程序哦",Toast.LENGTH_SHORT).show();
			mExitTime = nowTime;
		} else {
			//父类的这个方法也将会退出的
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		/*if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {// 如果两次按键时间间隔大于2000毫秒，则不退出
				Toast.makeText(this, "再按一次退出程序啊", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();// 更新mExitTime
			} else {
				System.exit(0);// 否则退出程序
			}
			return true;
		}*/
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();
//			camera.startPreview();//拍照完成时,Camera自动停止预览状态,需调用startPreview来拍新的照片
//			camera.stopPreview();//停止预览
		if (camera != null){
			camera.release();
			camera=null;
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
