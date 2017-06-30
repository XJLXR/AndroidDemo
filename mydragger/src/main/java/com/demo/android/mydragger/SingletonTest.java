package com.demo.android.mydragger;

import javax.inject.Inject;

/**
 * Created by XiongRun on 2017/5/20.
 */

public class SingletonTest {

	private String desc;

	@Inject
	public SingletonTest(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
