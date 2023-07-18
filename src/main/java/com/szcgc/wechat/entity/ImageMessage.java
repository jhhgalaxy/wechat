package com.szcgc.wechat.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复图片消息类
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
	
	@XStreamAlias("Image")
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageMessage(Map<String, String> requestMap, Image image) {
		super(requestMap);
		this.setMsgType("image");
		this.image = image;
	}
	
}
