package com.demo.android.myrxjavaretrofit.http;


import com.demo.android.myrxjavaretrofit.entity.WXNews;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by XiongRun on 2017/5/9.
 */

public interface WXApiServer {

	/**
	 * https://api.tianapi.com/wxnew/?key=APIKEY&num=10
	 *
	 * key	string	是	urlParam	API密钥（请在个人中心获取）	用户自己的key
	 num	int	是	urlParam	指定返回数量，最大50	10
	 src	string	否	urlParam	指定来源为某微信公众号	例如：人民日报
	 rand	int	否	urlParam	参数值为1则随机获取	0
	 word	string	否	urlParam	检索关键词	上海
	 page	int	否	urlParam	翻页，每页输出数量跟随num参数	1，若指定文章来源则必带此参数
	 */

	String BASE_WX_URL = "https://api.tianapi.com/";
	String IPIKEY="a168a48492d240fae6fd2c6b6ea4e7b4";

	@GET("wxnew")
	Observable<WXNews> getNewsList(@Query("key") String key, @Query("num") int num, @Query("page") int page);

	@GET("wxnew")
	Observable<WXNews> getNewsList(@QueryMap Map<String, String> map);
}
