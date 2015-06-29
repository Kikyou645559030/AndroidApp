/*
 * Copyright (c) Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.common;

import android.graphics.*;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

/**
 * Created by 刘志超 on 2015/6/29 14:25.
 * To change this template use File | Settings | Editor | File and Code Templates | Includes.
 * 使用ImageLoader显示圆形图片可以使用到，设置到DisplayImageOptions中的postProcessor()方法参数里
 */
public class CircleImageProcessor implements BitmapProcessor {

	@Override
	public Bitmap process(Bitmap bitmap) {
		int min;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		if (width < height) {
			min = width;
		} else {
			min = height;
		}
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
		//产生一个同样大小的画布
		Canvas canvas = new Canvas(target);
		//首先绘制圆形
		canvas.drawCircle(min / 2, min / 2, min / 2, paint);
		//使用SRC_IN，参考上面的说明
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		//绘制图片
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return target;
	}
}
