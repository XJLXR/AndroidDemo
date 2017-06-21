package com.sanhang.android.addimagedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private ImageView img;
	private final static int REQUEST_CODE_GALLERY = 1001;
	private List<PhotoInfo> mList;
	private RecyclerView recyclerView;
	private RecyclerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initData();
		initEvent();
	}

	private void initView() {
		img = (ImageView) findViewById(R.id.img);
		recyclerView = (RecyclerView) findViewById(R.id.recycler);

		//设置布局管理器
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		recyclerView.setLayoutManager(linearLayoutManager);



	}

	private void initData() {

	}

	private void initEvent() {
		img.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.img:

				FunctionConfig functionConfig = new FunctionConfig
						.Builder()
						.setMutiSelectMaxSize(9)
						.build();
				GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, mOnHanlderResultCallback);

				break;
		}
	}

	private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback(){



		@Override
		public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
			mList = new ArrayList<>();
			if (resultList  != null){
				mList.addAll(resultList);
			}else {
				return;
			}

			adapter = new RecyclerAdapter(MainActivity.this,mList);

			adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					Toast.makeText(getApplication(),"点击的位置"+position,Toast.LENGTH_SHORT).show();
				}
			});

			adapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
				@Override
				public void onItemLongClick(View view, int position) {

				}
			});
			recyclerView.setAdapter(adapter);

		}

		@Override
		public void onHanlderFailure(int requestCode, String errorMsg) {
			Toast.makeText(getApplication(),errorMsg,Toast.LENGTH_SHORT).show();
		}
	};
}
