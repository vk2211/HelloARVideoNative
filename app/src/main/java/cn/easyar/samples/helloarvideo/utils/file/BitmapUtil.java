package cn.easyar.samples.helloarvideo.utils.file;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.easyar.samples.helloarvideo.utils.log.CLog;

/**
 * Created by liuchuanliang on 2017/3/16.
 */
public class BitmapUtil {


            public static final String DIR = "Yipai/ArDemo";
            public static final String CACHE_DIR="Yipai/Cache";
            private Context mContext;

            public  BitmapUtil(Context context){

                        mContext=context;
            }



            public void saveBitmap(Bitmap bitmap, String path) {
                        try {
                                    File file = new File(path);
                                    FileOutputStream fos = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			bitmap.recycle();
                                    fos.close();
                        } catch (FileNotFoundException e) {
                                    CLog.e(mContext.toString(), "File not found: " + e.getMessage());
                        } catch (IOException e) {
                                    CLog.e(mContext.toString(), "Error accessing file: " + e.getMessage());
                        }
                        String dir = FileUtil.getDirectoryPath(DIR, false);
                        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(FileUtil.sdcard.createDir(DIR)))));
            }
}
