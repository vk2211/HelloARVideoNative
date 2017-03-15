package cn.easyar.samples.helloarvideo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by epai on 17/3/7.
 */

public class BaseDataShare {
	private SharedPreferences mSharedPreferences;
	private SharedPreferences.Editor editor;
	private Context mContext;
	private static BaseDataShare mDataServices = null;

	public BaseDataShare(Context context) {
		mContext = context;
		mSharedPreferences = mContext.getSharedPreferences("DataServices", Activity.MODE_PRIVATE);
		//实例化SharedPreferences.Editor对象
		editor = mSharedPreferences.edit();
	}

	/**
	 * 获得DataServices对象
	 *
	 * @param context
	 * @return
	 */
	public static BaseDataShare get(Context context) {
		if (mDataServices == null) {
			synchronized (BaseDataShare.class) {
				if (mDataServices == null) {
					mDataServices = new BaseDataShare(context);
				}
			}
		}

		return mDataServices;
	}

	/**
	 * @param key   key值,留作索引
	 * @param value
	 */
	public void setData(String key, String value) {
		editor.putString(key, value);
		editor.apply();
		editor.commit();
	}

	public void addData(String key, String value) {
		editor.putString(key, value);
		editor.apply();
		editor.commit();
	}

	public String getData(String key) {
		return mSharedPreferences.getString(key, "");
	}

	protected boolean contains(String key) {
		return mSharedPreferences.contains(key);
	}


	void enumKeys() {
		Map map = mSharedPreferences.getAll();
		Set set = map.entrySet();
	}
}
