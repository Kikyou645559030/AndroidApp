/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.common;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by 刘志超 on 2015/6/27 16:22.
 * To change this template use File | Settings | Editor | File and Code Templates | Includes.
 * <p/>
 * ImageLoader加载图片配置的公共方法工具类
 */
public class ImageLoaderTools {

	/**
	 * @return
	 */
	public static DisplayImageOptions getImageOptions() {
		DisplayImageOptions imgoptions = null;
		try {
			imgoptions = new DisplayImageOptions.Builder()
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgoptions;
	}

	/**
	 * 设置默认图
	 *
	 * @param drawableId
	 * @return
	 */
	public static DisplayImageOptions getImageOptions(int drawableId) {
		DisplayImageOptions imgoptions = null;
		try {
			imgoptions = new DisplayImageOptions.Builder()
					.showStubImage(drawableId)
					.showImageForEmptyUri(drawableId)
					.showImageOnFail(drawableId)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgoptions;
	}

	/**
	 * 设置默认图和图形质量
	 *
	 * @param drawableId
	 * @return
	 */
	public static DisplayImageOptions getImageOptions(int drawableId, Bitmap.Config bitmapConfig) {
		DisplayImageOptions imgoptions = null;
		try {
			imgoptions = new DisplayImageOptions.Builder()
					.showStubImage(drawableId)
					.showImageForEmptyUri(drawableId)
					.showImageOnFail(drawableId)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.displayer(new RoundedBitmapDisplayer(0))
					.bitmapConfig(bitmapConfig)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgoptions;
	}

	/**
	 * 设置默认图和圆角
	 *
	 * @param drawableId 默认图片
	 * @param angleNum   圆角
	 * @return imgoptions
	 * <p/>
	 * 图片质量：
	 * ALPHA_8 代表8位Alpha位图，透明度很精确
	 * ARGB_4444 代表16位ARGB位图，4个4位就是16位组成，能显示透明效果且内存占用小，不推荐，显示效果差
	 * ARGB_8888 代表32位ARGB位图，8个8位就是32位组成，图片质量最好但占用内存大
	 * RGB_565 代表8位RGB位图，R由5位、G由6位、B由5位共16位组成，不算透明度质量略高于ARGB_4444，但内存占用小
	 * <p/>
	 * 图片缩放方式：
	 * EXACTLY :图像将完全按比例缩小的目标大小
	 * EXACTLY_STRETCHED:图片会缩放到目标大小完全
	 * IN_SAMPLE_INT:图像将被二次采样的整数倍
	 * IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
	 * NONE:图片不会调整
	 */
	public static DisplayImageOptions getImageOptions(int drawableId, int angleNum) {
		DisplayImageOptions imgoptions = null;
		try {
			imgoptions = new DisplayImageOptions.Builder()
					.showStubImage(drawableId)
					.showImageForEmptyUri(drawableId)
					.showImageOnFail(drawableId)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.displayer(new RoundedBitmapDisplayer(angleNum))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgoptions;
	}
}
