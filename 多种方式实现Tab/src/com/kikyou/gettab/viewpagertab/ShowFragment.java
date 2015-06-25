package com.kikyou.gettab.viewpagertab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kikyou.gettab.R;

/**
 * fragment
 * Created by Liu_Zhichao on 2015/3/11 0011.
 */
public class ShowFragment extends Fragment {

	private String content;

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pager_kikyou,container,false);
		TextView pager_content = (TextView) view.findViewById(R.id.pager_content);
		pager_content.setText(content);
		container.addView(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
