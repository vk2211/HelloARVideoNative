/******************************************************************************
 * @file FileUtilBase.java
 * @brief
 * @author YaoChuan (vk2211@gmail.com)
 * @module com.trustworthy.wineplatformapp.util
 * @date 2014.11月1日
 * @version 0.1
 * @history v0.1, 2014.11.1, by YaoChuan (vk2211@gmail.com)
 * <p>
 * <p>
 * Copyright (C) 2014 YaoChuan.
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.easyar.samples.helloarvideo.utils.log.CLog;


abstract class FileUtilBase {
	private static final String TAG = FileUtilBase.class.getSimpleName();
	private static final int SIZE1K = 1024;

	/**
	 * Create file(fileName) in the directory(path). Try like this:
	 * createFile("/sdcard/abc/", "001/002/xyz.txt")
	 *
	 * @param dir      the directory to create file
	 * @param fileName file name
	 * @return File class for the created file
	 * @throws IOException
	 */
	public File createFile(String dir, String fileName) {
		createDir(dir);
		String fullpath = getFullPath(dir, fileName);
		CLog.d(TAG, fullpath);
		String fulldir = getDirectoryPath(fullpath, false);
		if (!isFileExist(fulldir, null)) {
			File fileDir = new File(fulldir);
			fileDir.mkdirs();
		}
		File file = new File(fullpath);
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create file with specified size, fill with specified byte
	 *
	 * @param dir
	 * @param fileName
	 * @param length
	 * @return
	 */
	public File createFile(String dir, String fileName, long length, byte fillbyte) {
		File file = createFile(dir, fileName);
		byte[] buffer = new byte[SIZE1K];
		OutputStream output = null;
		initBuffer(buffer, fillbyte);
		try {
			output = new FileOutputStream(file);
			long chunk = length / SIZE1K;
			long remain = length % SIZE1K;
			for (int i = 0; i < chunk; i++) {
				output.write(buffer);
			}
			output.write(buffer, 0, (int) remain);
			output.flush();
		} catch (FileNotFoundException e) {
			CLog.e(TAG, "FileNotFoundException:" + e.toString());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			CLog.e(TAG, "IOException:" + e.toString());
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return file;
	}

	public abstract String createDir(String dirName);

	public abstract String getFullPath(String dir, String fileName);

	/**
	 * Create file with specified size, fill with specified byte
	 *
	 * @param dir
	 * @param fileName
	 * @param length
	 * @return
	 */
	public File createFile(String dir, String fileName, long length) {
		return createFile(dir, fileName, length, (byte) '\0');
	}

	/**
	 * Whether the path specified is Directory
	 *
	 * @param path full path of the file/dir
	 * @return true: the path is dir
	 */
	public static boolean isDirectory(String path) {
		File file = new File(path);
		return file.exists() && file.isDirectory();
	}

	/**
	 * Get the file name from a full path. The file name is usually after the last '/'
	 *
	 * @param path the full path of the file
	 * @return file name
	 */
	public static String getFileName(String path) {
		int start = path.lastIndexOf('/') + 1;
		String name = path.substring(start);
		return name;
	}

	/**
	 * Get the deepest directory of the file.
	 *
	 * @param path         the full path of the file
	 * @param endWithSlash whether the result end with a slash("/")
	 * @return the deepest directory of the file, if the path is a directory, return the name of itself
	 */
	public static String getDirectoryPath(String path, boolean endWithSlash) {
		int end = 0;
		if (isDirectory(path)) {
			if (path.endsWith("/")) {
				end = path.lastIndexOf('/');
			} else {
				if (endWithSlash) {
					return path + "/";
				} else {
					return path;
				}
			}
		} else {
			end = path.lastIndexOf('/');
		}
		if (endWithSlash) {
			end++;
		}
		String dir = path.substring(0, end);
		return dir;
	}

	/**
	 * In the dir(path), find file(fileName)
	 *
	 * @param path     the dir where to check the file
	 * @param fileName file name
	 * @return if the file with given name fileName exist in the dir, return true
	 */
	public boolean isFileExist(String path, String fileName) {
		File file = new File(getFullPath(path, fileName));
		return file.exists();
	}

	/**
	 * Remove file
	 *
	 * @param path
	 * @param fileName
	 * @return exist & removed, return true
	 */
	public boolean remove(String path, String fileName) {
		File file = new File(getFullPath(path, fileName));
		if (file.exists()) {
			delete(file);
			return true;
		}
		return false;
	}

	private static void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}

			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}

	public File write(String path, String fileName, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			file = createFile(path, fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((input.read(buffer)) != -1) {
				output.write(buffer);
			}
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private static void initBuffer(byte[] buffer, byte fill) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = fill;
		}
	}
}
