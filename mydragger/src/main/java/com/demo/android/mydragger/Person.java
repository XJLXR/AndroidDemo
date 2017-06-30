package com.demo.android.mydragger;

/**
 * Created by XiongRun on 2017/5/20.
 */

public class Person {
	private String sex;

	public Person(String sex) {
		this.sex = sex;
	}

	public Person() {
		sex = "太监";
	}

	public String getSex() {
		return sex;
	}
}
