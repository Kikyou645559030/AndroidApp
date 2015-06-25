package com.kikyou.testbaidumap;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;

/**
 * 在应用最先启动的时候初始化百度地图
 * Created by Liu_Zhichao on 2015/1/20 0020.
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
		//注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
	}
}
