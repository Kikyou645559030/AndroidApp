/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.network;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.jmwdgc.imageloader.R;
import com.jmwdgc.imageloader.entity.RequestVO;
import com.jmwdgc.imageloader.utils.LogUtil;
import com.jmwdgc.imageloader.widget.LoadingDialog;
import org.json.JSONObject;

/**
 * 描述：该类为app端请求服务接口类。
 * 使用方法：RequestServerImp rsImp=new RequestServerImp(context);
 * rsImp.getDataFromServer(reqVo,callBack);
 * 参数说明：
 * RequestVO reqVo
 * reqVo.context(必填)
 * reqVo.requestUrl(必填)  请求Url地址,配合absUrl使用，该地址可为绝对地址（http//www.paintcircle.com/api.php?op=app_api&action=login）,
 * 也可以为缩写地址（api.php?op=app_api&action=login）会自动补上域名前缀(http//www.paintcircle.com/)
 * reqVo.paramMap(可缺省)    请求参数集合
 * reqVo.jsonParser(可缺省，默认返回原值)  json数据解析模版
 * reqVo.reqMethod(可缺省，默认GET)  请求方式
 * reqVo.absUrl(可缺省，默认false)  是否绝对地址请求
 */
public class RequestServerImp {

	private static final String TAG = "RequestServerImp";
	private ThreadPoolManager threadPoolManager;
	private Context mContext;
	private Resources res;
	private LoadingDialog dialog;
	private NetUtil netUtil;

	public RequestServerImp(Context mContext) {
		this.mContext = mContext;
		res = mContext.getResources();
		threadPoolManager = ThreadPoolManager.getInstance();
		netUtil = new NetUtil();
	}

	@SuppressWarnings("unchecked")
	private class BaseHandler extends Handler {

		private Context context;
		private DataCallback callBack;
		private RequestVO reqVo;

		public BaseHandler(Context context, DataCallback callBack, RequestVO reqVo) {
			this.context = context;
			this.callBack = callBack;
			this.reqVo = reqVo;
		}

		public void handleMessage(Message msg) {
			try {
				if (msg.what == 1) {
					if (msg.obj == null) {
						callBack.onFailure(res.getString(R.string.network_no_response));
					} else {
						// 结果解析
						String jsonStr = (String) msg.obj;
						LogUtil.e(TAG, "--json-->>jsonStr:" + jsonStr);
						if (reqVo.jsonParser != null) {
							JSONObject json = new JSONObject(jsonStr);
							boolean success = json.optBoolean("success");
							if (success || reqVo.absUrl) {
								Object parseJSON = reqVo.jsonParser.parseJSON(jsonStr);
								callBack.onSuccess(parseJSON);
							} else {
								String errorMsg = json.getString("message");
								if (!TextUtils.isEmpty(errorMsg)) {
									callBack.onFailure(errorMsg);
								}
							}
						} else {
							callBack.onSuccess(jsonStr);
						}
					}
				} else if (msg.what == 2) {
					callBack.onFailure(res.getString(R.string.network_response_error));
				} else if (msg.what == 0) {
					callBack.onFailure(res.getString(R.string.network_no_connected));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dialog != null) {
					dialog.dismiss();
				}
			}
		}
	}

	private class BaseTask implements Runnable {
		private Context context;
		private RequestVO reqVo;
		private Handler handler;
		private Message msg = new Message();

		public BaseTask(Context context, RequestVO reqVo, Handler handler) {
			this.context = context;
			this.reqVo = reqVo;
			this.handler = handler;
		}

		private RequestCallback requestCallback = new RequestCallback() {

			@Override
			public void onSuccess(String result) {
				msg.what = 1;
				msg.obj = result;
				handler.sendMessage(msg);
			}

			@Override
			public void onError(Throwable e) {
				msg.what = 2;
				msg.obj = null;
				handler.sendMessage(msg);
			}
		};

		@Override
		public void run() {
			Object obj = null;
			if (NetworkUtil.isNetworkAvailable(context)) {
				if (reqVo.reqMethod == HttpUtils.HTTP_POST) {
					if (reqVo.absUrl) {
						netUtil.post(reqVo, true, requestCallback);
					} else {
						netUtil.post(reqVo, requestCallback);
					}
				} else {
					if (reqVo.absUrl) {
						netUtil.get(reqVo, true, requestCallback);
					} else {
						netUtil.get(reqVo, requestCallback);
					}
				}
			} else {
				msg.what = 0;
				msg.obj = obj;
				handler.sendMessage(msg);
			}
		}
	}

	public void getDataFromServer(RequestVO reqVo, DataCallback callBack) {
		if (reqVo.showProgress) {
			dialog = new LoadingDialog(mContext, res.getString(R.string.dialog_loading));
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
		}
		BaseHandler handler = new BaseHandler(mContext, callBack, reqVo);
		BaseTask taskThread = new BaseTask(mContext, reqVo, handler);
		this.threadPoolManager.addTask(taskThread);
	}

	public interface RequestCallback {
		public void onSuccess(String result);

		public void onError(Throwable e);
	}

	public abstract interface DataCallback<T> {
		public abstract void onSuccess(T result);

		public abstract void onFailure(String errCode);
	}
}
