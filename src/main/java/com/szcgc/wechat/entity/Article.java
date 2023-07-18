package com.szcgc.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复图文消息类中的Article类，
 * 
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("item")
public class Article {
	
	@XStreamAlias("Title")
	private String title;
	@XStreamAlias("Description")
	private String description;
	@XStreamAlias("PirUrl")
	private String pirUrl;
	@XStreamAlias("Url")
	private String url;

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

	public String getPirUrl() {
		return pirUrl;
	}

	public void setPirUrl(String pirUrl) {
		this.pirUrl = pirUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Article(String title, String description, String pirUrl, String url) {
		super();
		this.title = title;
		this.description = description;
		this.pirUrl = pirUrl;
		this.url = url;
	}

}
