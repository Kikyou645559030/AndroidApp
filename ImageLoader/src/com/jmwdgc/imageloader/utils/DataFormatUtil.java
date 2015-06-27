/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.text.TextUtils;

/**
 * 数字格式化工具类
 */
public class DataFormatUtil {

	/**
	 * 判断字符是否是数字类型
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static int string2Int(String value) {
		try {
			if (isNumeric(value)) {
				return Integer.parseInt(value);
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	public static double string2Double(String value) {
		try {
			if (!TextUtils.isEmpty(value)) {
				return Double.parseDouble(value);
			}
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}
}
