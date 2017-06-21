package com.demo.android.greendao;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.demo.android.greendao.gen.DaoMaster;
import com.demo.android.greendao.gen.DaoSession;

/**
 * Created by XiongRun on 2017/3/13.
 *
 *
 */

public class MyApplication extends Application {

	public static Context mContext;
	private static MyApplication instance;
	private static SQLiteDatabase db;
	private static DaoMaster daoMaster;
	private static DaoSession daoSession;

	public MyApplication(){

		getAppContext();
	}
	// 单例模式中获取唯一的MyApplication实例
	public static MyApplication getInstance(){

		synchronized (MyApplication.class){
			if (instance == null){
				synchronized (MyApplication.class){
					instance = new MyApplication();
				}
			}
		}
		return instance;
	}

	public static Context getAppContext() {
		return mContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		setUpDataBase();
	}

	/**
	 * 配置数据库
	 *
	 DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
	 DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
	 DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API

	 */
	private void setUpDataBase() {

		//创建数据库shop.db"
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,"shop.db",null);
		//获取可写数据库
		db = helper.getWritableDatabase();
		//获取数据库对象
		daoMaster = new DaoMaster(db);
		//获取Dao对象管理者
		daoSession = daoMaster.newSession();
	}

	public static DaoSession getDaoSession() {
		return daoSession;
	}

	public static DaoMaster getDaoMaster() {
		return daoMaster;
	}

	public static SQLiteDatabase getDb(){
		return db;
	}

}
