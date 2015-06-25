package com.kikyou.simple;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MyActivity extends Activity {

	private VideoView video;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		video = (VideoView) findViewById(R.id.vv_player);
		Toast.makeText(this,Environment.getExternalStorageDirectory().getAbsolutePath(),Toast.LENGTH_SHORT).show();
		video.setVideoPath(Environment.getExternalStorageDirectory().getPath() + "/01复习.avi");
		/**
		 *
		 */
		video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				video.start();
			}
		});
		video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				Toast.makeText(getApplicationContext(),"电影播放完毕",Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		/*在17版本才能用、监听视频播放卡的事件
		video.setOnInfoListener(new MediaPlayer.OnInfoListener() {
			@Override
			public boolean onInfo(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});*/
		video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Toast.makeText(getApplicationContext(),"电影播放出现错误",Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		video.setMediaController(new MediaController(this));
	}
}
