package com.demo.android.myrxjavaretrofit.acticty;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.demo.android.myrxjavaretrofit.R;
import com.demo.android.myrxjavaretrofit.entity.WXNews;
import com.demo.android.myrxjavaretrofit.http.WXApiServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by XiongRun on 2017/5/12.
 */

public class WxActivity extends AppCompatActivity {


	private int pageSize=10;
	private int pageIndex=1;
	private Context mContext;
	private RecyclerView recyclerView;
	private LinearLayoutManager manager;
	private SwipeRefreshLayout refreshLayout;
	private WxModel wxModel;
	private Map<String, String> map;
	private List<WXNews.NewslistBean> newslist;
	private List<WXNews.NewslistBean> mData;
	private WxAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wx);
		mContext = this;
		wxModel = new WxModel();
		initViews();
		initData();
	}

	private void initData() {
		map = new HashMap<>();
		map.put("key", WXApiServer.IPIKEY);
		map.put("num",pageSize+"");
		map.put("page",pageIndex+"");
		getData();

	}

	private void getData() {
		wxModel.getNewsLate(map).subscribe(new Observer<WXNews>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(WXNews value) {
				if (value != null){
					newslist = value.getNewslist();
					mData.addAll(newslist);
					adapter = new WxAdapter(mData,mContext);
					recyclerView.setAdapter(adapter);
					adapter.setData(mData);
				}
			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		});
	}

	private void initViews() {
		mData = new ArrayList<>();
		recyclerView = (RecyclerView) findViewById(R.id.recycler);
		manager = new LinearLayoutManager(mContext);
		recyclerView.setLayoutManager(manager);
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
		refreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
				R.color.refresh_progress_2,R.color.refresh_progress_3);
		refreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,getResources().getDisplayMetrics()));


	}
}
