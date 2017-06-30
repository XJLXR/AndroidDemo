package com.demo.android.myrxjavaretrofit.acticty;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class WxPresent {

	private Context mContext;
	private WxModel wxModel;
	private IWxView iWxView;
	private RecyclerView recyclerView;
	private LinearLayoutManager manager;
	private SwipeRefreshLayout refreshLayout;
	private Map<String, String> map;
	private int pageSize=10;
	private int pageIndex=1;
	private int lastVisibleItem;
	private List<WXNews.NewslistBean> newslist;
	private List<WXNews.NewslistBean> mData;
	private  WxAdapter adapter;

	public WxPresent(Context mContext,IWxView iWxView){
		this.mContext=mContext;
		wxModel = new WxModel();
		mData = new ArrayList<>();
		this.iWxView = iWxView;

	}

	public void getNews(){

		if (iWxView != null){
			recyclerView = iWxView.getRecyclerView();
			manager = iWxView.getLayoutManager();
			refreshLayout = iWxView.geRefreshLayout();

			map = new HashMap<>();
			map.put("key", WXApiServer.IPIKEY);
			map.put("num",pageSize+"");
			map.put("page",pageIndex+"");

			wxModel.getNewsLate(map).subscribe(new Observer<WXNews>() {

				Disposable disposable;
				@Override
				public void onSubscribe(Disposable d) {
					disposable = d;

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

	}

}
