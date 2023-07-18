package com.szcgc.wechat.entity;

/**
 * 抽象类，不能实例化对象
 * 
 * @author yangyc
 * @date 2020-3-26
 */
public abstract class AbstractBtn {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractBtn(String name) {
		super();
		this.name = name;
	}

}
