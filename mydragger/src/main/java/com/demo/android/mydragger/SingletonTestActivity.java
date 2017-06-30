package com.demo.android.mydragger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by XiongRun on 2017/5/20.
 */

public class SingletonTestActivity extends AppCompatActivity {

	@Inject
	SingletonTest mSingletonTest;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SingletonTestComponent.getsComponent().inject(this);
		Log.i("tag",mSingletonTest.getDesc());
	}
}
