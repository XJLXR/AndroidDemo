package com.demo.android.mydragger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XiongRun on 2017/5/20.
 */

@Module
public class SingletonModule {

	@Provides
	@Singleton
	SingletonTest  provideSingletonTest(){
		return new SingletonTest("what");
	}
}
