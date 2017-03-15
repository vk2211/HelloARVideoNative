/******************************************************************************
 * @project Zebra
 * @brief
 * @author yaochuan
 * @module com.ezebra.zebra.utils.file
 * @date 2016/7/17
 * @version 0.1
 * @history v0.1, 2016/7/17, by yaochuan
 * <p/>
 * Copyright (C) 2016
 ******************************************************************************/
package cn.easyar.samples.helloarvideo.utils.file;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by yaochuan on 2016/7/17.
 */
public class SdcardFileUtil extends FileUtilBase {
	private static final String TAG = SdcardFileUtil.class.getSimpleName();
	private static String s_SdcardPath = Environment.getExternalStorageDirectory().toString() + "/";

	SdcardFileUtil() {
	}

	public static String getSdcardRootPath() {
		return s_SdcardPath;
	}

	/**
	 * Create a directory on SDcard
	 *
	 * @param dirName
	 * @return
	 */
	@Override
	public String createDir(String dirName) {
		File dir;
		String fullpath;
		if (dirName.startsWith(s_SdcardPath)) {
			dir = new File(dirName);
			fullpath = getDirectoryPath(dirName + "/", false);
		} else {
			dir = new File(s_SdcardPath + dirName);
			fullpath = getDirectoryPath(s_SdcardPath + dirName + "/", true);
		}
		dir.mkdirs();
		if (!fullpath.equals(dir.getAbsolutePath())) {
			Log.e(TAG, "fullpath != dir.getAbsolutePath()");
		}
		return fullpath;
	}

	/**
	 * Get the full path for the specified dir & fileName
	 *
	 * @param dir      directory, either ends with "/" or not is ok
	 * @param fileName file name
	 * @return if fileName is blank, return directory(ends with "/"),
	 * else return the full path for fileName in the directory
	 */
	@Override
	public String getFullPath(String dir, String fileName) {
		String sep = "";
		if (!dir.endsWith("/")) {
			sep = "/";
		}
		String pre = "";
		if (!dir.startsWith(s_SdcardPath)) {
			pre = s_SdcardPath;
		}
		String name = fileName;
		if (fileName == null) {
			name = "";
		}
		return pre + dir + sep + name;
	}
}
