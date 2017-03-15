package cn.easyar.samples.helloarvideo.utils.log;

import android.util.Log;

import java.io.File;

/**
 * Created by yaochuan on 2016/4/19.
 */
public abstract class FileLog extends CLog {
	private static final String TAG_HEADER = "CLog";
	private static final String TAG_BODY = "FileStreamLog";
	protected static final String TAG = TAG_HEADER + ":" + TAG_BODY;

	protected static final int LOG_LEVEL_CRITICAL = 0;
	protected static final int LOG_LEVEL_ERROR = 1;
	protected static final int LOG_LEVEL_WARNING = 2;
	protected static final int LOG_LEVEL_INFO = 3;
	protected static final int LOG_LEVEL_DEBUG = 4;
	protected static final int LOG_LEVEL_VERBOSE = 5;

	protected boolean mOnlyTime = true;

	/**
	 * Initialize file stream. destory() SHOULD also be called after used
	 *
	 * @param filepath full path of log file
	 * @param onlytime only need time or also need date
	 */
	public boolean init(String filepath, boolean onlytime) {
		Log.d(TAG, "init()");
		//重新操作之前，删除旧文件
		File file = new File(filepath);
		if (file.exists() && file.isFile()) {
			Log.d(TAG, "init() '(file.exists() && file.isFile())' delete file ");
			file.delete();
			Log.d(TAG, "init() delete file ended...");
		}
		mOnlyTime = onlytime;
		return true;
	}

	/**
	 * After used, the file stream have to be detroyed
	 */
	public abstract void destroy();

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#verbose(java.lang.String, java.lang.String)
	 */
	@Override
	protected int verbose(String tag, String msg) {
		return log(LOG_LEVEL_VERBOSE, tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#debug(java.lang.String, java.lang.String)
	 */
	@Override
	protected int debug(String tag, String msg) {
		return log(LOG_LEVEL_DEBUG, tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#info(java.lang.String, java.lang.String)
	 */
	@Override
	protected int info(String tag, String msg) {
		return log(LOG_LEVEL_INFO, tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#warning(java.lang.String, java.lang.String)
	 */
	@Override
	protected int warning(String tag, String msg) {
		return log(LOG_LEVEL_WARNING, tag, msg);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see CLog#error(java.lang.String, java.lang.String)
	 */
	@Override
	protected int error(String tag, String msg) {
		return log(LOG_LEVEL_ERROR, tag, msg);
	}

	/**
	 * @param level 0:C 1:E 2:W 3:I 4:D
	 * @param tag   tag
	 * @param msg   message body
	 * @return log length
	 */
	protected abstract int log(int level, String tag, String msg);
}
