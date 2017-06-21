package com.sanhang.android.addimagedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by XiongRun on 2016/8/10.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

	private Context mContext;
	private List<PhotoInfo> mList;
	private LayoutInflater mInflater;

	public interface OnItemClickListener{
		void onItemClick(View view,int position);
	}

	public interface OnItemLongClickListener{
		void onItemLongClick(View view,int position);
	}

	private OnItemClickListener mOnItemClickListener;
	private OnItemLongClickListener mOnItemLongClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
		this.mOnItemClickListener = mOnItemClickListener;
	}

	public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
		this.mOnItemLongClickListener = mOnItemLongClickListener;
	}


	public RecyclerAdapter(Context mContext,List mList){
		this.mContext = mContext;
		this.mList = mList;
		mInflater= LayoutInflater.from(mContext);
	}

	public void setData(List mList){
		this.mList = mList;
		notifyDataSetChanged();
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = mInflater.inflate(R.layout.item,parent,false);
		ViewHolder holder = new ViewHolder(view);
		holder.img = (ImageView) view.findViewById(R.id.image);
		holder.delete = (ImageView) view.findViewById(R.id.delete);
		return holder;
	}

	@Override
	public void onBindViewHolder(final  ViewHolder holder, final int position) {

		if(mOnItemClickListener != null){
			//为ItemView设置监听器
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = holder.getLayoutPosition();
					mOnItemClickListener.onItemClick(holder.itemView,position); // 2
				}
			});

			if(mOnItemLongClickListener != null){
				holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View v) {
						int position = holder.getLayoutPosition();
						mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
						//返回true 表示消耗了事件 事件不会继续传递
						return true;
					}
				});
			}
		}


		Glide.with(mContext)
				.load(mList.get(position).getPhotoPath())
				.asBitmap()
				.into(holder.img);

		holder.delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mList.remove(position);
				setData(mList);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList == null?0:mList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{

		public ViewHolder(View itemView) {
			super(itemView);
		}
		ImageView img;
		ImageView delete;
	}

}
