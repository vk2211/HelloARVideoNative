package cn.easyar.samples.helloarvideo.utils.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by liuchuanliang on 2017/3/14.
 */
public class DialogServer {

            private MydialogInterface mDialogInterface;
            private Activity mActivity;
            private int yourChoice = 0;

            public DialogServer(Activity activity,MydialogInterface mydialogInterface){

                        mDialogInterface=mydialogInterface;
                        mActivity=activity;
            }


            public void  showSingleChoiceDialog(){
                        final String[] items = { "本地视频","网络视频" };
                        AlertDialog.Builder singleChoiceDialog = new AlertDialog.Builder(mActivity);

                        singleChoiceDialog.setTitle("视频选择");
                        // 第二个参数是默认选项，此处设置为0
                        singleChoiceDialog.setSingleChoiceItems(items, 0,
                                new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                        yourChoice=which;

                                            }
                                });
                        singleChoiceDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                        mDialogInterface.OnDoalogDismiss(items[yourChoice]);
                                                        yourChoice=0;

                                            }});
                        singleChoiceDialog.show();

            }

            public interface MydialogInterface {
                        public void OnDoalogDismiss(String result);
            }
}
