package com.demo.android.greendao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.demo.android.greendao.R;
import com.demo.android.greendao.adapter.ShopAdapter;
import com.demo.android.greendao.bean.Shop;
import com.demo.android.greendao.db.ShopDBDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button add;
	private Button del;
	private Button updata;
	private Button query;
	private RecyclerView recycler;
	private LinearLayoutManager manager;
	private List<Shop> mData;
	private Shop shop;
	private ShopAdapter adapter;
	private static int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();

		add = (Button) findViewById(R.id.bt_add);
		del = (Button) findViewById(R.id.bt_delete);
		updata = (Button) findViewById(R.id.bt_update);
		query = (Button) findViewById(R.id.bt_query);
		recycler = (RecyclerView) findViewById(R.id.recycle);
		manager = new LinearLayoutManager(this);
		recycler.setLayoutManager(manager);


		add.setOnClickListener(this);
		del.setOnClickListener(this);
		updata.setOnClickListener(this);
		query.setOnClickListener(this);
		queryDate();


	}

	private void initData() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.bt_add:
				addDate();
				break;

			case R.id.bt_delete:
				deleteDate();
				break;

			case R.id.bt_update:
				updateDate();
				break;

			case R.id.bt_query:
				queryDate();
				break;

			default:
				break;
		}
	}

	private void updateDate() {
		if (!mData.isEmpty()) {
			Shop shop = mData.get(0);
			shop.setName("我是修改的名字");
			ShopDBDao.updateData(shop);
			queryDate();
		}
	}

	private void queryDate() {
		mData = new ArrayList<>();
		mData = ShopDBDao.queryData();
		adapter = new ShopAdapter(this, R.layout.item_shop,mData);
		recycler.setAdapter(adapter);
	}

	private void deleteDate() {
		if (!mData.isEmpty()){
			ShopDBDao.deleteData(mData.get(0).getId());
			queryDate();
		}
	}

	private void addDate() {
		shop = new Shop();
		shop.setType(Shop.TYPE_LOVE);
		shop.setAddress("浙江杭州");
		shop.setImage_url("https://img.alicdn.com/bao/uploaded/i2/TB1N4V2PXXXXXa.XFXXXXXXXXXX_!!0-item_pic.jpg_640x640q50.jpg");
		shop.setPrice("19.40");
		shop.setSell_num(15263);
		shop.setName("正宗梅菜扣肉  2盒包邮  " + "item"+ i++);
		ShopDBDao.insertData(shop);
		queryDate();
	}
}
