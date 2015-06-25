package com.kikyou.gettab.fragmenttab;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.kikyou.gettab.R;
import com.kikyou.gettab.common.BaseActivity;
import com.kikyou.gettab.viewpagertab.ShowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过viewPager和FragmentPagerAdapter来实现tab
 * Created by Liu_Zhichao on 2015/3/11 0011.
 */
public class FragmentAdapterTabActivity extends BaseActivity{

	private ViewPager pager;
	private TextView kikyou;
	private TextView contacts;
	private TextView find;
	private TextView me;

	private List<Fragment> fragments = new ArrayList<>();

	@Override
	protected void initView() {
		setContentView(R.layout.activity_fragment_adapter_tab);

		pager = (ViewPager) findViewById(R.id.tab_pager);
		kikyou = (TextView) findViewById(R.id.tab_kikyou);
		contacts = (TextView) findViewById(R.id.tab_contacts);
		find = (TextView) findViewById(R.id.tab_find);
		me = (TextView) findViewById(R.id.tab_me);

		kikyou.setOnClickListener(this);
		contacts.setOnClickListener(this);
		find.setOnClickListener(this);
		me.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		ShowFragment fragment1 = new ShowFragment();
		fragment1.setContent("我是第一个页面");
		ShowFragment fragment2 = new ShowFragment();
		fragment2.setContent("我是第二个页面");
		ShowFragment fragment3 = new ShowFragment();
		fragment3.setContent("我是第三个页面");
		ShowFragment fragment4 = new ShowFragment();
		fragment4.setContent("我是第四个页面");

		fragments.add(fragment1);
		fragments.add(fragment2);
		fragments.add(fragment3);
		fragments.add(fragment4);

		pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public Fragment getItem(int i) {
				return fragments.get(i);
			}

			@Override
			public int getCount() {
				return fragments.size();
			}
		});
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
		kikyou.setTextColor(Color.rgb(255, 255, 255));
		contacts.setTextColor(Color.rgb(255,255,255));
		find.setTextColor(Color.rgb(255,255,255));
		me.setTextColor(Color.rgb(255,255,255));
	}
}
