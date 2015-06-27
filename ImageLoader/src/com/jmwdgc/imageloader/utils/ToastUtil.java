/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Toast工具类
 */
public class ToastUtil {

	public static void shortToast(Context context, String content) {
		if (!TextUtils.isEmpty(content)) {
			Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
		}
	}
}
