package com.demo.android.mymvpdemo.view;

import com.demo.android.mymvpdemo.bean.User;

/**
 * Created by XiongRun on 2017/5/1.
 */

public interface IUserLoginView {

	String getUserName();

	String getPassWord();

	void clearUserName();

	void clearPassword();

	void showLoading();

	void hideLoading();

	void toMainActivity(User user);

	void showFailedError();
}
