package com.demo.android.myswiperef;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.demo.android.myswiperef.RefreshRecyclerAdapter.LOADING_MORE;

public class SwipeRefreshActivity extends AppCompatActivity {

	private SwipeRefreshLayout mRefreshLayout;
	private RecyclerView recyclerView;
	private LinearLayoutManager manager;
	private List<String> newDatas;
	private RefreshRecyclerAdapter adapter;
	private int lastVisibleItem ;//recyclerview最后显示的Item,用于判断recyclerview自动加载下一页

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_swipe_refresh);

		initViews();
	}

	private void initViews() {
		mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
		recyclerView = (RecyclerView) findViewById(R.id.recycler);
		manager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(manager);
		recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
		adapter = new RefreshRecyclerAdapter(this);
		recyclerView.setAdapter(adapter);

		mRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
		mRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
						.getDisplayMetrics()));

		mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						newDatas = new ArrayList<String>();
						for (int i = 0; i <5; i++) {
							int index = i + 1;
							newDatas.add("new item" + index);
						}
						adapter.addItem(newDatas);
						mRefreshLayout.setRefreshing(false);
						Toast.makeText(getApplication(),"更新了5条数据",Toast.LENGTH_LONG).show();
					}
				},4000);
			}
		});

		/**
		 * 滑动刷新
		 */
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState==RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +1==adapter.getItemCount()){
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							List<String> newDatas = new ArrayList<String>();
							for (int i = 0; i< 5; i++) {
								int index = i +1;
								newDatas.add("more item" + index);
							}
							adapter.addMoreItem(newDatas);
							adapter.changeMoreStatus(LOADING_MORE);
						}
					},3000);
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				lastVisibleItem =manager.findLastVisibleItemPosition();
			}
		});

	}
}
