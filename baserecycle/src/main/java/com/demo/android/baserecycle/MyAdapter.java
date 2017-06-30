package com.demo.android.baserecycle;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by XiongRun on 2017/6/27.
 */

public class MyAdapter extends BaseQuickAdapter<Shop,BaseViewHolder> {

	public MyAdapter(List<Shop> data) {
		super(R.layout.item, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, Shop item) {
		helper.setText(R.id.tv_name,item.getName());
		helper.setText(R.id.tv_price,item.getPrice());
		helper.setText(R.id.tv_sell_num,item.getSell_num()+"");
		Glide.with(mContext)
				.load(item.getImage_url())
				.asBitmap()
				.into((ImageView) helper.getView(R.id.iv_shop));
	}

}
