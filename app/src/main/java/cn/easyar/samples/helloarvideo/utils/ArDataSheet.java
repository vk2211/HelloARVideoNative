/******************************************************************************
 * @project CvArDemo
 * @brief
 * @author yaochuan
 * @module com.yipai.ardemo.utils
 * @date 2017/3/8
 * @version 0.1
 * @history v0.1, 2017/3/8, by yaochuan
 * <p>
 * Copyright (C) 2017
 ******************************************************************************/
package cn.easyar.samples.helloarvideo.utils;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaochuan on 2017/3/8.
 */
public class ArDataSheet extends BaseDataShare {
	private static final String TAG = ArDataSheet.class.getSimpleName();
	private static final String PREFIX_FILE = "name";
	private static final String PREFIX_VIDEO = "video";
	Map<String, String> result;

	public ArDataSheet(Context context) {
		super(context);
	}

	public void add(String path, String url) {
		for (int i = 1; i < 100; i++) {
			if (!contains(PREFIX_FILE + i)) {
				addData(PREFIX_FILE + i, path);
				addData(PREFIX_VIDEO + i, url);
				break;
			}
		}
	}

	public Map<String, String> read() {
		if (result == null) {
			result = new HashMap<>();
			for (int i = 1; i < 100; i++) {
				if (contains(PREFIX_FILE + i)) {
					result.put(getData(PREFIX_FILE + i), getData(PREFIX_VIDEO + i));
				} else {
					break;
				}
			}
		}
		return result;
	}

	public String get(String key) {
		read();
		return result.get(key);
	}
}
