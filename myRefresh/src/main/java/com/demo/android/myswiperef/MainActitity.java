package com.demo.android.myswiperef;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by XiongRun on 2017/5/9.
 */

public class MainActitity extends AppCompatActivity implements View.OnClickListener {

	private Button swipeRefresh,xRecyclerRefresh;
	private Intent intent;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
		swipeRefresh = (Button) findViewById(R.id.SwipeRefresh);
		xRecyclerRefresh = (Button) findViewById(R.id.XRecyclerRefresh);

		swipeRefresh.setOnClickListener(this);
		xRecyclerRefresh.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()){

			case R.id.SwipeRefresh:
				intent = new Intent(this,SwipeRefreshActivity.class);
				startActivity(intent);
				break;

			case R.id.XRecyclerRefresh:
				intent = new Intent(this,XRecyclerRefreshActivity.class);
				startActivity(intent);
				break;

			default:
				break;
		}
	}
}
