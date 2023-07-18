package com.szcgc.wechat.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复音乐消息类
 * 
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
	@XStreamAlias("Music")
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public MusicMessage(Map<String, String> requestMap, Music music) {
		super(requestMap);
		this.setMsgType("music");
		this.music = music;
	}

}
