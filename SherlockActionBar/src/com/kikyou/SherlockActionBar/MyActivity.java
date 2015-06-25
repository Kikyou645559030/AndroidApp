package com.kikyou.SherlockActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * 使用第三方工具，开发兼容低版本的ActionBar
 */
public class MyActivity extends BaseActivity {
	private static final String TAG = "MyActivity";

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * ActionBar菜单初始化时调用的方法
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * ActionBar菜单被选择时调用的方法
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.menu_title:
				Log.d(TAG,"我是多级菜单");
				break;
			case R.id.menu_download:
				Log.d(TAG,"我是下载菜单");
				break;
			case R.id.menu_upload:
				Log.d(TAG,"我是上传菜单");
				break;
			case R.id.menu_child1:
				Log.d(TAG,"我是多级菜单的子菜单1");
				break;
			case R.id.menu_child2:
				Log.d(TAG,"我是多级菜单的子菜单2");
				break;
			case R.id.menu_child3:
				Log.d(TAG,"我是多级菜单的子菜单3");
				Intent intent = new Intent(this,SecondActivity.class);
				startActivity(intent);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
