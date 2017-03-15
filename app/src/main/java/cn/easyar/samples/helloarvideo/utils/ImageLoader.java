/******************************************************************************
 * @project WinePlatform
 * @brief
 * @author yaochuan
 * @module com.trustworthy.wineplatformapp.util
 * @date 2016-5-10
 * @version 0.1
 * @history v0.1, 2016-5-10, by yaochuan
 * <p>
 * Copyright (C) 2016
 ******************************************************************************/
package cn.easyar.samples.helloarvideo.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import cn.easyar.samples.helloarvideo.R;


/**
 * Created by yaochuan on 2016-5-10.
 */
public class ImageLoader {
	private static final String TAG = ImageLoader.class.getSimpleName();
	public static final int DEFAULT_RESID = R.drawable.refreshing;

	/**
	 * 显示网络图片到本地的控件上
	 *
	 * @param context
	 * @param urlPath      图片地址
	 * @param imageView    控件
	 * @param resIdDefault 未完成加载的图片显示
	 * @param resIdError   加载出错的图片显示
	 */
	public static void showImageView(Context context, String urlPath, ImageView imageView, int resIdDefault, int resIdError, Callback callback) {
		if (callback != null) {
			Picasso.with(context).load(urlPath).placeholder(resIdDefault).error(resIdError).into(imageView);
		}
		Picasso.with(context).load(urlPath).placeholder(resIdDefault).error(resIdError).into(imageView, callback);
	}

//	public static void showImageView(Context context, String urlPath, ImageView imageView, int resIdDefault, int resIdError,RequestListener requestListener) {
//		if(requestListener==null) {
//			Glide.with(context).load(urlPath).placeholder(resIdDefault).error(resIdError).into(imageView);
//		}else {
//			Glide.with(context).load(urlPath).placeholder(resIdDefault).error(resIdError).listener(requestListener).into(imageView);
//		}
//	}
	/**
	 * 显示网络图片到本地的控件上
	 *
	 * @param context
	 * @param urlPath   图片地址
	 * @param imageView 控件
	 * @param defImgResId     失败或者未加载成功的图片资源
	 * @param callback  加载结束的回调
	 */
//	public static void showImageView(Context context, String urlPath, ImageView imageView, int defImgResId, Callback callback) {
//		showImageView(context, urlPath, imageView, defImgResId, defImgResId, callback);
//	}

	/**
	 * 显示网络图片到本地的控件上
	 *
	 * @param context
	 * @param urlPath   图片地址
	 * @param imageView 控件
	 * @param defImgResId     失败或者未加载成功的图片资源
	 */
	public static void showImageView(Context context, String urlPath, ImageView imageView, int defImgResId) {
		showImageView(context, urlPath, imageView, defImgResId, defImgResId, null);
	}

	/**
	 * 显示网络图片到本地的控件上
	 *
	 * @param context
	 * @param urlPath   图片地址
	 * @param imageView 控件
	 * @param callback  加载结束的回调
	 */
//	public static void showImageView(Context context, String urlPath, ImageView imageView, Callback callback) {
//		showImageView(context, urlPath, imageView, DEFAULT_RESID, callback);
//	}

	/**
	 * 显示网络图片到本地的控件上
	 *
	 * @param context
	 * @param urlPath   图片地址
	 * @param imageView 控件
	 */
	public static void showImageView(Context context, String urlPath, ImageView imageView) {
		showImageView(context, urlPath, imageView, DEFAULT_RESID);
	}

	public static void showCateImageView(Context context, @NonNull String urlPath, ImageView imageView){
		showImageView(context, urlPath, imageView, R.drawable.tasklist_default);
	}
}
