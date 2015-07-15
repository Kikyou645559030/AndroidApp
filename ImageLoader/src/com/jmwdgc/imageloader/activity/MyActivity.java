/*
 * Copyright (c) Liu_ZhiChao.
 * To change this template use File | Settings | Editor | Copyright | Copyright Profiles.
 */

package com.jmwdgc.imageloader.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.jmwdgc.imageloader.R;
import com.jmwdgc.imageloader.adapter.ImageListAdapter;
import com.jmwdgc.imageloader.common.AnimateFirstDisplayListener;
import com.jmwdgc.imageloader.common.ImageLoaderTools;
import com.jmwdgc.imageloader.utils.ColorPhrase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.Arrays;

/**
 * Created by 刘志超 on 2015/6/25.
 * 主界面
 */
public class MyActivity extends BaseActivity {

	public final static String IMAGE_URL_1 = "http://img0.ph.126.net/KCfMnN8YqcBdzACN8-ET5w==/6598001053005486021.jpg";
	public final static String IMAGE_URL_2 = "http://img1.ph.126.net/c0JQ2u2ay9xZxvzmA4AuNg==/6598001053005486022.jpg";
	public final static String IMAGE_URL_3 = "http://img1.ph.126.net/VKCVjpxJoiBVfoKqylbofw==/3030078124307475577.jpg";
	public final static String IMAGE_URL_4 = "http://img2.ph.126.net/L8suwLQ24hw_y4Z_fL_syw==/1576259869597600295.jpg";
	public final static String IMAGE_URL_5 = "http://img2.ph.126.net/QBrSrIQCIBv_WL41BoaxAw==/6597517267889139683.jpg";
	public final static String IMAGE_URL_6 = "http://img1.ph.126.net/YvQppdlQjhIR7y46g-o34A==/6597604129307845138.jpg";
	public final static String IMAGE_URL_7 = "http://img0.ph.126.net/-j8jbyrwIeFYsc0m_cK9og==/6597605228819471388.jpg";
	public final static String IMAGE_URL_8 = "http://img2.ph.126.net/mXtTrqDxfHt9CMPaON-OHw==/6597155528565015554.jpg";
	public final static String IMAGE_URL_9 = "http://img1.ph.126.net/H6pw3CibWV2590a8zUyzNA==/6597829529191552843.jpg";
	public final static String IMAGE_URL_10 = "http://img2.ph.126.net/eG11R_dNw_ZrXMMnU_6LWA==/1556838096204570722.jpg";
	public final static String[] IMAGE_URL_LIST = {IMAGE_URL_1, IMAGE_URL_2, IMAGE_URL_3, IMAGE_URL_4,
			IMAGE_URL_5, IMAGE_URL_6, IMAGE_URL_7, IMAGE_URL_8, IMAGE_URL_9, IMAGE_URL_10};

	/**
	 * 主要显示图片的方法displayImage(), loadImage(),loadImageSync()
	 * loadImageSync()方法是同步的，网络操作不能在主线程，所以loadImageSync()方法不能使用
	 */
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private ImageView image6;
	private ImageView image7;
	private ImageView image8;
	private ImageView image9;
	private ImageView image10;
	private ListView imgListView;

