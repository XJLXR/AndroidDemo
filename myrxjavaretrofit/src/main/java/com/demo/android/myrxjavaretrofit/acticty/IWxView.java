package com.demo.android.myrxjavaretrofit.acticty;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by XiongRun on 2017/5/12.
 */

public interface IWxView {

	RecyclerView getRecyclerView();
	LinearLayoutManager getLayoutManager();
	SwipeRefreshLayout geRefreshLayout();
}
