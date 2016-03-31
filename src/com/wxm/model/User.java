package com.wxm.model;

import cn.bmob.v3.BmobUser;

/**
 * 用户实体类
 * 
 * @date 2014-4-24
 * @author Stone
 */
@SuppressWarnings("serial")
public class User extends BmobUser {

	// 父类中已经存在的属性
	// private String id;
	// private String username;
	// private String password;
	// private String email;
	// private String regTime;

	private String phone; // 电话

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
