package com.example.plframework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.plframwork.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import dalvik.system.DexClassLoader;

public class MainActivity extends ListActivity {

	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	public Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			AssetManager asset = getAssets();
			for (String s : asset.list("apks")) {
				addItem(s, "apks/" + s);
			}
		} catch (Exception e) {
		}

		SimpleAdapter adapter = new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 });
		setListAdapter(adapter);
	}

	private void addItem(String title, String path) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("path", path);
		data.add(map);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Map<String, String> item = data.get(position);
		String title = item.get("title");
		String path = item.get("path");

		try {
			File dexInternalStoragePath = getDir("dex", Context.MODE_PRIVATE);
			dexInternalStoragePath.mkdirs();
			File f = new File(dexInternalStoragePath, title);
			InputStream fis = getAssets().open(path);
			FileOutputStream fos = new FileOutputStream(f);
			byte[] buffer = new byte[0xFF];
			int len;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fis.close();
			fos.close();

			File optimizedDexPath = getDir("outdex", Context.MODE_PRIVATE);
			optimizedDexPath.mkdirs();
			DexClassLoader dcl = new DexClassLoader(f.getAbsolutePath(),
					optimizedDexPath.getAbsolutePath(), null,
					MyApplication.ORIGINAL_LOADER.getParent());
			MyApplication.CUSTOM_LOADER = dcl;

			Toast.makeText(this, title + " loaded, try launch again",
					Toast.LENGTH_SHORT).show();
			//mIntent = new Intent("com.test.intent.action.Mytest");
		} catch (Exception e) {
			Toast.makeText(this, "Unable to load " + title, Toast.LENGTH_SHORT)
					.show();
			e.printStackTrace();
			MyApplication.CUSTOM_LOADER = null;
		}
		mIntent = new Intent("com.example.intent.action.stub_ACTIVITY");
		startActivity(mIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
