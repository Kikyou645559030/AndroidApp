package com.kikyou.showpic;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MyActivity extends Activity {

	private int[] ids = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6};
	private PicChange picChange;
	private RadioGroup rg;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		picChange = (PicChange) findViewById(R.id.pc);
		rg = (RadioGroup) findViewById(R.id.rg);

		// 初始化ImageView
		for (int i = 0; i < 6; i++) {
			ImageView iv = new ImageView(getApplicationContext());
			iv.setImageResource(ids[i]);
			picChange.addView(iv);
		}

		View view = View.inflate(getApplicationContext(), R.layout.view, null);
		picChange.addView(view, 2);// 添加view对象

		for (int i = 0; i < picChange.getChildCount(); i++) {
			RadioButton rb = new RadioButton(this);
			rb.setId(i);
			rg.addView(rb);
			if (i == 0) {
				rb.setChecked(true);
			}
		}

		picChange.setListener(new PicChange.OnPageChangedListener() {
			/**
			 * 当界面切换的时候 会调用onChange方法
			 * @param index 图片下标
			 */
			@Override
			public void onChange(int index) {
				RadioButton button = (RadioButton) rg.getChildAt(index);
				button.setChecked(true);
			}
		});

		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			/**
			 * 当选中孩子 RadioButton 发生变化的时候会调用
			 * @param group
			 * @param checkedId
			 */
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				picChange.moveToIndex(checkedId);// 因为孩子的id 就是0-7  正好和index对应
			}
		});
	}
}
