/******************************************************************************
 * @file FileStreamLog.java
 * @brief
 * @author yaochuan (vk2211@gmail.com)
 * @module Clog
 * @date 2015年9月20日
 * @version 0.1
 * @history v0.1, 2015年9月20日, by yaochuan
 * <p>
 * <p>
 * Copyright (C)
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.log;

import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;


public class FileStreamLog extends FileLog {
	private static final int LOG_LEVEL_CRITICAL = 0;
	private static final int LOG_LEVEL_ERROR = 1;
	private static final int LOG_LEVEL_WARNING = 2;
	private static final int LOG_LEVEL_INFO = 3;
	private static final int LOG_LEVEL_DEBUG = 4;
	private static final int LOG_LEVEL_VERBOSE = 5;

	private FileOutputStream mFileOutputStream;

	/**
	 * start with "E/... \n"</br>
	 * 1: "D/I/W/E"</br>
	 * 2: "/"</br>
	 * 3: " "</br>
	 * ex: "D/10:00:00 CLTAG	MSG\n", length= 4 + "10:00:00".length + "CLTAG MSG".length;</br>
	 * 4 is "D/ \n".length
	 */
	private int mLogLineBaseLength = 4;

	/**
	 * Initialize file stream. destory() SHOULD also be called after used
	 *
	 * @param filepath full path of log file
	 * @param onlytime only need time or also need date
	 */
	@Override
	public boolean init(String filepath, boolean onlytime) {
		super.init(filepath, onlytime);
		try {
			if (null != mFileOutputStream) {
				mFileOutputStream.close();
				mFileOutputStream = null;
				Log.d(TAG, "init() 'null != mFileOutputStream'  close and set null mFileOutputStream");
			}
			mFileOutputStream = new FileOutputStream(filepath);
		} catch (IOException e) {
			String msg = (null == e) ? "null" : e.getMessage();
			Log.e(TAG, "init() IOException:" + msg);
			e.printStackTrace();
			return false;
		}
		if (onlytime) {
			mLogLineBaseLength += TimeUtil.strTimeFormat.length();
		} else {
			mLogLineBaseLength += TimeUtil.strDateTimeFormat.length();
		}
		return true;
	}

	/**
	 * After used, the file stream have to be detroyed
	 */
	public void destroy() {
		if (null == mFileOutputStream) {
			Log.d(TAG, "destroy() 'null == mFileOutputStream' ,return... ");
			return;
		}
		try {
			mFileOutputStream.close();
		} catch (IOException e) {
			String msg = e.getMessage();
			Log.e(TAG, "destroy() IOException:" + msg);
			e.printStackTrace();
		}
	}

	/**
	 * LOG FORMAT: tag\tmsg\n
	 *
	 * @param tag tag
	 * @param msg message body
	 * @return tag + msg length: tag.length + \t + msg.length
	 */
	private int countLogContentLength(String tag, String msg) {
		return tag.length() + msg.length() + 1;// tag.length + \t + msg.length
	}

	/**
	 * @param level 0:C 1:E 2:W 3:I 4:D
	 * @param tag   tag
	 * @param msg   message body
	 * @return log length
	 */
	protected int log(int level, String tag, String msg) {
		int length = mLogLineBaseLength + countLogContentLength(tag, msg);
		byte[] buffer = new byte[length];
		final String[] strLevel = {
			"C/", "E/", "W/", "I/", "D/", "V/"
		};
		long time = TimeUtil.getCurrentTime();
		String strTime = mOnlyTime ? TimeUtil.getTimeStr(time) : TimeUtil.getDateTimeStr(time);
		int len = 0;

		// for debug:
		len = strLevel[level].length() + strTime.length() + 1 + tag.length() + 1 + msg.length() + 1;
		if (len > length) {
			Log.e(TAG, "len=" + len + ",length=" + length);
		}

		len = 0;
		System.arraycopy(strLevel[level].getBytes(), 0, buffer, len, strLevel[level].length());
		len += strLevel[level].length();
		System.arraycopy(strTime.getBytes(), 0, buffer, len, strTime.length());
		len += strTime.length();
		System.arraycopy(" ".getBytes(), 0, buffer, len, 1);
		len += 1;
		System.arraycopy(tag.getBytes(), 0, buffer, len, tag.length());
		len += tag.length();
		System.arraycopy("\t".getBytes(), 0, buffer, len, 1);
		len += 1;
		System.arraycopy(msg.getBytes(), 0, buffer, len, msg.length());
		len += msg.length();
		System.arraycopy("\n".getBytes(), 0, buffer, len, 1);
		len += 1;
		try {
			if (null != mFileOutputStream) {
				mFileOutputStream.write(buffer);
				mFileOutputStream.flush();
			} else {
				Log.d(TAG, "log() mFileOutputStream is null,buffer:" + buffer.toString());
			}
		} catch (IOException e) {
			String c = e.getMessage();
			Log.e(TAG, "log() IOException:" + c);
			e.printStackTrace();
		}
		return length;
	}
}
