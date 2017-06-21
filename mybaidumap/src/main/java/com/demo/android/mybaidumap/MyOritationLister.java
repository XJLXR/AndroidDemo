package com.demo.android.mybaidumap;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by XiongRun on 2017/4/5.
 */

public class MyOritationLister implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mSensor;
	private Context mContext;

	private float lastX;

	public MyOritationLister(Context context){
		this.mContext = context;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION){
			float x = event.values[SensorManager.DATA_X];

			if (Math.abs(x-lastX)>1.0){
				//监听回掉通知ui界面更新
				mOnOrietationlistener.onOrietationChanged(lastX);
			}
			lastX = x;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void start(){
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);

		if (mSensorManager != null){
			//获取方向传感器
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}

		if (mSensor != null){
			mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_UI);
		}
	}

	public void stop(){
		mSensorManager.unregisterListener(this);
	}

	public void setOnOrietationlistener(onOrietationlistener mOnOrietationlistener) {
		this.mOnOrietationlistener = mOnOrietationlistener;
	}

	private onOrietationlistener mOnOrietationlistener;


	public interface onOrietationlistener{
		void onOrietationChanged(float x);
	}
}
