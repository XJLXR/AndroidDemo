package com.demo.android.greendao.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.android.greendao.R;
import com.demo.android.greendao.bean.Shop;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by XiongRun on 2017/3/13.
 */

public class ShopAdapter extends CommonAdapter<Shop> {
	public ShopAdapter(Context context, int layoutId, List<Shop> datas) {
		super(context, layoutId, datas);
	}

	@Override
	protected void convert(ViewHolder holder, Shop shop, int position) {
		holder.setText(R.id.tv_name,shop.getName());
		holder.setText(R.id.tv_price_discount,shop.getPrice());
		holder.setText(R.id.tv_sell_num, shop.getSell_num()+"");
		Glide.with(mContext)
				.load(shop.getImage_url())
				.asBitmap()
				.into((ImageView) holder.getView(R.id.iv_shop));
	}


}
