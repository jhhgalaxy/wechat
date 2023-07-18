package com.szcgc.wechat.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复视屏消息类
 * 
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
	@XStreamAlias("Video")
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public VideoMessage(Map<String, String> requestMap, Video video) {
		super(requestMap);
		this.setMsgType("video");
		this.video = video;
	}
	
}
