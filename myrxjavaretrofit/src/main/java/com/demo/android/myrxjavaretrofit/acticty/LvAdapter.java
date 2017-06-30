package com.demo.android.myrxjavaretrofit.acticty;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.demo.android.myrxjavaretrofit.R;
import com.demo.android.myrxjavaretrofit.entity.Livings;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by XiongRun on 2017/5/14.
 */

public class LvAdapter extends CommonAdapter<Livings.LivesBean> {
	private Context mContext;

	public LvAdapter(Context context, int layoutId, List<Livings.LivesBean> datas) {
		super(context, layoutId, datas);
		this.mContext=context;
	}

	@Override
	protected void convert(ViewHolder holder, Livings.LivesBean livesBean, int position) {
		holder.setText(R.id.tv_title,livesBean.getName());
		Glide.with(mContext)
				.load(livesBean.getStream_addr())
				.into((ImageView) holder.getView(R.id.iv));
	}
}
