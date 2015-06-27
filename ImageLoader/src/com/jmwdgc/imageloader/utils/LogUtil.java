/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.util.Log;

/**
 * 日志打印工具类
 */
public class LogUtil {

	public static final String TAG = "LogUtil";
	public static boolean DEBUG = true;
	private static boolean PRINTLOG = true;
	private static boolean IS_DEBUG = true;

	public static void i(String msg) {
		if (DEBUG) {
			Log.i(TAG, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (DEBUG) {
			Log.i(tag, msg, tr);
		}
	}

	public static void v(String msg) {
		if (DEBUG || IS_DEBUG) {
			Log.v(TAG, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (DEBUG || IS_DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void e(String msg) {
		if (DEBUG) {
			Log.e(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (DEBUG) {
			Log.e(tag, msg, tr);
		}
	}

	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static void setDEBUG(boolean debug) {
		DEBUG = debug;
	}

	public static boolean isPrintlog() {
		return PRINTLOG;
	}

	public static boolean isPRINTLOG() {
		return PRINTLOG;
	}

	public static void setPRINTLOG(boolean pRINTLOG) {
		PRINTLOG = pRINTLOG;
	}


	public static void Logger(String msg) {
		if (IS_DEBUG) {
			Log.e(TAG, msg);
		}
	}
}
