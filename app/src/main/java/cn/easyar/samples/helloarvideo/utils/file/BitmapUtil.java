package cn.easyar.samples.helloarvideo.utils.file;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.easyar.samples.helloarvideo.StartActivity;
import cn.easyar.samples.helloarvideo.utils.log.CLog;

/**
 * Created by liuchuanliang on 2017/3/15.
 */
public class BitmapUtil {
            private static final String TAG = FileUtil.class.getSimpleName();
            public static String CacheImageName="CacheAR.png";



            /**
             * 保存图片
             *
             * @param b           图片资源
             * @param strFileName 图片名称
             * @throws IOException
             */
            public static void saveARBitmap(Bitmap b, String strFileName) {
                        try {
                                    File file = new File(Environment.getExternalStorageDirectory()+ "/"+StartActivity.DIR, strFileName);
                                    if (!file.isDirectory()) {
                                                file.createNewFile();
                                    }
                                    FileOutputStream fos = new FileOutputStream(file);
                                    if (fos != null) {
                                                b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                                                fos.flush();
                                                fos.close();
                                    }
                        } catch (FileNotFoundException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                        } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                        }
            }

            /**
             * 读取图片
             *
             * @param strFileName 图片名称
             * @return 图片内容
             * @throws IOException
             */
            @SuppressWarnings("unused")
            public static  Bitmap readARBitmap(String strFileName) {
                        String path = Environment.getExternalStorageDirectory() +"/"+ StartActivity.DIR+"/" + strFileName;
                        if (path != null) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                                    return bitmap;
                        } else
                                    return null;

            }

            public static String  GetCacheImagePath(String ImageName){

                        return Environment.getExternalStorageDirectory()+ StartActivity.DIR+"/"+ImageName;
            }



}
