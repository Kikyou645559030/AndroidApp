/*
 * Copyright (c) $today.year.Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BitMap处理工具类
 */
public class BitmapUtil {

	public static int max = 0;
	public static boolean act_bool = true;
	// 压缩后bitmap
	public static List<Bitmap> bmps = new ArrayList<Bitmap>();
	// 压缩后图片路径
	public static List<String> compressDirs = new ArrayList<String>();
	// 原图片路径
	public static List<String> dirs = new ArrayList<String>();

	/**
	 * 读取图片属性：旋转的角度
	 *
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 压缩图片大小
	 */
	public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap;
		while (true) {
			if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
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

	@SuppressLint("NewApi")
	public static Bitmap fastblur(Context context, Bitmap sentBitmap, int radius) {
		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
		if (VERSION.SDK_INT > 16) {
			final RenderScript rs = RenderScript.create(context);
			final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
			final Allocation output = Allocation.createTyped(rs, input.getType());
			final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
			script.setRadius(radius);
			script.setInput(input);
			script.forEach(output);
			output.copyTo(bitmap);
			return bitmap;
		} else {
			if (radius < 1) {
				return (null);
			}
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			int[] pix = new int[w * h];
			bitmap.getPixels(pix, 0, w, 0, 0, w, h);
			int wm = w - 1;
			int hm = h - 1;
			int wh = w * h;
			int div = radius + radius + 1;
			int r[] = new int[wh];
			int g[] = new int[wh];
			int b[] = new int[wh];
			int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
			int vmin[] = new int[Math.max(w, h)];
			int divsum = (div + 1) >> 1;
			divsum *= divsum;
			int temp = 256 * divsum;
			int dv[] = new int[temp];
			for (i = 0; i < temp; i++) {
				dv[i] = (i / divsum);
			}
			yw = yi = 0;
			int[][] stack = new int[div][3];
			int stackpointer;
			int stackstart;
			int[] sir;
			int rbs;
			int r1 = radius + 1;
			int routsum, goutsum, boutsum;
			int rinsum, ginsum, binsum;
			for (y = 0; y < h; y++) {
				rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
				for (i = -radius; i <= radius; i++) {
					p = pix[yi + Math.min(wm, Math.max(i, 0))];
					sir = stack[i + radius];
					sir[0] = (p & 0xff0000) >> 16;
					sir[1] = (p & 0x00ff00) >> 8;
					sir[2] = (p & 0x0000ff);
					rbs = r1 - Math.abs(i);
					rsum += sir[0] * rbs;
					gsum += sir[1] * rbs;
					bsum += sir[2] * rbs;
					if (i > 0) {
						rinsum += sir[0];
						ginsum += sir[1];
						binsum += sir[2];
					} else {
						routsum += sir[0];
						goutsum += sir[1];
						boutsum += sir[2];
					}
				}
				stackpointer = radius;
				for (x = 0; x < w; x++) {
					r[yi] = dv[rsum];
					g[yi] = dv[gsum];
					b[yi] = dv[bsum];
					rsum -= routsum;
					gsum -= goutsum;
					bsum -= boutsum;
					stackstart = stackpointer - radius + div;
					sir = stack[stackstart % div];
					routsum -= sir[0];
					goutsum -= sir[1];
					boutsum -= sir[2];
					if (y == 0) {
						vmin[x] = Math.min(x + radius + 1, wm);
					}
					p = pix[yw + vmin[x]];
					sir[0] = (p & 0xff0000) >> 16;
					sir[1] = (p & 0x00ff00) >> 8;
					sir[2] = (p & 0x0000ff);
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
					rsum += rinsum;
					gsum += ginsum;
					bsum += binsum;
					stackpointer = (stackpointer + 1) % div;
					sir = stack[(stackpointer) % div];
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
					rinsum -= sir[0];
					ginsum -= sir[1];
					binsum -= sir[2];
					yi++;
				}
				yw += w;
			}
			for (x = 0; x < w; x++) {
				rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
				yp = -radius * w;
				for (i = -radius; i <= radius; i++) {
					yi = Math.max(0, yp) + x;
					sir = stack[i + radius];
					sir[0] = r[yi];
					sir[1] = g[yi];
					sir[2] = b[yi];
					rbs = r1 - Math.abs(i);
					rsum += r[yi] * rbs;
					gsum += g[yi] * rbs;
					bsum += b[yi] * rbs;
					if (i > 0) {
						rinsum += sir[0];
						ginsum += sir[1];
						binsum += sir[2];
					} else {
						routsum += sir[0];
						goutsum += sir[1];
						boutsum += sir[2];
					}
					if (i < hm) {
						yp += w;
					}
				}
				yi = x;
				stackpointer = radius;
				for (y = 0; y < h; y++) {
					// Preserve alpha channel: ( 0xff000000 & pix[yi] )
					pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
					rsum -= routsum;
					gsum -= goutsum;
					bsum -= boutsum;
					stackstart = stackpointer - radius + div;
					sir = stack[stackstart % div];
					routsum -= sir[0];
					goutsum -= sir[1];
					boutsum -= sir[2];
					if (x == 0) {
						vmin[y] = Math.min(y + r1, hm) * w;
					}
					p = x + vmin[y];
					sir[0] = r[p];
					sir[1] = g[p];
					sir[2] = b[p];
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
					rsum += rinsum;
					gsum += ginsum;
					bsum += binsum;
					stackpointer = (stackpointer + 1) % div;
					sir = stack[stackpointer];
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
					rinsum -= sir[0];
					ginsum -= sir[1];
					binsum -= sir[2];
					yi += w;
				}
			}
			bitmap.setPixels(pix, 0, w, 0, 0, w, h);
			return (bitmap);
		}
	}
}
