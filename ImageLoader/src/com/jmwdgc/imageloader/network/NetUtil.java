/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.network;

import android.text.TextUtils;
import android.util.Log;
import com.jmwdgc.imageloader.MyApplication;
import com.jmwdgc.imageloader.config.URLInterface;
import com.jmwdgc.imageloader.entity.RequestVO;
import com.jmwdgc.imageloader.network.RequestServerImp.RequestCallback;
import com.jmwdgc.imageloader.utils.LogUtil;
import com.jmwdgc.imageloader.utils.StringUtil;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import org.apache.http.Header;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 */
public class NetUtil {

	private static final String TAG = "NetUtil";
	//private AsyncHttpClient httpClient = new AsyncHttpClient();
	private SyncHttpClient httpClient = new SyncHttpClient();

	public NetUtil() {
		httpClient.setTimeout(30000);//设置请求30s超时,默认10s
	}


	public void get(RequestVO vo, RequestCallback callback) {
		get(vo, false, callback);
	}

	public void post(RequestVO vo, RequestCallback callback) {
		post(vo, false, callback);
	}

	public void get(RequestVO vo, boolean absUrl, final RequestCallback callback) {
		httpClient = new SyncHttpClient();
		String url = null;
		if (!absUrl) {
			url = URLInterface.REQUEST_HEAD + vo.requestUrl;
		} else {
			url = vo.requestUrl;
		}

		RequestParams params;

		try {
			if (!StringUtil.isEmpty(url)) {
				HashMap<String, String> headParamMap = vo.headParamMap;
				HashMap<String, Object> paramMap = vo.paramMap;
				params = new RequestParams();
				//系统参数
				if (!TextUtils.isEmpty(URLInterface.operation)) {
					params.put("p", URLInterface.operation);
				}
				if (!TextUtils.isEmpty(URLInterface.control)) {
					params.put("c", URLInterface.control);
				}
				if (!TextUtils.isEmpty(URLInterface.version)) {
					params.put("v", URLInterface.version);
				}
				if (!TextUtils.isEmpty(URLInterface.mac)) {
					params.put("m", URLInterface.mac);
				}
				if (headParamMap != null && headParamMap.size() > 0) {
					for (Map.Entry<String, String> entry : headParamMap.entrySet()) {
						httpClient.addHeader(entry.getKey(), entry.getValue());
					}
					LogUtil.e(TAG, "head params:" + headParamMap.toString());
				}
				if (paramMap != null && paramMap.size() > 0) {
					for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
						if (entry.getValue() instanceof String) {
							params.put(entry.getKey(), (String) entry.getValue());
						} else {
							params.put(entry.getKey(), String.valueOf(entry.getValue()));
						}
					}
				}
				LogUtil.e(TAG, "get:" + url);
				LogUtil.e(TAG, "params:" + params.toString());
				Map<String, String> headers = MyApplication.getInstance().getHttpHeader();
				LogUtil.e(TAG, "http headers:" + headers);
				if (headers != null && headers.size() > 0) {
					for (Map.Entry<String, String> entry : headers.entrySet()) {
						httpClient.addHeader(entry.getKey(), entry.getValue());
					}
				}
				httpClient.get(url, params, new TextHttpResponseHandler() {
					@Override
					public void onStart() {
					}

					@Override
					public void onFinish() {
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2, Throwable e) {
						callback.onError(e);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, String responseBody) {
						callback.onSuccess(responseBody);
					}
				});
			}
		} catch (Exception e) {
			callback.onError(e);
			e.printStackTrace();
			Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(), e);
		}
	}

	public void post(RequestVO vo, boolean absUrl, final RequestCallback callback) {
		httpClient = new SyncHttpClient();
		String url;
		if (!absUrl) {
			url = URLInterface.REQUEST_HEAD + vo.requestUrl;
		} else {
			url = vo.requestUrl;
		}
		RequestParams params;
		try {
			if (!StringUtil.isEmpty(url)) {
				HashMap<String, String> headParamMap = vo.headParamMap;
				HashMap<String, Object> paramMap = vo.paramMap;
				params = new RequestParams();
				//系统参数
				if (!TextUtils.isEmpty(URLInterface.operation)) {
					params.put("p", URLInterface.operation);
				}
				if (!TextUtils.isEmpty(URLInterface.control)) {
					params.put("c", URLInterface.control);
				}
				if (!TextUtils.isEmpty(URLInterface.version)) {
					params.put("v", URLInterface.version);
				}
				if (!TextUtils.isEmpty(URLInterface.mac)) {
					params.put("m", URLInterface.mac);
				}
				if (paramMap != null && paramMap.size() > 0) {
					for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
						if (entry.getValue() instanceof String) {
							params.put(entry.getKey(), (String) entry.getValue());
						} else if (entry.getValue() instanceof File) {
							params.put(entry.getKey(), (File) entry.getValue(), "image/jpeg");
						} else if (entry.getValue() instanceof InputStream) {
							params.put(entry.getKey(), (InputStream) entry.getValue());
						} else if (entry.getValue() instanceof List) {
							List list = (List) entry.getValue();
							if (list.size() > 0) {
								for (int i = 0; i < list.size(); i++) {
									Object obj = list.get(i);
									if (obj instanceof File) {
										//params.put(entry.getKey()+"["+i+"]",(File)obj,"image/jpeg");
										File file = (File) obj;
										if (file.exists()) {
											params.put(entry.getKey() + "[" + i + "]", new FileInputStream(file), file.getName(), "image/jpeg");
										}
									} else if (obj instanceof String) {
										params.put(entry.getKey() + "[" + i + "]", (String) obj);
									} else if (obj instanceof InputStream) {
										params.put(entry.getKey() + "[" + i + "]", (InputStream) obj, "test" + i, "image/jpeg");
									}
								}
							}
						}
					}
				}
				LogUtil.e(TAG, "post:" + url);
				LogUtil.e(TAG, "params:" + params.toString());
				Map<String, String> headers = MyApplication.getInstance().getHttpHeader();
				LogUtil.e(TAG, "http headers:" + headers);
				if (headers != null && headers.size() > 0) {
					for (Map.Entry<String, String> entry : headers.entrySet()) {
						httpClient.addHeader(entry.getKey(), entry.getValue());
					}
				}
				if (headParamMap != null && headParamMap.size() > 0) {
					for (Map.Entry<String, String> entry : headParamMap.entrySet()) {
						httpClient.addHeader(entry.getKey(), entry.getValue());
					}
					LogUtil.e(TAG, "http headers params:" + headParamMap.toString());
				}
				httpClient.post(url, params, new TextHttpResponseHandler() {
					@Override
					public void onStart() {
					}

					@Override
					public void onFinish() {
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2, Throwable e) {
						callback.onError(e);
					}

					@Override
					public void onSuccess(int arg0, Header[] arg1, String responseBody) {
						callback.onSuccess(responseBody);
					}
				});
			}
		} catch (Exception e) {
			callback.onError(e);
			e.printStackTrace();
			Log.e(NetUtil.class.getSimpleName(), e.getLocalizedMessage(), e);
		}
	}
}
