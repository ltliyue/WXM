package com.wxm.model;

import cn.bmob.v3.BmobUser;

/**
 * 用户实体类
 * 
 */
@SuppressWarnings("serial")
public class User extends BmobUser {

	// 父类中已经存在的属性
	// private String id;
	// private String username;
	// private String password;
	// private String email;
	// private String regTime;

	private int type; // 

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
