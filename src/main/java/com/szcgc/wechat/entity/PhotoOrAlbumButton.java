package com.szcgc.wechat.entity;

import java.util.ArrayList;
import java.util.List;

public class PhotoOrAlbumButton extends AbstractBtn {

	private String type = "pic_photo_or_album";
	private String key;
	private List<AbstractBtn> sub_button = new ArrayList<>();

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<AbstractBtn> getSub_button() {
		return sub_button;
	}

	public PhotoOrAlbumButton(String name, String key) {
		super(name);
		this.key = key;
	}

}
