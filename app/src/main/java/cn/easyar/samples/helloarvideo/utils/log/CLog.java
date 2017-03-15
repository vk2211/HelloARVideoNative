/******************************************************************************
 * @file CLog.java
 * @brief
 * @author yaochuan (vk2211@gmail.com)
 * @module CLog
 * @date 2015年8月25日
 * @version 0.1
 * @history v0.1, 2015年8月25日, by yaochuan
 * <p>
 * <p>
 * Copyright (C)
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.log;

/**
 * This is the base class for Log in projects. This class support original log in android & file log
 *
 * @author yaochuan
 */
public abstract class CLog {
	protected static String CLTAG = "CLog:";
	private static CLog sInstance = new NoLog();

	public CLog() {
	}

	/**
	 * Use android original log. If this function not called, there may be NO log.
	 */
	public static void open() {
		sInstance = new CLogImpl();
	}

	/**
	 * Use both android original log & file log. If this function not called, there may be NO log.
	 *
	 * @see this.close()
	 */
	public static void open(String filepath) {
		open(filepath, true);
	}

	/**
	 * Use both android original log & file log. If this function not called, there may be NO log.
	 *
	 * @see this.close()
	 */
	public static void open(String filepath, boolean onlytime) {
		CLog nolog = sInstance; // keep instance, if file log instance not created, this will be restored
		sInstance = new CLogImplEx();
		// if log file cannot be created, ignore file log
		if (!CLogImplEx.init(filepath, onlytime)) {
			sInstance = nolog; // restore no log instance, with less memory assignment
		}
	}

	public static void setGlobalTag(String tag) {
		CLTAG = tag;
	}

	/**
	 * If using file log, memory recyle is necessary. Before stop logging, close() must be called.
	 *
	 * @see this.open()
	 */
	public static void close() {
		if (sInstance instanceof CLogImplEx) {
			CLogImplEx.destory();
		}
	}

	public static void v(String tag, String msg) {
		sInstance.verbose(tag, msg);
	}

	public static void d(String tag, String msg) {
		sInstance.debug(tag, msg);
	}

	public static void i(String tag, String msg) {
		sInstance.info(tag, msg);
	}

	public static void w(String tag, String msg) {
		sInstance.warning(tag, msg);
	}

	public static void e(String tag, String msg) {
		sInstance.error(tag, msg);
	}

	protected abstract int verbose(String tag, String msg);

	protected abstract int debug(String tag, String msg);

	protected abstract int info(String tag, String msg);

	protected abstract int warning(String tag, String msg);

	protected abstract int error(String tag, String msg);
}
