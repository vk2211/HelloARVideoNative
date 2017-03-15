package cn.easyar.samples.helloarvideo.utils.shotbitmap;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import cn.easyar.samples.helloarvideo.StartActivity;
import cn.easyar.samples.helloarvideo.utils.file.BitmapUtil;

/**
 * Created by liuchuanliang on 2017/3/15.
 */
public class ShotCurrentView {

            public static String shot(View view) {
                        view.buildDrawingCache();
                        view.setDrawingCacheEnabled(true);
                        Bitmap bmp = view.getDrawingCache();
                        BitmapUtil.saveARBitmap(bmp,BitmapUtil.CacheImageName);
                        String BitmapPath= Environment.getExternalStorageDirectory()+ "/"+StartActivity.DIR+"/"+BitmapUtil.CacheImageName;
                        view.destroyDrawingCache();
                        return BitmapPath;
            }
}
