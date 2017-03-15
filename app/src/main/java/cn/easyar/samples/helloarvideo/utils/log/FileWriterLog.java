/******************************************************************************
 * @file FileWriterLog.java
 * @brief
 * @author yaochuan (vk2211@gmail.com)
 * @module CLog
 * @date 2015年9月20日
 * @version 0.1
 * @history v0.1, 2015年9月20日, by yaochuan
 * <p>
 * <p>
 * Copyright (C)
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.log;

import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;


public class FileWriterLog extends FileLog {
	private FileWriter mFileWriter;

	/**
	 * Initialize file writer. destory() SHOULD also be call after used
	 *
	 * @param filepath full path of log file
	 * @param onlytime only need time or also need date
	 */
	@Override
	public boolean init(String filepath, boolean onlytime) {
		super.init(filepath, onlytime);
		try {
			if (null != mFileWriter) {
				mFileWriter.close();
				mFileWriter = null;
				Log.d(TAG, "init() 'null != mFileWriter'  close and set null mFileWriter");
			}
			mFileWriter = new FileWriter(filepath);
		} catch (IOException e) {
			String msg = e.getMessage();
			Log.e(TAG, "init() IOException:" + msg);
			e.printStackTrace();
			return false;
		}
		mOnlyTime = onlytime;
		return true;
	}

	/**
	 * After used, the file stream have to be detroyed
	 */
	public void destroy() {
		if (null == mFileWriter) {
			Log.d(TAG, "destroy() 'null == mFileWriter' ,return... ");
			return;
		}

		try {
			mFileWriter.close();
		} catch (IOException e) {
			String msg = e.getMessage();
			Log.e(TAG, "destroy() IOException:" + msg);
			e.printStackTrace();
		}
	}

	/**
	 * @param level 0:C 1:E 2:W 3:I 4:D
	 * @param tag   tag
	 * @param msg   message body
	 * @return log length
	 */
	protected int log(int level, String tag, String msg) {
		final String[] strLevel = {
			"C/", "E/", "W/", "I/", "D/", "V/"
		};
		long time = TimeUtil.getCurrentTime();
		String strTime = mOnlyTime ? TimeUtil.getTimeStr(time) : TimeUtil.getDateTimeStr(time);
		String sLevel = strLevel[level];
		String blank = " ";
		String sTag = tag;
		String sT = "\t";
		String strMsg = msg;
		String sN = "\n";
		StringBuilder sb = new StringBuilder();
		sb.append(sLevel);
		sb.append(strTime);
		sb.append(blank);
		sb.append(sTag);
		sb.append(sT);
		sb.append(strMsg);
		sb.append(sN);
		String content = sb.toString();
		try {
			if (null != mFileWriter) {
				mFileWriter.write(content);
				mFileWriter.flush();
			} else {
				Log.d(TAG, "log() mFileWriter is null,content:" + content);
			}
		} catch (IOException e) {
			String c = (null == e) ? "null" : e.getMessage();
			Log.e(TAG, "log() IOException:" + c);
			e.printStackTrace();
		}
		return sb.length();
	}
}
