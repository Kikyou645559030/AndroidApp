package com.kikyou.gettab.viewpagertab;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kikyou.gettab.R;
import com.kikyou.gettab.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用ViewPager来实现Tab效果
 * Created by Liu_Zhichao on 2015/3/6 0006.
 */
public class ViewPagerTabActivity extends BaseActivity{

	private ViewPager pager;
	private TextView kikyou;
	private TextView contacts;
	private TextView find;
	private TextView me;
	private List<View> views = new ArrayList<>();

	@Override
	protected void initView() {
		setContentView(R.layout.activity_viewpager_tab);

		pager = (ViewPager) findViewById(R.id.tab_pager);
		kikyou = (TextView) findViewById(R.id.tab_kikyou);
		contacts = (TextView) findViewById(R.id.tab_contacts);
		find = (TextView) findViewById(R.id.tab_find);
		me = (TextView) findViewById(R.id.tab_me);

		kikyou.setOnClickListener(this);
		contacts.setOnClickListener(this);
		find.setOnClickListener(this);
		me.setOnClickListener(this);
		pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i1) {
			}

			@Override
			public void onPageSelected(int i) {
				setDefault();
				switch (i){
					case 0:
						kikyou.setTextColor(Color.parseColor("#0088ff"));
						break;
					case 1:
						contacts.setTextColor(Color.parseColor("#0088ff"));
						break;
					case 2:
						find.setTextColor(Color.parseColor("#0088ff"));
						break;
					case 3:
						me.setTextColor(Color.parseColor("#0088ff"));
						break;
				}
			}

			@Override
			public void onPageScrollStateChanged(int i) {
			}
		});
	}

	@Override
	protected void initData() {
		views.add(View.inflate(this,R.layout.pager_kikyou,null));
		views.add(View.inflate(this,R.layout.pager_contacts,null));
		views.add(View.inflate(this,R.layout.pager_find,null));
		views.add(View.inflate(this, R.layout.pager_me, null));

		pager.setAdapter(new PagerAdapter() {
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = views.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object o) {
				return view == o;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(views.get(position));
			}
		});
//		pager.setCurrentItem(1);
	}

	/**
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tab_kikyou:
				pager.setCurrentItem(0);
				break;
			case R.id.tab_contacts:
				pager.setCurrentItem(1);
				break;
			case R.id.tab_find:
				pager.setCurrentItem(2);
				break;
			case R.id.tab_me:
				pager.setCurrentItem(3);
				break;
		}
	}

	private void setDefault(){
		kikyou.setTextColor(Color.rgb(255,255,255));
		contacts.setTextColor(Color.rgb(255,255,255));
		find.setTextColor(Color.rgb(255,255,255));
		me.setTextColor(Color.rgb(255,255,255));
	}
}
