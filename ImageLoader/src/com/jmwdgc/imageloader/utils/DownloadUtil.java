/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * 下载工具类
 */
public class DownloadUtil {

	private static final int CONNECT_TIMEOUT = 30000;
	private static final int DATA_TIMEOUT = 40000;
	private final static int DATA_BUFFER = 8192;

	public interface DownloadListener {
		public void downloading(int progress);

		public void downloaded();
	}

	public static long download(String urlStr, File dest, boolean append, DownloadListener downloadListener) throws Exception {
		int downloadProgress;
		long remoteSize;
		int currentSize = 0;
		long totalSize = -1;

		if (!append && dest.exists() && dest.isFile()) {
			dest.delete();
		}

		if (append && dest.exists() && dest.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(dest);
				currentSize = fis.available();
			} catch (IOException e) {
				throw e;
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
		}

		HttpGet request = new HttpGet(urlStr);
		if (currentSize > 0) {
			request.addHeader("RANGE", "bytes=" + currentSize + "-");
		}

		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, CONNECT_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, DATA_TIMEOUT);
		HttpClient httpClient = new DefaultHttpClient(params);

		InputStream is = null;
		FileOutputStream os = null;
		try {
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				is = response.getEntity().getContent();
				remoteSize = response.getEntity().getContentLength();
				Header contentEncoding = response.getFirstHeader("Content-Encoding");
				if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					is = new GZIPInputStream(is);
				}
				os = new FileOutputStream(dest, append);
				byte buffer[] = new byte[DATA_BUFFER];
				int readSize = 0;
				while ((readSize = is.read(buffer)) > 0) {
					os.write(buffer, 0, readSize);
					os.flush();
					totalSize += readSize;
					if (downloadListener != null) {
						downloadProgress = (int) (totalSize * 100 / remoteSize);
						downloadListener.downloading(downloadProgress);
					}
				}
				if (totalSize < 0) {
					totalSize = 0;
				}
			}
		} finally {
			if (os != null) {
				os.close();
			}
			if (is != null) {
				is.close();
			}
		}

		if (totalSize < 0) {
			throw new Exception("Download file fail: " + urlStr);
		}
		if (downloadListener != null) {
			downloadListener.downloaded();
		}
		return totalSize;
	}
}
