package com.kikyou.testbaidumap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyActivity extends Activity implements OnClickListener{

	private MapView mapView;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;
	private Button b8;
	private Button b9;
	private Button b10;
	private Button b11;
	private Button b12;

	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;
	private boolean flag5;
	private boolean flag6;

	private BaiduMap baiduMap;
	private Marker marker;
	private HeatMap heatmap;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById();
		setListener();
		handlerLogic();
	}

	private void findViewById() {
		mapView = (MapView) findViewById(R.id.mv_map);
		b1 = (Button) findViewById(R.id.b_1);
		b2 = (Button) findViewById(R.id.b_2);
		b3 = (Button) findViewById(R.id.b_3);
		b4 = (Button) findViewById(R.id.b_4);
		b5 = (Button) findViewById(R.id.b_5);
		b6 = (Button) findViewById(R.id.b_6);
		b7 = (Button) findViewById(R.id.b_7);
		b8 = (Button) findViewById(R.id.b_8);
		b9 = (Button) findViewById(R.id.b_9);
		b10 = (Button) findViewById(R.id.b_10);
		b11 = (Button) findViewById(R.id.b_11);
		b12 = (Button) findViewById(R.id.b_12);
		baiduMap = mapView.getMap();
	}

	private void setListener() {
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b10.setOnClickListener(this);
		b11.setOnClickListener(this);
		b12.setOnClickListener(this);
		baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				/**
				 * 弹出窗覆盖物
				 */
				ImageView view = new ImageView(getApplicationContext());
				view.setImageResource(R.drawable.alipy_complete);
				//定义用于显示该InfoWindow的坐标点
				LatLng pt = new LatLng(39.86923, 116.397428);
				//创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
				InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
				//显示InfoWindow
				baiduMap.showInfoWindow(mInfoWindow);
				return false;
			}
		});
		baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
			@Override
			public void onMarkerDrag(Marker marker) {
			}

			@Override
			public void onMarkerDragEnd(Marker marker) {
				Toast.makeText(getApplication(),"拖动结束",Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onMarkerDragStart(Marker marker) {
			}
		});
	}

	private void handlerLogic() {

	}

	/**
	 * 添加标注覆盖物
	 */
	private void addOverlay1() {
		LatLng point = new LatLng(39.963175, 116.400244);//定义Maker坐标点
		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_like_s);//构建Marker图标
		OverlayOptions overlayOptions = new MarkerOptions()//构建MarkerOption，用于在地图上添加Marker
				.position(point)
				.draggable(true)
				.icon(bitmap)
				.rotate(-30)
				.title("喜欢")
				.zIndex(16);
		marker = (Marker) baiduMap.addOverlay(overlayOptions);
	}

	/**
	 * 几何图形覆盖物，多边形覆盖物
	 */
	private void addOverlay2(){
		//定义多边形的五个顶点
		LatLng pt1 = new LatLng(39.93923, 116.357428);
		LatLng pt2 = new LatLng(39.91923, 116.327428);
		LatLng pt3 = new LatLng(39.89923, 116.347428);
		LatLng pt4 = new LatLng(39.89923, 116.367428);
		LatLng pt5 = new LatLng(39.91923, 116.387428);
		List<LatLng> pts = new ArrayList<>();
		pts.add(pt1);
		pts.add(pt2);
		pts.add(pt3);
		pts.add(pt4);
		pts.add(pt5);
		//构建用户绘制多边形的Option对象
		OverlayOptions polygonOption = new PolygonOptions()
				.points(pts)
				.stroke(new Stroke(5, 0xff00ff00))
				.fillColor(0xffff00ff);
		//在地图上添加多边形Option，用于显示
		baiduMap.addOverlay(polygonOption);
	}

	/**
	 * 文字覆盖物
	 */
	private void addOverlay3(){
		//定义文字所显示的坐标点
		LatLng llText = new LatLng(39.86923, 116.397428);
		//构建文字Option对象，用于在地图上添加文字
		OverlayOptions textOption = new TextOptions()
				.bgColor(0xAAFFFF00)
				.fontSize(24)
				.fontColor(0xFFFF00FF)
				.text("我是文字标注")
				.rotate(30)
				.position(llText);
		//在地图上添加该文字对象并显示
		baiduMap.addOverlay(textOption);
	}

	/**
	 *地形图图层（GroundOverlay），又可叫做图片图层，即开发者可在地图的指定位置上添加图片。
	 * 该图片可随地图的平移、缩放、旋转等操作做相应的变换。该图层是一种特殊的Overlay，
	 * 它位于底图和底图标注层之间（即该图层不会遮挡地图标注信息）。
	 */
	private void addOverlay4(){
		//定义Ground的显示地理范围
		LatLng southwest = new LatLng(39.92235, 116.380338);
		LatLng northeast = new LatLng(39.947246, 116.414977);
		LatLngBounds bounds = new LatLngBounds.Builder()
				.include(northeast)
				.include(southwest)
				.build();
		//定义Ground显示的图片
		BitmapDescriptor bdGround = BitmapDescriptorFactory
				.fromResource(R.drawable.bg_store_head);
		//定义Ground覆盖物选项
		OverlayOptions ooGround = new GroundOverlayOptions()
				.positionFromBounds(bounds)
				.image(bdGround)
				.transparency(0.8f);
		//在地图中添加Ground覆盖物
		baiduMap.addOverlay(ooGround);
	}

	/**
	 * 热力图是用不同颜色的区块叠加在地图上描述人群分布、密度和变化趋势的一个产品，
	 * 百度地图SDK将绘制热力图的能力为广大开发者开放，帮助开发者利用自有数据，
	 * 构建属于自己的热力图，提供丰富的展示效果。
	 */
	private void addOverlay5(){
		//第一步：设置颜色变化
		//设置渐变颜色值
		int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0) };
		//设置渐变颜色起始值
		float[] DEFAULT_GRADIENT_START_POINTS = { 0.2f, 1f };
		//构造颜色渐变对象
		Gradient gradient = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);

		//第二步：准备数据
		//以下数据为随机生成地理位置点，开发者根据自己的实际业务，传入自有位置数据即可
		List<LatLng> randomList = new ArrayList<LatLng>();
		Random r = new Random();
		for (int i = 0; i < 500; i++) {
			// 116.220000,39.780000 116.570000,40.150000
			int rlat = r.nextInt(370000);
			int rlng = r.nextInt(370000);
			int lat = 39780000 + rlat;
			int lng = 116220000 + rlng;
			LatLng ll = new LatLng(lat / 1E6, lng / 1E6);
			randomList.add(ll);
		}

		//第三步：添加、显示热力图
		//在大量热力图数据情况下，build过程相对较慢，建议放在新建线程实现
		heatmap = new HeatMap.Builder()
				.data(randomList)
				.gradient(gradient)
				.build();
		//在地图上添加热力图
		baiduMap.addHeatMap(heatmap);
	}

	/**
	 * Called when a view has been clicked.
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.b_1:
				baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
				break;
			case R.id.b_2:
				baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
				break;
			case R.id.b_3:
				if (flag1) {
					flag1 = false;
					baiduMap.setTrafficEnabled(true);
				} else {
					flag1 = true;
					baiduMap.setTrafficEnabled(false);
				}
				break;
			case R.id.b_4:
				if (flag2) {
					flag2 = false;
					baiduMap.setBaiduHeatMapEnabled(true);
				} else {
					flag2 = true;
					baiduMap.setBaiduHeatMapEnabled(false);
				}
				break;
			case R.id.b_5:
				baiduMap.clear();//清空所有显示的覆盖物
				if (marker != null){
					marker = null;
				}
				break;
			case R.id.b_6:
				if (marker != null) {
					marker.remove();
					marker = null;
				}
				break;
			case R.id.b_7:
				if (marker == null) addOverlay1();
				break;
			case R.id.b_8:
				addOverlay2();
				break;
			case R.id.b_9:
				addOverlay3();
				break;
			case R.id.b_10:
				baiduMap.hideInfoWindow();
				break;
			case R.id.b_11:
				addOverlay4();
				break;
			case R.id.b_12:
				if (flag3){
					flag3 = false;
					heatmap.removeHeatMap();
					heatmap = null;
				}else {
					addOverlay5();
					flag3 = true;
				}
				break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
}
