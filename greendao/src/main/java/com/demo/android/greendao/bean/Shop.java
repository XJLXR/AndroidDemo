package com.demo.android.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by XiongRun on 2017/3/13.
 *
 @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
 @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
 @Property：可以自定义字段名，注意外键不能使用该属性
 @NotNull：属性不能为空
 @Transient：使用该注释的属性不会被存入数据库的字段中
 @Unique：该属性值必须在数据库中是唯一值
 @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

 */

@Entity
public class Shop {
	//表示为购物车列表
	public static final int TYPE_CART = 0x01;
	//表示为收藏列表
	public static final int TYPE_LOVE = 0x02;

	@Id(autoincrement = true)
	private Long id;

	@Unique
	private String name;

	@Property(nameInDb = "price")
	private String price;

	private int sell_num;

	private String image_url;

	private String address;

	private int type;

	@Generated(hash = 1304458862)
	public Shop(Long id, String name, String price, int sell_num, String image_url,
									String address, int type) {
					this.id = id;
					this.name = name;
					this.price = price;
					this.sell_num = sell_num;
					this.image_url = image_url;
					this.address = address;
					this.type = type;
	}

	@Generated(hash = 633476670)
	public Shop() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getSell_num() {
		return sell_num;
	}

	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"id=" + id +
				", name='" + name + '\'' +
				", price='" + price + '\'' +
				", sell_num=" + sell_num +
				", image_url='" + image_url + '\'' +
				", address='" + address + '\'' +
				", type=" + type +
				'}';
	}
}
