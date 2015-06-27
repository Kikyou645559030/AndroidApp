/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * 全局Activity退出管理类
 */
public class ExitManager extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();
	private List<Activity> loginBlock = new LinkedList<Activity>();

	private static ExitManager instance;

	private ExitManager() {
	}

	public static ExitManager getInstance() {
		if (instance == null) {
			instance = new ExitManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	//新增登录模块
	public void addLoginBlock(Activity activity) {
		loginBlock.add(activity);
	}

	public void clearLoginBlock(Activity activity) {
		loginBlock.clear();
	}

	public void exit() {
		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	public void exitLoginBlock() {
		for (Activity activity : loginBlock) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
}
