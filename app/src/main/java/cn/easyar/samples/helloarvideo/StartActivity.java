package cn.easyar.samples.helloarvideo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DialogTitle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import cn.easyar.samples.helloarvideo.constant.RequestCode;
import cn.easyar.samples.helloarvideo.utils.ArDataSheet;
import cn.easyar.samples.helloarvideo.utils.dialog.DialogServer;
import cn.easyar.samples.helloarvideo.utils.file.FileUtil;
import cn.easyar.samples.helloarvideo.utils.log.CLog;
import cn.easyar.samples.helloarvideo.utils.log.TimeUtil;
import cn.easyar.samples.helloarvideo.utils.shotbitmap.ShotCurrentView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class StartActivity extends AppCompatActivity{
	private static final String TAG = "StartActivity";
	public static final String ACTION_MEDIA_SCANNER_SCAN_DIR = "android.intent.action.MEDIA_SCANNER_SCAN_DIR";
	private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;
	private SensorManager                          sensorManager;


	JCVideoPlayerStandard mVideo;
	ImageView mShot;
	View mOpenVideo;
	View mPrintFrame;
	View mScanPhoto;

	private static final String DIR = "Yipai/ArDemo";
	private Uri mUri;
	private ArDataSheet mArDataSheet;
	private DialogServer mDialogServer;
	private String VideoPath;



	/**
	 * 选择对话框接口
	 */
	private DialogServer.MydialogInterface mydialogInterface=new DialogServer.MydialogInterface() {
		@Override
		public void OnDoalogDismiss(String result) {
                            if (result.equals("本地视频")) {
				    Intent intentPicture = new Intent(Intent.ACTION_PICK,
				    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				    intentPicture.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "video/*");
				    startActivityForResult(intentPicture, RequestCode.RC_VIDEO);
			    } else if (result.equals("网络视频")) {

				    mUri = Uri.parse("http://test-epai.oss-cn-shenzhen.aliyuncs.com/video/VR/yangshanhu1002.mp4");

				    mVideo.setUp(mUri.toString(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
				    mVideo.backButton.setVisibility(View.INVISIBLE);
				    mVideo.setVisibility(View.VISIBLE);
				    mVideo.startButton.performClick();
				    mUri=null;

			    }
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		mVideo = (JCVideoPlayerStandard) findViewById(R.id.video);
		mShot = (ImageView) findViewById(R.id.shot);
		mOpenVideo = findViewById(R.id.openVideo);
		mPrintFrame = findViewById(R.id.printFrame);
		mScanPhoto = findViewById(R.id.scanPhoto);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

		FileUtil.sdcard.createDir(DIR);
		mDialogServer=new DialogServer(StartActivity.this,mydialogInterface);

		mArDataSheet = new ArDataSheet(this);
		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				switch (view.getId()) {
				case R.id.openVideo:

					mDialogServer.showSingleChoiceDialog();
					break;
				case R.id.printFrame:
//					if (mVideo.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
//						mVideo.startButton.performClick();
//					}c
//				if (mVideo.currentState == JCVideoPlayer.CURRENT_STATE_PAUSE) {
//					int time = mVideo.getCurrentPositionWhenPlaying();
//					getBitmapsFromVideo(time * 1000);

					getBitmapsFromView(mVideo);


//					}
					break;
				case R.id.scanPhoto:
					Intent it = new Intent(StartActivity.this, MainActivity.class);
					Bundle bundle = new Bundle();
//					it.putExtra("", )
					startActivity(it);
					break;
				}

			}
		};
		mOpenVideo.setOnClickListener(onClickListener);
		mPrintFrame.setOnClickListener(onClickListener);
		mScanPhoto.setOnClickListener(onClickListener);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (requestCode == RequestCode.RC_VIDEO) {
				Uri selectedImage = data.getData();
				String[] filePathColumns = {MediaStore.Images.Media.DATA};
				Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
				c.moveToFirst();
				int columnIndex = c.getColumnIndex(filePathColumns[0]);
				VideoPath= c.getString(columnIndex);
				c.close();

				mVideo.release();
				mVideo.setUp(VideoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
				mVideo.backButton.setVisibility(View.INVISIBLE);
				mVideo.setVisibility(View.VISIBLE);
				mVideo.startButton.performClick();
			}
		}
	}

//	public void onBackPressed() {
//		if (JCVideoPlayer.backPress()) {
//			return;
//		}
//		super.onBackPressed();
//	}


	@Override
	protected void onResume() {
		super.onResume();
		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(sensorEventListener);
		JCVideoPlayer.releaseAllVideos();

	}

	public void getBitmapsFromVideo(long timeUs) {
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//		retriever.setDataSource(StartActivity.this, mUri);


		if(mUri==null&&VideoPath==null){

		}
		else {
			if(mUri==null){
				retriever.setDataSource(VideoPath, new HashMap<String, String>());

			}else {
				retriever.setDataSource(mUri.toString(), new HashMap<String, String>());
			}
			// 取得视频的长度(单位为毫秒)
			String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
			// 取得视频的长度(单位为秒)
			int seconds = Integer.valueOf(time) / 1000;
			Bitmap bitmap = retriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
			mShot.setImageBitmap(bitmap);
			String path = FileUtil.sdcard.getFullPath(DIR, "" + TimeUtil.getCurrentTime() + ".jpg");
			saveBitmap(bitmap, path);
			if(mUri==null){
				mArDataSheet.add(path,VideoPath);
			}
			else {
				mArDataSheet.add(path, mUri.toString());
			}

		}

	}


	public void getBitmapsFromView(View view) {
		MediaMetadataRetriever retriever = new MediaMetadataRetriever();
		if(mUri==null&&VideoPath==null){

		}
		else {
			if(mUri==null){
				retriever.setDataSource(VideoPath, new HashMap<String, String>());

			}else {
				retriever.setDataSource(mUri.toString(), new HashMap<String, String>());
			}

			Bitmap bitmap= ShotCurrentView.shot(view);
			mShot.setImageBitmap(bitmap);
//			String path = FileUtil.sdcard.getFullPath(DIR, "" + TimeUtil.getCurrentTime() + ".jpg");
//			saveBitmap(bitmap, path);
//			if(mUri==null){
//				mArDataSheet.add(path,VideoPath);
//			}
//			else {
//				mArDataSheet.add(path, mUri.toString());
//			}
		}

	}



	private void saveBitmap(Bitmap bitmap, String path) {
		try {
			File file = new File(path);
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			bitmap.recycle();
			fos.close();
		} catch (FileNotFoundException e) {
			CLog.e(TAG, "File not found: " + e.getMessage());
		} catch (IOException e) {
			CLog.e(TAG, "Error accessing file: " + e.getMessage());
		}
		String dir = FileUtil.getDirectoryPath(DIR, false);
		this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(FileUtil.sdcard.createDir(DIR)))));
	}

}
