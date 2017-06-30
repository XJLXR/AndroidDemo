package com.demo.android.mydragger;

import javax.inject.Inject;

/**
 * Created by XiongRun on 2017/5/20.
 */

public class User {
	private String name;

	@Inject
	public User(){
		name="xiaom";
	}


	public String getName() {
		return name;
	}
}
