package com.wxm.model;

import cn.bmob.v3.BmobObject;

/**
 * 讨论实体类
 * 
 */
@SuppressWarnings("serial")
public class Chat extends BmobObject {

	private String username;
	private String chat;
	private String projectid;
	private int type; //

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

}
