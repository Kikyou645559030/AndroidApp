/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * sp保存数据的工具类
 */
public class PreferencesUtil {

	public static final String PREFERENCE_LOGIN_USER = "preference_fashionmall_login_user";
	public static final String KEY_IS_FIRST = "fashionmall_is_first";
	public static final String KEY_USERID = "fashionmall_userid";
	public static final String KEY_USER_NICKNAME = "fashionmall_user_nick_name";
	public static final String KEY_USER_AVATAR = "fashionmall_user_avatar";
	public static final String KEY_LOGINED = "fashionmall_login_status";
	public static final String KEY_CHAT_ID = "fashionmall_chat_loginid";
	public static final String KEY_USER_AUTHO = "fashionmall_user_autho";

	public static void setStringPreferences(Context context, String preference, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static String getStringPreference(Context context, String preference, String key, String defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, defaultValue);
	}

	public static void setLongPreference(Context context, String preference, String key, long value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	public static long getLongPreference(Context context, String preference, String key, long defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		return sharedPreferences.getLong(key, defaultValue);
	}

	public static void setBooleanPreferences(Context context, String preference, String key, boolean value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public static boolean getBooleanPreference(Context context, String preference, String key, boolean defaultValue) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, defaultValue);
	}
}
