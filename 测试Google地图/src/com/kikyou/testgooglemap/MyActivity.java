package com.kikyou.testgooglemap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * 只能翻墙连接，手机必须有google play和google play services两个应用才能正常使用google地图
 * 缺少google-play-services.jar包，原包已损坏删除
 */
public class MyActivity extends FragmentActivity implements View.OnClickListener {

//	static final LatLng LL = new LatLng(23.979548, 120.696745);
//
//	private GoogleMap gMap;
//	private MapView mvMap;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;

	private boolean flag1;
	private boolean flag2;
	private boolean flag3;
	private boolean flag4;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		findViewById();
		setListener();
		handlerLogic();
	}

	private void findViewById() {
		/*if (gMap == null) {
			SupportMapFragment fragment= (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.f_map);
			gMap = fragment.getMap();
			if (gMap != null){
				gMap.setMyLocationEnabled(true);
				gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LL, 16));
				Marker marker = gMap.addMarker(new MarkerOptions().position(LL).title("Google地图").snippet("测试测试测试"));
			}
		}*/
		/*mvMap = (MapView) findViewById(R.id.mv_map);
		b1 = (Button) findViewById(R.id.b_1);
		b2 = (Button)findViewById(R.id.b_2);
		b3 = (Button)findViewById(R.id.b_3);
		b4 = (Button)findViewById(R.id.b_4);*/
	}

	private void setListener() {
		/*b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);*/
	}

	private void handlerLogic() {

	}

	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		/*switch (v.getId()){
			case R.id.b_1:
				if (flag1) {
				} else {
				}
				break;
			case R.id.b_2:
				if (flag2) {
				} else {
				}
				break;
			case R.id.b_3:
				if (flag3) {
				} else {
				}
				break;
			case R.id.b_4:
				break;
		}*/
	}
}
