package com.kikyou.testbaidumap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.overlayutil.PoiOverlay;

/**
 * 构造自定义 PoiOverlay 类,利用检索结果覆盖物展示POI搜索结果
 * Created by Liu_Zhichao on 2015/1/22 0022.
 */
public class MyPoiOverlay extends PoiOverlay {
	public MyPoiOverlay(BaiduMap baiduMap) {
		super(baiduMap);
	}

	@Override
	public boolean onPoiClick(int i) {
		super.onPoiClick(i);
		return true;
	}
}
