/*
 * Copyright (c) Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.jmwdgc.imageloader.utils.PreferencesUtil;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘志超 on 2015/6/26 11:46.
 * To change this template use File | Settings | Editor | File and Code Templates | Includes.
 */
public class MyApplication extends Application {

	private static MyApplication instance = new MyApplication();
	private HashMap<String, String> headerMap = new HashMap<String, String>();
	public int runNum;// crash重启次数

	public static MyApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		/*初始化图片加载工具*/
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
		configuration = new ImageLoaderConfiguration.Builder(this)
				.memoryCacheExtraOptions(480, 800) // default = device screen dimensions
				.discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
//				.taskExecutor(...)
//				.taskExecutorForCachedImages(...)
				.threadPoolSize(3) // default 设置线程池的大小. 每一个图片加载和显示的任务都是在一个个单独的线程中进行的，这些线程在从图片网络中被下载的时候就会进入线程池。因此，池的大小决定能同时运行的线程数。一个大的线程池能显著地拖慢UI的响应速度
				.threadPriority(Thread.NORM_PRIORITY - 1) // 设置正在运行任务的所有线程在系统中的优先级（1到10）
				.tasksProcessingOrder(QueueProcessingType.FIFO) // default
				.denyCacheImageMultipleSizesInMemory() //default 强制UIL在内存中不能存储内容相同但大小不同的图像。由于完整大小的图片会存储在磁盘缓存中，后面当图片加载进入内存，他们就会缩小到ImageView的大小（图片要显示的尺寸），然而在某些情况下,相同的图像第一次显示在一个小的View中,
				// 然后又需要在一个大的View中显示。同时,两个不同大小的相同内容的图片就会被将被存储在内存中。
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //内存缓存策略，出现OOM时需要注意，你可以指定内存缓存的实现。你可以使用现成的解决方案(他们都是实现limited  size-cache,如果超过缓存大小,就通过一定算法删除一个对象)
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13) // default
//				.discCache(new UnlimitedDiscCache(cacheDir)) // default
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileCount(100)
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(this)) // default
				.imageDecoder(new BaseImageDecoder()) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//				.writeDebugLogs()
				.build();
		ImageLoader.getInstance().init(configuration);
	}

	/**
	 * set用户登录成功httpheader信息
	 */
	public boolean setHttpHeader(Map<String, String> params) {
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				PreferencesUtil.setStringPreferences(this, PreferencesUtil.PREFERENCE_LOGIN_USER, entry.getKey(), entry.getValue());
			}
		}
		return true;
	}

	/**
	 * get用户登录成功httpheader信息
	 */
	public HashMap<String, String> getHttpHeader() {
		String userid = PreferencesUtil.getStringPreference(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-uid", "");
		String token = PreferencesUtil.getStringPreference(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-token", "");
		String secret = PreferencesUtil.getStringPreference(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-secret", "");
		headerMap = new HashMap<String, String>();
		if (!TextUtils.isEmpty(userid)) {
			headerMap.put("x-uid", userid);
		}
		if (!TextUtils.isEmpty(token)) {
			headerMap.put("x-token", token);
		}
		if (!TextUtils.isEmpty(secret)) {
			headerMap.put("x-secret", secret);
		}
		return headerMap;
	}

	/**
	 * 清除用户httpheader信息
	 */
	public void clearHttpHeader() {
		PreferencesUtil.setStringPreferences(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-uid", null);
		PreferencesUtil.setStringPreferences(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-token", null);
		PreferencesUtil.setStringPreferences(this, PreferencesUtil.PREFERENCE_LOGIN_USER, "X-secret", null);
	}
}
