package com.demo.android.mymvpdemo.presenter;

import android.os.Handler;

import com.demo.android.mymvpdemo.bean.User;
import com.demo.android.mymvpdemo.model.IUserModel;
import com.demo.android.mymvpdemo.model.OnLoginListener;
import com.demo.android.mymvpdemo.model.UserModel;
import com.demo.android.mymvpdemo.view.IUserLoginView;

/**
 * Created by XiongRun on 2017/5/1.
 */

public class UserLoginPresenter {

	private IUserModel userModel;
	private IUserLoginView loginView;
	private Handler mHandler = new Handler();

	public UserLoginPresenter(IUserLoginView loginView){
		this.loginView = loginView;
		this.userModel = new UserModel();
	}

	public void login(){
		loginView.showLoading();
		userModel.login(loginView.getUserName(), loginView.getPassWord(), new OnLoginListener() {
			@Override
			public void loginSuccess(final User user) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						loginView.toMainActivity(user);
						loginView.hideLoading();
					}
				});
			}

			@Override
			public void loginFailed() {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						loginView.showFailedError();
						loginView.hideLoading();
					}
				});
			}
		});
	}

	public void clean(){
		loginView.clearPassword();
		loginView.clearUserName();
	}

}
