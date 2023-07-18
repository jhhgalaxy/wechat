package com.szcgc.wechat.entity;

public class TemplateMsgData {
	//参数值
	private String value;
	//颜色
	private String color;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public TemplateMsgData(String value, String color) {
		super();
		this.value = value;
		this.color = color;
	}
	
}
