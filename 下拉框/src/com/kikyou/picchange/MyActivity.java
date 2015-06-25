package com.kikyou.picchange;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉框
 */
public class MyActivity extends Activity {

    private EditText input;
    private ImageView select;
    private List<String> users = new ArrayList<String>();
    private PopupWindow window;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        input = (EditText) findViewById(R.id.et_input);
        select = (ImageView) findViewById(R.id.iv_select);

        for (int i = 0; i < 100; i++) {
            users.add("Kikyou,youcandoit" + i);
        }

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView view = new ListView(getApplicationContext());
                view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        input.setText(users.get(position));
                        window.dismiss();
                    }
                });
                view.setAdapter(new MyAdapter());
                window = new PopupWindow(view,input.getWidth(),300,true);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.showAsDropDown(input);//设置显示位置，在某个控件下面显示
                window.setOutsideTouchable(true);//默认false，true表示允许PopupWindow以外的地方获取焦点
            }
        });
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return users.size();
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null){
                view = View.inflate(getApplicationContext(),R.layout.item,null);
            }else {
                view = convertView;
            }
            TextView user = (TextView) view.findViewById(R.id.tv_user);
            user.setText(users.get(position));
            view.findViewById(R.id.iv_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users.remove(position);
                    MyAdapter.this.notifyDataSetChanged();
                }
            });
            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
