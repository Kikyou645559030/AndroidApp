/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 */
public class FileUtil {

	private static final String TAG = "FileUtil";

	/**
	 * 获取应用缓存地址根目录
	 */
	public static File getDiskCacheFile(Context context, String cacheDir) {

		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
				|| !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() : context.getCacheDir().getPath();
		File file = new File(cachePath + File.separator + cacheDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		LogUtil.e(TAG, "文件存储目录为:" + cachePath + File.separator + cacheDir);
		return new File(cachePath + File.separator + cacheDir);
	}

	/**
	 * 压缩图片大小：降低质量，宽高不变
	 *
	 * @param file=压缩图片,size=压缩后图片大小<size
	 * @return void
	 */
	public static void compressBmpToFile(File file, int size) {
		try {
			FileInputStream fis = new FileInputStream(file);
			Bitmap bitmap = BitmapFactory.decodeStream(fis);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int options = 100;// 从80开始,
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			while (baos.toByteArray().length / 1024 > size) {
				baos.reset();
				options -= 10;
				bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			}
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(baos.toByteArray());
				bitmap.recycle();
				fos.flush();
				fos.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩图片大小:改变图片宽高压缩图片大小
	 *
	 * @param path 图片路径
	 * @bitmap
	 */
	public static Bitmap revitionImageSize(String path, int width, int height) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap;
		while (true) {
			if ((options.outWidth >> i <= width) && (options.outHeight >> i <= height)) {
				in = new BufferedInputStream(new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}

	public static boolean isExternalStorageRemovable() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	public static File getExternalCacheDir(Context context) {
		if (hasExternalCacheDir()) {
			return context.getExternalCacheDir();
		}

		// Before Froyo we need to construct the external cache dir ourselves
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
	}

	public static boolean hasExternalCacheDir() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	public static void saveBitmap(Bitmap bm, String cpPath, String picName) {
		try {
			File f = new File(cpPath, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除目录下所有文件
	 */
	public static void deleteDir(String filePath) {
		File dir = new File(filePath);
		if (!dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete(); // 删除所有文件
			else if (file.isDirectory())
				deleteDir(file.getPath()); // 递规的方式删除文件夹
		}
		//dir.delete();// 删除目录本身
	}

	/**
	 * 根据文件路径返回File对象
	 */
	public static List<File> getFileByPath(List<String> paths) {
		List<File> files = new ArrayList<File>();
		if (paths != null && paths.size() > 0) {
			for (String path : paths) {
				if (!TextUtils.isEmpty(path)) {
					File file = new File(path);
					if (file.exists()) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	/**
	 * 根据文件路径返回InputStream对象
	 */
	public static List<InputStream> getInputStreamByPath(List<String> paths) {
		List<InputStream> files = new ArrayList<InputStream>();
		try {
			if (paths != null && paths.size() > 0) {
				for (String path : paths) {
					if (!TextUtils.isEmpty(path)) {
						File file = new File(path);
						if (file.exists()) {
							files.add(new FileInputStream(file));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	/**
	 * 将Bitmap保存到文件
	 */
	public static void saveBitmapToFile(Bitmap bm, String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从本地文件返回Bitmap
	 */
	public static Bitmap getBitmapFromUri(Context context, Uri uri) {
		try {
			if (uri == null) {
				return null;
			}
			// 读取uri所在的图片
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
			return bitmap;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			Log.e(TAG, "目录为：" + uri);
			e.printStackTrace();
			return null;
		}
	}
}
