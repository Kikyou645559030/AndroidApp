package com.kikyou.onoff;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends Activity {
	private OnOff oo;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		oo = (OnOff) findViewById(R.id.oo);
		oo.setListener(new OnOff.OnStateChangedListener() {
			@Override
			public void changed(boolean isOpen) {
				if (isOpen) {
					Toast.makeText(getApplicationContext(),"当前状态开",Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(),"当前状态关",Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
