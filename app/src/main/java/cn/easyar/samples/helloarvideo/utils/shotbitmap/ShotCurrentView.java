package cn.easyar.samples.helloarvideo.utils.shotbitmap;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by liuchuanliang on 2017/3/15.
 */
public class ShotCurrentView {

            public static Bitmap shot(View view) {
                        view.buildDrawingCache();
                        view.setDrawingCacheEnabled(true);

                        Bitmap bmp = view.getDrawingCache();
                        view.destroyDrawingCache();
                        return bmp;
            }
}
