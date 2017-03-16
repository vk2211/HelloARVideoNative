/**
 * Copyright (c) 2015-2016 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
 * EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
 * and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
 */

package cn.easyar.samples.helloarvideo.ui;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.Map;
import java.util.Set;

import cn.easyar.engine.EasyAR;
import cn.easyar.samples.helloarvideo.R;
import cn.easyar.samples.helloarvideo.utils.ArDataSheet;


public class MainActivity extends Activity {

	/*
	* Steps to create the key for this sample:
	*  1. login www.easyar.com
	*  2. create app with
	*      Name: HelloARVideo
	*      Package Name: cn.easyar.samples.helloarvideo
	*  3. find the created item in the list and show key
	*  4. set key string bellow
	*/
	static String key = "H01pOZpUyzg68CDUPnr8vJCf0GJyDTFwmePbvzKwQFAo8KIyPTGYoo39sNUotCE5hMRlhlcdWpU86BhvR4bHdi5mTMgjMvgg8gWpefe54f7f9518f680b5015f091e1084ackalLvhamePhWZ0qgFpdyaky9Nx5BbHAWl5qw9IftFgkZhlqs1Dshd0HuKJRaFBM1xDvD";

	static {
		System.loadLibrary("EasyAR");
		System.loadLibrary("HelloARVideoNative");
	}

	public static native void nativeInitGL();

	public static native void nativeResizeGL(int w, int h);

	public static native void nativeRender();

	private native boolean nativeInit();

	private native void nativeDestory();

	private native void nativeRotationChange(boolean portrait);

	public static native void nativeAdd(String s1, String s2);

	private ArDataSheet mArDataSheet;
	private Map<String, String> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		mArDataSheet = new ArDataSheet(this);
		map = mArDataSheet.read();
		Set<String> set = map.keySet();
		for (String path : set) {
			String uri = mArDataSheet.get(path);
			String p = path.replace("/storage/emulated/0", "/sdcard");
//			String u = uri.replace("/storage/emulated/0", "/sdcard");
			nativeAdd(p, uri);

		}

		EasyAR.initialize(this, key);
		nativeInit();

		GLView glView = new GLView(this);
		glView.setRenderer(new Renderer());
		glView.setZOrderMediaOverlay(true);

		((ViewGroup) findViewById(R.id.preview)).addView(glView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		nativeRotationChange(getWindowManager().getDefaultDisplay().getRotation() == android.view.Surface.ROTATION_0);
	}

	@Override
	public void onConfigurationChanged(Configuration config) {
		super.onConfigurationChanged(config);
		nativeRotationChange(getWindowManager().getDefaultDisplay().getRotation() == android.view.Surface.ROTATION_0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		nativeDestory();
	}

	@Override
	protected void onResume() {
		super.onResume();
		EasyAR.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		EasyAR.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
