package com.szcgc.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复图片消息类中的Image类，
 * 
 * @author yangyc
 * @date 2020-3-23
 */
public class Image {
	@XStreamAlias("MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Image(String mediaId) {
		super();
		this.mediaId = mediaId;
	}

}
