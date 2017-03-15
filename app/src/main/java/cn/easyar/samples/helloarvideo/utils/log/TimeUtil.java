/******************************************************************************
 * @file TimeUtil.java
 * @brief DO NOT USE THE CLOG CLASS IN THIS SCOPE
 * @author YaoChuan (vk2211@gmail.com)
 * @date 2016.4.19
 * @version 0.1
 * @history v0.1, 2014.8.25, by YaoChuan
 * @history v1.0, 2016.4.19, by YaoChuan
 * <p>
 * Copyright (C) 2014
 ******************************************************************************/

package cn.easyar.samples.helloarvideo.utils.log;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TimeUtil {
	private static final String TAG = "TimeUtil";
	public static final String strTimeFormat = "HH:mm:ss";
	public static final String strDateFormat = "yyyy-MM-dd";
	public static final String strDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(strTimeFormat, Locale.getDefault());
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat, Locale.getDefault());
	private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(strDateTimeFormat, Locale.getDefault());

	/**
	 * Get local time whose format is yyyy-MM-dd HH:mm:ss.
	 *
	 * @return
	 */
	public static String getCurrentTimeStr() {
		return getDateTimeStr(getCurrentTime());
	}

	public static String getTimeStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleTimeFormat.format(date);
	}

	public static String getDateStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	public static String getYearStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("yyyy", Locale.getDefault()).format(date);
	}

	public static String getMonthStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("MM", Locale.getDefault()).format(date);
	}

	public static String getDayStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return new SimpleDateFormat("dd", Locale.getDefault()).format(date);
	}

	public static int getYear(long time) {
		return Integer.parseInt(getYearStr(time));
	}

	public static int getMonth(long time) {
		return Integer.parseInt(getMonthStr(time));
	}

	public static int getDay(long time) {
		return Integer.parseInt(getDayStr(time));
	}

	public static String getDateTimeStr(long time) {
		Date date = new Date();
		date.setTime(time);
		return simpleDateTimeFormat.format(date);
	}

	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static long parseTimeStr(String timeStr) {
		return parseTimeStr(timeStr, simpleTimeFormat);
	}

	public static long parseDateStr(String dateStr) {
		return parseTimeStr(dateStr, simpleDateFormat);
	}

	public static long parseDateTimeStr(String datetimeStr) {
		return parseTimeStr(datetimeStr, simpleDateTimeFormat);
	}

	public static long parseTimeStr(String timeStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
		return parseTimeStr(timeStr, df);
	}

	public static long parseTimeStr(String timeStr, SimpleDateFormat df) {
		Date date;
		long time = 0;
		try {
			date = df.parse(timeStr);
			time = date.getTime();
		} catch (ParseException e) {
			Log.e(TAG, "Cannot parse the time string!!");
			time = getCurrentTime();
			e.printStackTrace();
		}
		return time;
	}
}
