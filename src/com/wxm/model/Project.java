package com.wxm.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * 项目实体类
 * 
 */
@SuppressWarnings("serial")
public class Project extends BmobObject {

	private String name;
	private String desc;
	private String target;
	private String request;
	private String teacher;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}
