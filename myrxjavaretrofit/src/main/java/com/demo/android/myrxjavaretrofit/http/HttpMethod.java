package com.demo.android.myrxjavaretrofit.http;

import android.util.Log;

import com.demo.android.myrxjavaretrofit.entity.MovieEntity;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by XiongRun on 2017/5/2.
 */

public class HttpMethod {
	public static final String BASE_URL = "https://api.douban.com";
	private static final int DEFAULT_TIMEOUT = 5;
	private  Retrofit retrofit;
	private  MovieService movieService;
	private final Retrofit wxRetrofit;
	private final WXApiServer wxApiServer;
	private final Retrofit lsRetrofit;
	private final LivingServer livingServer;

	private HttpMethod(){
		//拦截请求和响应日志并输出，其实有很多封装好的日志拦截插件，大家也可以根据个人喜好选择。
		HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
		HttpLoggingInterceptor loggingInterceptor =new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				Log.i("HttpManager",message);
			}
		});
		loggingInterceptor.setLevel(level);

		//手动创建一个OkHttpClient并设置超时时间
		OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();
		okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
				.retryOnConnectionFailure(true)
		.addNetworkInterceptor(loggingInterceptor);

		//此处添加RxJavaCallAdapterFactory，把请求结果直接映射为
//MovieService接口方法返回的具体类型MovieEntity
		retrofit = new Retrofit.Builder()
				.client(okHttpClient.build())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(BASE_URL)
				.build();
		movieService = retrofit.create(MovieService.class);

		wxRetrofit = new Retrofit.Builder()
				.client(okHttpClient.build())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(WXApiServer.BASE_WX_URL)
				.build();
		wxApiServer = wxRetrofit.create(WXApiServer.class);

		lsRetrofit = new Retrofit.Builder()
				.client(okHttpClient.build())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(LivingServer.BASE_URL)
				.build();

		livingServer = lsRetrofit.create(LivingServer.class);
	}

	public WXApiServer getWxApiServer(){
		return wxApiServer;
	}

	public LivingServer getLivingServer(){
		return livingServer;
	}

	//在访问HttpMethods时创建单例
	private static class SingletonHolder{
		private static final HttpMethod INSTANCE = new HttpMethod();
	}

	//获取单例
	public static HttpMethod getInstance(){
		return SingletonHolder.INSTANCE;
	}

	public void getTopMovie(Observer<MovieEntity> observer, int start, int count){
		movieService.getToMovie(start, count)
				/*.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
					@Override
					public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
						return null;
					}
				})*/
				//指定subscribe()发生在io调度器（读写文件、读写数据库、网络信息交互等）
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				//指定subscriber的回调发生在主线程
				.observeOn(AndroidSchedulers.mainThread())
				//实现订阅关系
				.subscribe((Subscriber<? super MovieEntity>) observer);
	}

	public void getM(Subscriber<MovieEntity> subscriber, int start, int count){
		movieService.getToMovie(start,count)
				.subscribeOn(Schedulers.io())
				.unsubscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber);

	}
}
