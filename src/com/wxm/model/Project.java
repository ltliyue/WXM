package com.wxm.model;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

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
	private String state;

	private ArrayList<Integer> rate;
	private ArrayList<String> stunames;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<Integer> getRate() {
		return rate;
	}

	public void setRate(ArrayList<Integer> rate) {
		this.rate = rate;
	}

	public ArrayList<String> getStunames() {
		return stunames;
	}

	public void setStunames(ArrayList<String> stunames) {
		this.stunames = stunames;
	}

}
