package com.demo.android.myrxjavaretrofit.http;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by XiongRun on 2017/5/3.
 */

public class CookieManger implements CookieJar {
	@Override
	public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
		
	}

	@Override
	public List<Cookie> loadForRequest(HttpUrl url) {
		return null;
	}
}
