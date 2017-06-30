package com.demo.android.myrxjavaretrofit.http;


import com.demo.android.myrxjavaretrofit.entity.Livings;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by XiongRun on 2017/5/14.
 */

public interface LivingServer {

	String url = "near_recommend?lc=3000000000011509&cv=IK3.1.10_Android&cc=TG36008&ua=XiaomiMI4LTE&uid=190761403&sid=20Tr1VWDFRc5wxUub4BC6rl55284NDi0VsCuvi2aZi2h59JJRfVDI&devi=867323029795190&imsi=460002330273772&imei=867323029795190&icc=898600520115f0989782&conn=WIFI&vv=1.0.3-2016060211417.android&aid=6524c2b6ae0bb697&osversion=android_23&mtid=4c8b78842db191e46d8639b709d1fa38&mtxid=fcd7333d06da&proto=4&smid=DujlPyXDfceh+88GbEzm+rhiRWdHAXcqw3ASJWkadmdHVmg5HpwVO2vPVauokUmZh2DI3gKxpE7rh4aEPx32U3LA&longitude=116.32758&latitude=39.75431";
	String BASE_URL = "http://120.55.238.158/api/live/";
	@GET(url)
	Observable<Livings> getData();

}
