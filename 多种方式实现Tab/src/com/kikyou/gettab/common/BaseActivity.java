package com.kikyou.gettab.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * 公共Activity
 * Created by Liu_Zhichao on 2015/3/6 0006.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
		initData();
	}

	protected abstract void initView();

	protected abstract void initData();
}
