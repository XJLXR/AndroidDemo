package com.sanhang.android.addimagedemo;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import cn.finalteam.galleryfinal.*;

/**
 * Created by XiongRun on 2016/8/18.
 */

public class MyApplication extends Application {

	public static Context mContext;
	public static Thread mainThread;
	public static int mainThreadId;
	public  static Handler handler;

	@Override
	public void onCreate() {
		super.onCreate();

		mContext = getApplicationContext();
		mainThread = Thread.currentThread();
		mainThreadId = android.os.Process.myTid();
		handler = new Handler();
		initGallery();
	}

	private void initGallery() {
		ThemeConfig theme = new ThemeConfig.Builder()

				.build();
//配置功能
		FunctionConfig functionConfig = new FunctionConfig.Builder()
				.setEnableCamera(true)
				.setEnableEdit(true)
				.setEnableCrop(true)
				.setEnableRotate(true)
				.setCropSquare(true)
				.setEnablePreview(true)
				.build();

//配置imageloader
		ImageLoader imageloader = new GlideImageLoader();
//设置核心配置信息
		CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageloader, theme)
				.setDebug(BuildConfig.DEBUG)
				.setFunctionConfig(functionConfig)
		.build();
		GalleryFinal.init(coreConfig);
	}
}
