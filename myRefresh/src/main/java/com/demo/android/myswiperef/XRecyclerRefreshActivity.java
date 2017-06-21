package com.demo.android.myswiperef;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

/**
 * Created by XiongRun on 2017/5/9.
 */

public class XRecyclerRefreshActivity extends AppCompatActivity {


	private Toolbar toolbar;
	private XRecyclerView mRecyclerView;
	private View handView;
	private RefreshRecyclerAdapter adapter;
	private ArrayList<String> listData;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activty_xrecycler_refresh);
		initView();

	}

	private void initView() {
		listData= new ArrayList<>();
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mRecyclerView = (XRecyclerView)this.findViewById(R.id.recyclerview);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);



		mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
		mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
		mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);


		handView = LayoutInflater.from(this).inflate(R.layout.recycler_head,(ViewGroup)findViewById(android.R.id.content),false);
		mRecyclerView.addHeaderView(handView);
		adapter = new RefreshRecyclerAdapter(this);
		mRecyclerView.setAdapter(adapter);

		/**
		 * 原先自定义的adapter加了一个footer
		 */
		mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
							listData.clear();

						for(int i = 0; i < 5 ;i++){
							listData.add("additem" + i );
						}

						adapter.addItem(listData);
						mRecyclerView.refreshComplete();
						Toast.makeText(getApplication(),"更新了5条数据",Toast.LENGTH_LONG).show();
					}
				},2000);
			}

			@Override
			public void onLoadMore() {
				new Handler().postDelayed(new Runnable(){
					public void run() {
						listData.clear();
						for(int i = 0; i < 15 ;i++){
							listData.add("item" + (1 + listData.size() ) );
						}

						mRecyclerView.loadMoreComplete();
						adapter.addMoreItem(listData);
						Toast.makeText(getApplication(),"更新了15条数据",Toast.LENGTH_LONG).show();
					}
				}, 1000);
			}

		});
	}
}
