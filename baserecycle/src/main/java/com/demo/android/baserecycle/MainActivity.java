package com.demo.android.baserecycle;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

	private RecyclerView recycler;
	private List<Shop> mData;
	private Shop shop;
	private MyAdapter adapter;
	private SwipeRefreshLayout refreshLayout;
	private List<Shop> newData;
	private int mCurrentCounter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
		init();
	}

	private void initData() {
		mData = new ArrayList<>();
		newData = new ArrayList<>();

		for (int i = 0; i < 10;i++){
			shop = new Shop();
			shop.setAddress("浙江杭州");
			shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
			shop.setPrice("19.40");
			shop.setSell_num(15263);
			shop.setName("正宗梅菜扣肉  2盒包邮  " + "item"+ i);
			mData.add(shop);
		}

	}

	private void init() {
		recycler = (RecyclerView) findViewById(R.id.recycler);
		recycler.setLayoutManager(new LinearLayoutManager(this));

		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
		refreshLayout.setColorSchemeResources(R.color.refresh_progress_1,
				R.color.refresh_progress_2, R.color.refresh_progress_3);
		refreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
				.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,getResources().getDisplayMetrics()));

		refreshLayout.setRefreshing(false);
		refreshLayout.setOnRefreshListener(this);
		adapter = new MyAdapter(mData);
		mCurrentCounter = adapter.getData().size();
		Log.i("TAG",mCurrentCounter+"");

		adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
		adapter.isFirstOnly(false);
		adapter.setOnLoadMoreListener(this,recycler);


		View headerView = getHeaderView(0, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
			}
		});

		View footerView = getFooterView(0, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.addFooterView(getFooterView(1, getRemoveFooterListener()), 0);
			}
		});

		adapter.addHeaderView(headerView);
		adapter.addFooterView(footerView);
		recycler.setAdapter(adapter);

	}

	/**
	 * 头布局
	 * @param type
	 * @param listener
	 * @return
	 */
	private View getHeaderView(int type, View.OnClickListener listener){

		View view = getLayoutInflater().inflate(R.layout.header, (ViewGroup) recycler.getParent(), false);
		if (type == 1) {
			ImageView imageView = (ImageView) view.findViewById(R.id.iv);
			imageView.setImageResource(R.mipmap.rm_icon);
		}
		view.setOnClickListener(listener);
		return view;
	}

	private View.OnClickListener getRemoveHeaderListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.removeHeaderView(v);
			}
		};
	}

	/**
	 * 底部布局
	 * @param type
	 * @param listener
	 * @return
	 */
	private View getFooterView(int type, View.OnClickListener listener) {
		View view = getLayoutInflater().inflate(R.layout.footer, (ViewGroup) recycler.getParent(), false);
		if (type == 1) {
			ImageView imageView = (ImageView) view.findViewById(R.id.iv);
			imageView.setImageResource(R.mipmap.rm_icon);
		}
		view.setOnClickListener(listener);
		return view;
	}

	private View.OnClickListener getRemoveFooterListener() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.removeFooterView(v);
			}
		};
	}

	/**
	 * SwipeRefreshLayout
	 * 自带刷新
	 */
	@Override
	public void onRefresh() {
		adapter.setEnableLoadMore(false);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i < 5;i++){
					shop = new Shop();
					shop.setType(Shop.TYPE_LOVE);
					shop.setAddress("武汉");
					shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
					shop.setPrice("1000");
					shop.setSell_num(100000);
					shop.setName("好吃的热干面  2盒包邮  " + "item"+ i);
					newData.add(shop);
				}
				mData.addAll(newData);
				adapter.setNewData(mData);
				refreshLayout.setRefreshing(false);
				adapter.setEnableLoadMore(true);
			}
		},1000);
	}

	/**
	 * 下拉刷新
	 */
	@Override
	public void onLoadMoreRequested() {

		for (int i=0;i<6;i++){
			shop = new Shop();
			shop.setType(Shop.TYPE_LOVE);
			shop.setAddress("深圳");
			shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
			shop.setPrice("2333");
			shop.setSell_num(10200);
			shop.setName("大龙虾 2盒包邮  " + "item"+ i);
			mData.add(shop);
		}
		adapter.setNewData(mData);
		adapter.loadMoreComplete();
	}
}
