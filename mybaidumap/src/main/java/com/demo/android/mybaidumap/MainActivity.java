package com.demo.android.mybaidumap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;


public class MainActivity extends AppCompatActivity {

	private MapView mapView;
	private BaiduMap baiduMap;
	private MapStatusUpdate msu;
	private LocationClient mLocationClient;
	private MyLocationListener myLocationListener;
	private LocationClientOption option;
	private boolean isFisrtIn = true;
	private double mLatitude,mLongtitude;
	private BitmapDescriptor mIconLocation;
	private MyOritationLister myOritationLister;
	private float mCurrentX;
	private MyLocationConfiguration.LocationMode mLocationMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext
		//注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		//获取地图控件引用
		initView();

		//定位功能
		initLocation();
	}

	private void initLocation() {

		mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
		mLocationClient = new LocationClient(this);
		myLocationListener = new MyLocationListener();

		//注册定位监听
		mLocationClient.registerLocationListener(myLocationListener);

		option = new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setIsNeedAddress(true);
		option.setOpenGps(true);
		option.setScanSpan(1000);//每秒发送一次请求
		mLocationClient.setLocOption(option);

		mIconLocation =  BitmapDescriptorFactory.
				fromResource(R.mipmap.baidu);

		myOritationLister = new MyOritationLister(getApplicationContext());
		myOritationLister.setOnOrietationlistener(new MyOritationLister.onOrietationlistener() {
			@Override
			public void onOrietationChanged(float x) {
				mCurrentX = x;
			}
		});
	}

	private void initView() {
		mapView = (MapView) findViewById(R.id.bmapView);
		baiduMap = mapView.getMap();
		msu = MapStatusUpdateFactory.zoomTo(15.0f);
		baiduMap.setMapStatus(msu);



	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();

		//开启定位
		baiduMap.setMyLocationEnabled(true);
		if (!mLocationClient.isStarted()){
			mLocationClient.start();
		}

		//开启方向传感器
		myOritationLister.start();
	}

	@Override
	protected void onStop() {
		super.onStop();

		//关闭定位
		baiduMap.setMyLocationEnabled(false);
		mLocationClient.stop();

		//关闭传感器
		myOritationLister.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	/**
	 * 菜单item选择
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()){
			//普通地图
			case R.id.baidu_common:
				baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
				break;
			//卫星地图
			case R.id.baidu_site:
				baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
				break;
			//实时交通地图
			case R.id.baidu_traffic:

				if (baiduMap.isTrafficEnabled()){
					baiduMap.setTrafficEnabled(false);
					item.setTitle("实时交通(off)");

				}else {
					baiduMap.setTrafficEnabled(true);
					item.setTitle("实时交通(on)");
				}
				break;

			case R.id.baidu_mylocation:
				LatLng latLng = new LatLng(mLatitude,mLongtitude);
				MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
				baiduMap.animateMapStatus(mapStatusUpdate);
				break;

			case R.id.baidu_mode_common:
				mLocationMode= MyLocationConfiguration.LocationMode.NORMAL;
				break;

			case R.id.baidu_mode_follow:
				mLocationMode= MyLocationConfiguration.LocationMode.FOLLOWING;

				break;

			case R.id.baidu_mode_compass:
				mLocationMode= MyLocationConfiguration.LocationMode.COMPASS;
				break;

			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private class MyLocationListener implements BDLocationListener{

		//定位成功的回掉
		@Override
		public void onReceiveLocation(BDLocation bdLocation) {

			MyLocationData data = new MyLocationData.Builder()
					.direction(mCurrentX)
					.accuracy(bdLocation.getRadius())
					.latitude(bdLocation.getLatitude())
					.longitude(bdLocation.getLongitude())
					.build();

			baiduMap.setMyLocationData(data);

			MyLocationConfiguration configuration = new
					MyLocationConfiguration(mLocationMode,true,mIconLocation);

			baiduMap.setMyLocationConfigeration(configuration);

			mLatitude = bdLocation.getLatitude();
			mLongtitude = bdLocation.getLongitude();

			if (isFisrtIn){
				LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
				MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
				baiduMap.animateMapStatus(mapStatusUpdate);

				isFisrtIn = false;

				Toast.makeText(getApplicationContext(),bdLocation.getAddrStr(),Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onConnectHotSpotMessage(String s, int i) {

		}
	}
}
