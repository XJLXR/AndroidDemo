package com.demo.android.myrxjavaretrofit.acticty;

import com.demo.android.myrxjavaretrofit.entity.WXNews;
import com.demo.android.myrxjavaretrofit.http.HttpMethod;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XiongRun on 2017/5/12.
 */

public class WxModel {

	public Observable<WXNews> getNewsLate(Map<String,String> map){

		return HttpMethod.getInstance().getWxApiServer().getNewsList(map)
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

}
