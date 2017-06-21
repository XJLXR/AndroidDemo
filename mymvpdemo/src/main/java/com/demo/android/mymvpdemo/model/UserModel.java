package com.demo.android.mymvpdemo.model;

import com.demo.android.mymvpdemo.bean.User;

/**
 * Created by XiongRun on 2017/5/1.
 */

public class UserModel  implements IUserModel {
	@Override
	public void login(final String username, final String password, final OnLoginListener onLoginListener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if ("xr".equals(username)&&"123".equals(password)){
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					onLoginListener.loginSuccess(user);
				}else {
					onLoginListener.loginFailed();
				}
			}
		}).start();
	}
}
