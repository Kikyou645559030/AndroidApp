/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.parse;

import org.json.JSONException;

/**
 * json解析的公共类
 *
 * @param <T>
 */
public abstract class BaseParser<T> {

	public abstract T parseJSON(String jsonStr) throws JSONException;

}
