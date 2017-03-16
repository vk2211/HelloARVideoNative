/******************************************************************************
 * @project Zebra
 * @brief
 * @author yaochuan
 * @module com.ezebra.zebra.utils.file
 * @date 2016/10/19
 * @version 0.1
 * @history v0.1, 2016/10/19, by yaochuan
 * <p>
 * Copyright (C) 2016
 ******************************************************************************/
package cn.easyar.samples.helloarvideo.utils.file;

import java.io.File;
import java.io.IOException;

/**
 * Created by yaochuan on 2016/10/19.
 */
public class FileUtil {
	private static final String TAG = FileUtil.class.getSimpleName();
	public static final SdcardFileUtil sdcard = new SdcardFileUtil();

	public static final String DIR = "Yipai/ArDemo";
	public static final String CACHE_DIR="Yipai/Cache";

	/**
	 * Whether the path specified is Directory
	 *
	 * @param path full path of the file/dir
	 * @return true: the path is dir
	 */
	public static boolean isDirectory(String path) {
		return FileUtilBase.isDirectory(path);
	}

	/**
	 * Get the file name from a full path. The file name is usually after the last '/'
	 *
	 * @param path the full path of the file
	 * @return file name
	 */
	public static String getFileName(String path) {
		return FileUtilBase.getFileName(path);
	}

	/**
	 * Get the deepest directory of the file.
	 *
	 * @param path         the full path of the file
	 * @param endWithSlash whether the result end with a slash("/")
	 * @return the deepest directory of the file, if the path is a directory, return the name of itself
	 */
	public static String getDirectoryPath(String path, boolean endWithSlash) {
		return FileUtilBase.getDirectoryPath(path, endWithSlash);
	}

	public static File createAbsoluteFile(String dir, String fileName) {
		File fileDir = new File(dir);
		fileDir.mkdirs();

		File file = new File(dir, fileName);
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isAbsoluteFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	public static boolean isAbsoluteFileExist(String path, String fileName) {
		File file = new File(path, fileName);
		return file.exists();
	}

	public static boolean removeAbsoluteFile(String path, String fileName) {
		File file = new File(path, fileName);
		if (file.exists()) {
			file.delete();
			return true;
		}
		return false;
	}
}
