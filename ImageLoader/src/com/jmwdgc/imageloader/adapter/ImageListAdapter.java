/*
 * Copyright (c) Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jmwdgc.imageloader.common.ImageLoaderTools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 刘志超 on 2015/6/29 11:48.
 * To change this template use File | Settings | Editor | File and Code Templates | Includes.
 */
public class ImageListAdapter extends CommonAdapter<String> {

	private ImageLoader imageLoader = ImageLoader.getInstance();

	public ImageListAdapter(Context mContext, List<String> itemList) {
		super(mContext, itemList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		imageLoader.displayImage(itemList.get(position), imageView, ImageLoaderTools.getImageOptions());
		return imageView;
	}
}
