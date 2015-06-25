package com.kikyou.SherlockActionBar;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * 使用第三方工具，开发兼容低版本的ActionBar
 * Created by Liu_Zhichao on 2014/12/20 0020.
 */
public class SecondActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		ActionBar actionBar = getSupportActionBar();
		//设置标题
		actionBar.setTitle("我叫MT");
		//设置是否显示返回按钮
		actionBar.setDisplayHomeAsUpEnabled(true);
		//设置显示的logo
		actionBar.setLogo(R.drawable.picture_icon);
		//设置是否显示logo
		actionBar.setDisplayShowHomeEnabled(false);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home){
			//判断点击的是不是返回按钮，android.R.id.home是google官方提供的返回按钮的id
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
