/*
 * Copyright (c) Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 公用Adapter
 * Created by Liu_ZhiChao on 2015/4/14.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	public Context mContext;
	public List<T> itemList;

	public CommonAdapter(Context mContext, List<T> itemList) {
		this.mContext = mContext;
		this.itemList = itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);
}
