package com.demo.android.mydragger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by XiongRun on 2017/5/20.
 */
@Component(modules = {SingletonModule.class})
@Singleton//这个Component的@Scope要和对应的Module的@Scope一致
public abstract  class SingletonTestComponent {
	/**
	 * /@Component不仅可以注解接口也可以注解抽象类，为了方便测试单例，把Component改为抽象类，
	 * 实际开发中可以在Application中创建单例。
	 */
	public abstract void inject(SingletonTestActivity singletonTestActivity);

	private static SingletonTestComponent sComponent;

	public static SingletonTestComponent getsComponent(){
		if (sComponent == null) {
			sComponent = DaggerSingletonTestComponent.builder().build();
		}
		return sComponent;
	}
}
