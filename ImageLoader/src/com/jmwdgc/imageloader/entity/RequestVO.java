/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.entity;

import android.content.Context;
import com.jmwdgc.imageloader.network.HttpUtils;
import com.jmwdgc.imageloader.parse.BaseParser;

import java.util.HashMap;

/**
 * 网络请求实体
 */
public class RequestVO {

	public Context context;
	/**
	 * 请求Url
	 */
	public String requestUrl;
	/**
	 * 请求头部参数
	 */
	public HashMap<String, String> headParamMap = new HashMap<String, String>();
	/**
	 * 请求参数
	 */
	public HashMap<String, Object> paramMap = new HashMap<String, Object>();
	/**
	 * Json数据解析模版
	 */
	public BaseParser<?> jsonParser;
	/**
	 * 请求方式；默认为get
	 */
	public int reqMethod = HttpUtils.HTTP_GET;
	/**
	 * 是否为绝对Url请求
	 */
	public boolean absUrl;
	/**
	 * 是否显示进度条
	 */
	public boolean showProgress;
}
