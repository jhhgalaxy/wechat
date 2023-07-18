package com.szcgc.wechat.entity;

public class ViewButton extends AbstractBtn {

	private String type = "view";
	private String url;

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ViewButton(String name, String url) {
		super(name);
		this.url = url;
	}

}
