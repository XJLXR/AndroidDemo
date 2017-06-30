package com.demo.android.mydragger;

import dagger.Component;

/**
 * Created by XiongRun on 2017/5/20.
 */

@Component(modules = PersonModule.class)
public interface PersonComponent {

	void inject(MainActivity mainActivity);
}
