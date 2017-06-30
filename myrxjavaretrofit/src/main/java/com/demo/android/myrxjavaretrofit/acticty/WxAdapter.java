package com.demo.android.myrxjavaretrofit.acticty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.android.myrxjavaretrofit.R;
import com.demo.android.myrxjavaretrofit.entity.WXNews;

import java.util.List;

/**
 * Created by XiongRun on 2017/5/12.
 */

public class WxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private List<WXNews.NewslistBean> mData ;
	private Context mContext;

	public WxAdapter(List<WXNews.NewslistBean> mData,Context mContext){
		this.mData = mData;
		this.mContext= mContext;
	}

	public void setData(List<WXNews.NewslistBean> mData){
		this.mData=mData;
		notifyDataSetChanged();
	}
	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = View.inflate(parent.getContext(), R.layout.item_wx,parent);
		return new ItemViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ItemViewHolder){
			((ItemViewHolder) holder).tvTitle.setText(mData.get(position).getTitle());
		}
	}



	@Override
	public int getItemCount() {
		return mData.size();
	}

	public static class ItemViewHolder extends RecyclerView.ViewHolder {

		public final TextView tvTitle;

		public ItemViewHolder(View itemView) {
			super(itemView);

			tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
		}
	}
}
