package com.demo.android.mymvpdemo.model;

/**
 * Created by XiongRun on 2017/5/1.
 */

public interface IUserModel {
		void login(String username,String password,OnLoginListener onLoginListener);
}