	private AnimateFirstDisplayListener displayListener = new AnimateFirstDisplayListener();

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.main);
	}

	@Override
	protected void findViewById() {
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
		image3 = (ImageView) findViewById(R.id.image3);
		image4 = (ImageView) findViewById(R.id.image4);
		image5 = (ImageView) findViewById(R.id.image5);
		image6 = (ImageView) findViewById(R.id.image6);
		image7 = (ImageView) findViewById(R.id.image7);
		image8 = (ImageView) findViewById(R.id.image8);
		image9 = (ImageView) findViewById(R.id.image9);
		image10 = (ImageView) findViewById(R.id.image10);
		imgListView = (ListView) findViewById(R.id.img_list_view);
	}

	@Override
	protected void setListener() {
		/**ImageLoader控制在滑动的时候是否停止加载图片，第二个参数是滑动是否暂停，第三个参数是快速滑动是否暂停*/
		imgListView.setOnScrollListener(new PauseOnScrollListener(imageLoader, false, true));
	}

	@Override
	protected void processLogic() {
		imageLoader.loadImage(IMAGE_URL_1, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				image1.setImageBitmap(loadedImage);
			}
		});

		imageLoader.loadImage(IMAGE_URL_2, new ImageSize(300, 300), new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				image2.setImageBitmap(loadedImage);
			}
		});

		/*加载图片的配置选项
			DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty) //设置图片Uri为空的时候显示的图片
				.showImageOnFail(R.drawable.ic_error) //设置图片加载失败的时候显示的图片
				.showStubImage //设置图片在下载期间显示的图片
				.resetViewBeforeLoading(false)  // default 设置图片在下载前是否重置，复位
				.delayBeforeLoading(1000) //设置图片下载前的延迟
				.cacheInMemory(false) // default 是否缓存到内存
				.cacheOnDisc(false) // default 是否缓存到磁盘
				.preProcessor(...) //设置图片加入缓存前，对bitmap进行设置
				.postProcessor(...) //设置显示前的图片，显示后这个图片一直保留在缓存中
				.extraForDownloader(...) //设置额外的内容给ImageDownloader
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 图片缩放方式
				.bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型 图片位深度
				.decodingOptions(...) //设置图片的解码配置
				.displayer(new SimpleBitmapDisplayer()) //设置图片的显示方式
				.handler(new Handler()) // default
				.build();
			图片的显示方式：
				RoundedBitmapDisplayer(int roundPixels)设置圆角图片
				FakeBitmapDisplayer()这个类什么都没做，导致图片显示默认的
				FadeInBitmapDisplayer(int durationMillis)设置图片渐显的时间
				SimpleBitmapDisplayer()正常显示一张图片
			图片缩放方式：
				EXACTLY :图像将完全按比例缩小的目标大小
				EXACTLY_STRETCHED:图片会缩放到目标大小完全
				IN_SAMPLE_INT:图像将被二次采样的整数倍
				IN_SAMPLE_POWER_OF_2:图片将降低2倍，直到下一减少步骤，使图像更小的目标大小
				NONE:图片不会调整
			*/
		//显示图片的配置
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.empty_bg)
				.showImageOnFail(R.drawable.empty_bg)
				.showStubImage(R.drawable.empty_bg)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.ARGB_4444)
				.displayer(new RoundedBitmapDisplayer(5))
				.build();
		imageLoader.loadImage(IMAGE_URL_3, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				image3.setImageBitmap(loadedImage);
			}
		});

		/**以上方法都不推荐，下面是推荐方法*/
		imageLoader.displayImage(IMAGE_URL_4, image4);
		//显示图片的配置
		DisplayImageOptions options1 = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.empty_bg)
				.showImageOnFail(R.drawable.empty_bg)
				.showStubImage(R.drawable.empty_bg)
				.delayBeforeLoading(1000)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.ARGB_8888)
				.displayer(new RoundedBitmapDisplayer(50))
				.build();
		imageLoader.displayImage(IMAGE_URL_5, image5, options1);
		imageLoader.displayImage(IMAGE_URL_6, image6, displayListener);
		imageLoader.displayImage(IMAGE_URL_7, image7, ImageLoaderTools.getImageOptions(R.drawable.empty_bg, Bitmap.Config.RGB_565), displayListener);
		imageLoader.displayImage(IMAGE_URL_8, image8, ImageLoaderTools.getImageOptions(R.drawable.empty_bg, Bitmap.Config.ARGB_4444), displayListener);
		imageLoader.displayImage(IMAGE_URL_9, image9, ImageLoaderTools.getImageOptions(R.drawable.empty_bg, Bitmap.Config.ARGB_8888), displayListener);
		imageLoader.displayImage(IMAGE_URL_10, image10, ImageLoaderTools.getImageOptions(R.drawable.empty_bg, Bitmap.Config.ALPHA_8), displayListener);

		//加载sd卡上的图片
//		String imageUrl1 = ImageDownloader.Scheme.FILE.wrap(Environment.getExternalStorageDirectory().getAbsolutePath() + "/img.jpg");
		//加载assets目录中的图片
//		String imageUrl2 = ImageDownloader.Scheme.ASSETS.wrap("img.jpg");
		//加载资源文件中的图片
//		String imageUrl3 = ImageDownloader.Scheme.DRAWABLE.wrap("R.drawable.img");
//		String imageUrl4 = ImageDownloader.Scheme.HTTP.wrap("");
//		String imageUrl5 = ImageDownloader.Scheme.HTTPS.wrap("");
//		String imageUrl6 = ImageDownloader.Scheme.CONTENT.wrap("");

		ImageListAdapter imageListAdapter = new ImageListAdapter(mContext, Arrays.asList(IMAGE_URL_LIST));
		imgListView.setAdapter(imageListAdapter);

		//最简单的改变一段文字中的部分字的颜色
		CharSequence formatted = ColorPhrase.from("I'm {Chinese}, I love {China}")
				.withSeparator("{}")
				.innerColor(0xFFE6454A)
				.outerColor(0xFF666666)
				.format();
	}

	@Override
	public void onClick(View v) {
	}
}
