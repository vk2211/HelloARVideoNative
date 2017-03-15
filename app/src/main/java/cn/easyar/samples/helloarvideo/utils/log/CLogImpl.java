/******************************************************************************
 * @file CLogImpl.java
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

import android.util.Log;


/**
 *
 * This is a class that package a Log class for implementing CLog
 *
 * @author yaochuan
 *
 *
 */
public class CLogImpl extends CLog {
	/*
	 * (non-Javadoc)
	 * 
	 * @see CLog#verbose(java.lang.String, java.lang.String)
	 */
	@Override
	protected int verbose(String tag, String msg) {
		return Log.v(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CLog#debug(java.lang.String, java.lang.String)
	 */
	@Override
	protected int debug(String tag, String msg) {
		return Log.d(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CLog#info(java.lang.String, java.lang.String)
	 */
	@Override
	protected int info(String tag, String msg) {
		return Log.i(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CLog#warning(java.lang.String, java.lang.String)
	 */
	@Override
	protected int warning(String tag, String msg) {
		return Log.w(CLTAG + tag, msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CLog#error(java.lang.String, java.lang.String)
	 */
	@Override
	protected int error(String tag, String msg) {
		return Log.e(CLTAG + tag, msg);
	}
}
