package com.demo.android.baserecycle;

/**
 * Created by XiongRun on 2017/6/27.
 */

public class Shop {

	//表示为购物车列表
	public static final int TYPE_CART = 0x01;
	//表示为收藏列表
	public static final int TYPE_LOVE = 0x02;
	private Long id;
	private String name;
	private String price;
	private int sell_num;
	private String image_url;
	private String address;
	private int type;

	public static int getTypeCart() {
		return TYPE_CART;
	}

	public static int getTypeLove() {
		return TYPE_LOVE;
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
