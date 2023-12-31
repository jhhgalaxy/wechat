package com.szcgc.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复视频消息类中的Video类，
 * 
 * @author yangyc
 * @date 2020-3-23
 */
public class Video {
	
	@XStreamAlias("MedidaId")
	private String mediaId;
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Video(String mediaId, String title, String description) {
		super();
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
	}

}
