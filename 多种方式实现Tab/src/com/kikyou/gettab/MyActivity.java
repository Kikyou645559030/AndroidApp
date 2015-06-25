package com.kikyou.gettab;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.kikyou.gettab.common.BaseActivity;
import com.kikyou.gettab.fragmenttab.FragmentAdapterTabActivity;
import com.kikyou.gettab.viewpagertab.ViewPagerTabActivity;

public class MyActivity extends BaseActivity {

	private Button one;
	private Button two;
	private Button three;
	private Button four;

	@Override
	protected void initView() {
		setContentView(R.layout.main);
	}

	@Override
	protected void initData() {
		one = (Button) findViewById(R.id.tab_one);
		two = (Button) findViewById(R.id.tab_two);
		three = (Button) findViewById(R.id.tab_three);
		four = (Button) findViewById(R.id.tab_four);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
	}

	/**
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tab_one:
				startActivity(new Intent(this, ViewPagerTabActivity.class));
				break;
			case R.id.tab_two:
				break;
			case R.id.tab_three:
				startActivity(new Intent(this, FragmentAdapterTabActivity.class));
				break;
			case R.id.tab_four:
				break;
		}
	}
}
