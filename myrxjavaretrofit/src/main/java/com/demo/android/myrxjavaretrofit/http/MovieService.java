package com.demo.android.myrxjavaretrofit.http;

import com.demo.android.myrxjavaretrofit.entity.MovieEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by XiongRun on 2017/5/2.
 */

public interface MovieService {

	//https://api.douban.com/v2/movie/top250?start=0&count=10
	/*@GET("/v2/movie/top250")
	Call<MovieEntity> getToMovie(@Query("start")int start,@Query("count")int count);*/


	/** 返回的Observable正是被观察者，我们用来通知观察者对象（这里我们通知UI更新） **/
	@GET("/v2/movie/top250")
	Flowable<MovieEntity> getToMovie(@Query("start")int start, @Query("count")int count);
}
