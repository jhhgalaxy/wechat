package com.szcgc.wechat.entity;

public class ClickButton extends AbstractBtn {
	private String type = "click";
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public ClickButton(String name, String key) {
		super(name);
		this.key = key;
	}

}
