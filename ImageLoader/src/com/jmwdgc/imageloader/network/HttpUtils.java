/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * http请求的工具类
 */
public class HttpUtils {

	public static final String HTTP_TRANSFER_ERROR = "connection failure";

	public static final int HTTP_GET = 0;
	public static final int HTTP_POST = 1;
	public static final int CONNECT_TIME_OUT = 30000;

	private HttpUtils() {
	}

	public static String getHttpEntity(String url, List<NameValuePair> params, int method) {
		switch (method) {
			case HTTP_GET:
				return HttpUtils.HttpGet(url);
			case HTTP_POST:
				return HttpUtils.HttpPost(url, params);
			default:
				return null;
		}
	}

	private static DefaultHttpClient getHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIME_OUT);
		HttpConnectionParams.setSoTimeout(httpParams, CONNECT_TIME_OUT);
		ConnManagerParams.setTimeout(httpParams, CONNECT_TIME_OUT);
		DefaultHttpClient mHttpClient = new DefaultHttpClient(httpParams);
		mHttpClient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIME_OUT);
		mHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, CONNECT_TIME_OUT);
		return mHttpClient;
	}

	/**
	 * Httpget请求，传入参数为httpget请求
	 * 例如：http://www.mydomain.com/music/resource/list?user=aaa&index=1
	 *
	 * @param url
	 * @return
	 */
	private static String HttpGet(String url) {
		DefaultHttpClient mHttpClient = getHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse httpResponse = mHttpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				int status = httpResponse.getStatusLine().getStatusCode();
				if (status == HttpStatus.SC_OK) {
					String response = EntityUtils.toString(httpEntity, "UTF-8");
					return response;
				}
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			return null;
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * HttpPost请求，需要构造post参数 post参数例子： Add data to your post List<NameValuePair>
	 * pairs = new ArrayList<NameValuePair>(2); pairs.add(new
	 * BasicNameValuePair("ID", "VALUE")); pairs.add(new
	 * BasicNameValuePair("string", "Yeah!"));
	 *
	 * @param url
	 * @param pairs
	 * @return
	 */
	private static String HttpPost(String url, List<NameValuePair> pairs) {
		DefaultHttpClient mHttpClient = getHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {
			if (pairs != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			}
			HttpResponse httpResponse = mHttpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				int status = httpResponse.getStatusLine().getStatusCode();
				if (status == HttpStatus.SC_OK) {
					String response = EntityUtils.toString(httpEntity, "UTF-8");
					return response;
				}
			}
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (HttpHostConnectException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrConnectionTypeName(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		String netInfo = "";
		String type = (networkInfo == null ? null : networkInfo.getTypeName());
		if ("".equals(type) || type == null) {
			netInfo += type;
		}
		String extraInfo = networkInfo.getExtraInfo();
		if ("".equals(extraInfo) || extraInfo == null) {
			netInfo += " " + extraInfo;
		}
		return netInfo;
	}

	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		if (null == networkInfo) {
			return false;
		}
		boolean connected = networkInfo.isConnected();
		return connected;
	}

	public static String getConnectionInfo(Context context) {
		// 获取网络连接管理者
		ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// 获取网络的状态信息，有下面三种方式
		NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
		return null;
		// NetworkInfo 有一下方法
		// getDetailedState()：获取详细状态。
		// getExtraInfo()：获取附加信息。
		// getReason()：获取连接失败的原因。
		// getType()：获取网络类型(一般为移动或Wi-Fi)。
		// getTypeName()：获取网络类型名称(一般取值“WIFI”或“MOBILE”)。
		// isAvailable()：判断该网络是否可用。
		// isConnected()：判断是否已经连接。
		// isConnectedOrConnecting()：判断是否已经连接或正在连接。
		// isFailover()：判断是否连接失败。
		// isRoaming()：判断是否漫游
		//
		// 当用wifi上的时候
		// getType 是 WIFI
		// getExtraInfo是空的
		// 当用手机上的时候
		// getType 是MOBILE
		//
		// 用移动CMNET方式
		// getExtraInfo 的值是cmnet
		// 用移动CMWAP方式
		// getExtraInfo 的值是cmwap 但是不在代理的情况下访问普通的网站访问不了
		//
		// 用联通3gwap方式
		// getExtraInfo 的值是3gwap
		// 用联通3gnet方式
		// getExtraInfo 的值是3gnet
		// 用联通uniwap方式
		// getExtraInfo 的值是uniwap
		// 用联通uninet方式
		// getExtraInfo 的值是uninet
	}
}
