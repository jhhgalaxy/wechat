package com.szcgc.wechat.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复图文消息类
 * 
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage {
	@XStreamAlias("ArticleCount")
	private String articleCount;
	@XStreamAlias("Articles")
	private List<Article> articles = new ArrayList<Article>();

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public NewsMessage(Map<String, String> requestMap, List<Article> articles) {
		super(requestMap);
		this.setMsgType("news");
		this.articleCount = articles.size() + "";
		this.articles = articles;
	}

}
