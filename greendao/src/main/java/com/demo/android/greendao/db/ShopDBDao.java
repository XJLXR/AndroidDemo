package com.demo.android.greendao.db;

import com.demo.android.greendao.MyApplication;
import com.demo.android.greendao.bean.Shop;
import com.demo.android.greendao.gen.ShopDao;

import java.util.List;

/**
 * Created by XiongRun on 2017/3/13.
 */

public class ShopDBDao {

	/**
	 * 添加数据
	 * @param shop
	 */
	public static void insertData(Shop shop){
		MyApplication.getDaoSession().getShopDao().insert(shop);
	}

	/**
	 * 删除数据
	 *
	 * @param id
	 */
	public static void deleteData(long id) {
		MyApplication.getDaoSession().getShopDao().deleteByKey(id);
	}

	/**
	 * 更新数据
	 *
	 * @param shop
	 */
	public static void updateData(Shop shop) {
		MyApplication.getDaoSession().getShopDao().update(shop);
	}

	/**
	 * 查询条件为Type=TYPE_LOVE的数据
	 *
	 * @return
	 */
	public static List<Shop> queryData() {
		return MyApplication.getDaoSession().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
	}

}
