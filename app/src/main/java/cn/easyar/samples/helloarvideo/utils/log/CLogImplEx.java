/******************************************************************************
 * @file CLogImplEx.java
 * @brief
 * @author yaochuan (vk2211@gmail.com)
 * @module CLog
 * @date 2015年9月21日
 * @version 0.1
 * @history v0.1, 2015年9月21日, by yaochuan
 * <p>
 * <p>
 * Copyright (C)
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.log;


public class CLogImplEx extends CLogImpl {
	// 'FileStreamLog'类有问题，在流量201版中打日志数组越界，这边改成 'FileWriterLog'类来打日志
	//private static FileLog sFileLog = new FileStreamLog();
	private static FileLog sFileLog = new FileWriterLog();

	/**
	 *
	 * @param filepath
	 *            Where to save the log file
	 * @param onlytime
	 *            In a log line, how to show the time, only time or full date&time. True for time, False for date&time
	 */
	public static boolean init(String filepath, boolean onlytime) {
		return sFileLog.init(filepath, onlytime);
	}

	public static void destory() {
		sFileLog.destroy();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#verbose(java.lang.String, java.lang.String)
	 */
	@Override
	protected int verbose(String tag, String msg) {
		super.verbose(tag, msg);
		return sFileLog.verbose(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#debug(java.lang.String, java.lang.String)
	 */
	@Override
	protected int debug(String tag, String msg) {
		super.debug(tag, msg);
		return sFileLog.debug(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#info(java.lang.String, java.lang.String)
	 */
	@Override
	protected int info(String tag, String msg) {
		super.info(tag, msg);
		return sFileLog.info(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#warning(java.lang.String, java.lang.String)
	 */
	@Override
	protected int warning(String tag, String msg) {
		super.warning(tag, msg);
		return sFileLog.warning(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#error(java.lang.String, java.lang.String)
	 */
	@Override
	protected int error(String tag, String msg) {
		super.error(tag, msg);
		return sFileLog.error(CLTAG + tag, msg);
	}
}
