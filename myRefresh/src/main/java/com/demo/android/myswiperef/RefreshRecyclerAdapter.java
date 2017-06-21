package com.demo.android.myswiperef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private static final int TYPE_ITEM =0;  //普通Item View

	private static final int TYPE_FOOTER = 1;  //底部FootView
	private Context mContext;
	private List<String> mTitles=null;

	//上拉加载更多
	public static final int  PULLUP_LOAD_MORE=0;
	//正在加载中
	public static final int  LOADING_MORE=1;
	//上拉加载更多状态-默认为0
	private int load_more_status=0;
	//没有数据
	public static final int NO_MORE_DATA=2;


	RefreshRecyclerAdapter(Context mContext){
		this.mContext = mContext;
		mTitles = new ArrayList<>();
		for (int i = 0;i<20;i++){
			mTitles.add("items"+i);
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		if (viewType ==TYPE_ITEM){
			View view = LayoutInflater.from(mContext).inflate(R.layout.ref_item,parent,false);
			ItemViewHolder itemHolder = new ItemViewHolder(view);
			return itemHolder;
		}else if (viewType == TYPE_FOOTER){
			View foot_view=LayoutInflater.from(mContext).inflate(R.layout.recycler_load_more_layout,parent,false);
			//这边可以做一些属性设置，甚至事件监听绑定
			//view.setBackgroundColor(Color.RED);
			FootViewHolder footViewHolder=new FootViewHolder(foot_view);
			return footViewHolder;
		}

		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof ItemViewHolder){
			((ItemViewHolder) holder).textView.setText(mTitles.get(position));
			holder.itemView.setTag(position);
		}else if (holder instanceof FootViewHolder){
			switch (load_more_status){
				case PULLUP_LOAD_MORE:
					((FootViewHolder) holder).foot_view_item_tv.setText("上拉加载更多...");
					break;
				case LOADING_MORE:
					((FootViewHolder) holder).foot_view_item_tv.setText("正在加载更多数据...");
					break;

				case NO_MORE_DATA:
					((FootViewHolder) holder).foot_view_item_tv.setText("没有更多数据...");
					break;
				default:

					break;
			}
		}
	}



	@Override
	public int getItemCount() {
		return mTitles.size()+1;
	}

	/**
	 * item布局
	 */

	public static class ItemViewHolder extends RecyclerView.ViewHolder {

		public TextView textView;

		public ItemViewHolder(View itemView) {

			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.tv);
		}
	}

	/**
	 * 底部FootView布局
	 */
	public static class FootViewHolder extends  RecyclerView.ViewHolder{
		private TextView foot_view_item_tv;
		public FootViewHolder(View view) {
			super(view);
			foot_view_item_tv=(TextView)view.findViewById(R.id.load_more);
		}
	}

	/**
	 * 进行判断是普通Item视图还是FootView视图
	 * @param position
	 * @return
	 */
	@Override
	public  int getItemViewType(int position) {
		// 最后一个item设置为footerView
		if (position + 1 == getItemCount()) {
			return TYPE_FOOTER;
		} else {
			return TYPE_ITEM;
		}
	}

	/**
	 * //上拉加载更多
	 * PULLUP_LOAD_MORE=0;
	 * //正在加载中
	 * LOADING_MORE=1;
	 * //加载完成已经没有更多数据了
	 * NO_MORE_DATA=2;
	 * @param status
	 */
	public void changeMoreStatus(int status){
		load_more_status=status;
		notifyDataSetChanged();
	}

	//添加数据
	public void addItem(List<String> newDatas) {
		newDatas.addAll(mTitles);
		mTitles.removeAll(mTitles);
		mTitles.addAll(newDatas);
		notifyDataSetChanged();
	}

	public void addMoreItem(List<String> newDatas) {
		mTitles.addAll(newDatas);
		notifyDataSetChanged();
	}
}
