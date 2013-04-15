package com.example.plframework.stub;

import com.example.plframework.ExtPoint.MessageExtPointInterace;
import com.example.pluginactivity.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class stubActivity extends BaseStubActivity implements MessageExtPointInterace{
	private static final String TAG = "stubActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public String getSummary() {
		return "This is plugin summary";
	}

	@Override
	public Bitmap getIconPath() {
		//Log.d(TAG, this.getPackageResourcePath());
		Log.d(TAG, this.getResources().getResourceName(R.drawable.ios));
		return BitmapFactory.decodeResource(this.getResources(), R.drawable.ios);
	}

	@Override
	public String getMsgTitle() {
		return "This is plugin title";
	}

}
