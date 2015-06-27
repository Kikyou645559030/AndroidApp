/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.view.Window;
import com.jmwdgc.imageloader.network.RequestServerImp;

/**
 * 公共Activity
 */
public abstract class BaseActivity extends FragmentActivity implements OnClickListener {

	protected Resources res;
	protected Context mContext;
	protected RequestServerImp rsImp;

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mContext = this;
		res = mContext.getResources();
		rsImp = new RequestServerImp(mContext);
		initView();
	}

	private void initView() {
		loadViewLayout();
		findViewById();
		setListener();
		processLogic();
	}

	protected abstract void loadViewLayout();

	protected abstract void findViewById();

	protected abstract void setListener();

	protected abstract void processLogic();
}
