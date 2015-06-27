/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 */
public class DateUtil {

	private static int UNIT = 1000;

	/**
	 * 获取发布时间差
	 *
	 * @fucntion:根据发布时间戳，显示发布时间差。例如：10分前
	 * @param:postStamp[String] 单位：秒
	 * @return:sb.toString() 单位：秒、分、时、天、月、年
	 */
	public static String getPostTime(String postStamp) {
		StringBuffer sb = new StringBuffer();
		try {
			if (!TextUtils.isEmpty(postStamp)) {
				long curSecond = new Date().getTime() / UNIT;
				long postMillSecond = Long.parseLong(postStamp);
				if (curSecond > postMillSecond) {
					long l = curSecond - postMillSecond;
					long year = l / (12 * 30 * 24 * 60 * 60);
					long month = ((l / (30 * 24 * 60 * 60)) - year * 12);
					long day = ((l / (24 * 60 * 60)) - month * 30);
					long hour = (l / (60 * 60) - day * 24);
					long min = ((l / 60) - day * 24 * 60 - hour * 60);
					long sec = (l - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
					if (year > 0) {
						return sb.append(year).append("年前").toString();
					}
					if (month > 0) {
						return sb.append(month).append("月前").toString();
					}
					if (day > 0)
						return sb.append(day).append("天前").toString();
					if (hour > 0)
						return sb.append(hour).append("小时前").toString();
					if (min > 0) {
						return sb.append(min).append("分钟前").toString();
					}
					if (sec > 0) {
						return sb.append("刚刚").toString();
					}
				} else {
					return sb.append("刚刚").toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 将date类型的数据转换成字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formateDateTimeToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
