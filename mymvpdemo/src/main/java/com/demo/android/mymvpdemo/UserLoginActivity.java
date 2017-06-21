package com.demo.android.mymvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.demo.android.mymvpdemo.bean.User;
import com.demo.android.mymvpdemo.presenter.UserLoginPresenter;
import com.demo.android.mymvpdemo.view.IUserLoginView;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener, IUserLoginView {

	private EditText username,password;
	private Button login;
	private Button clean;
	private ProgressBar mPbLoading;
	private  UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews() {
		username = (EditText) findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);

		login = (Button) findViewById(R.id.login);
		clean = (Button) findViewById(R.id.clean);
		mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);

		login.setOnClickListener(this);
		clean.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.login:
				mUserLoginPresenter.login();
				break;

			case R.id.clean:
				mUserLoginPresenter.clean();

				break;

			default:
				break;

		}
	}

	@Override
	public String getUserName() {
		return username.getText().toString();
	}

	@Override
	public String getPassWord() {
		return password.getText().toString();
	}

	@Override
	public void clearUserName() {
		username.setText("");
	}

	@Override
	public void clearPassword() {
		password.setText("");
	}

	@Override
	public void showLoading() {
		mPbLoading.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		mPbLoading.setVisibility(View.GONE);
	}

	@Override
	public void toMainActivity(User user) {
		Toast.makeText(this, user.getUsername() +
				" login success , to MainActivity", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showFailedError() {
		Toast.makeText(this,
				"login failed", Toast.LENGTH_SHORT).show();
	}
}
