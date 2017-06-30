package com.demo.android.myrxjavaretrofit.acticty;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by XiongRun on 2017/5/9.
 */

public class BasePresent<V>  {

	protected Reference<V> mView;

	//绑定view
	public void attachView(V view){
		mView = new WeakReference<V>(view);
	}

	//获取view
	protected V getRefVew(){
		return mView.get();
	}

	public void dettachView(){
		if (mView != null){}
		mView.clear();
		mView = null;
	}
}
