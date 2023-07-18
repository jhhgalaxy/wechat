package com.szcgc.wechat.entity;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复语音消息类
 * 
 * @author yangyc
 * @date 2020-3-23
 */
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
	@XStreamAlias("Voice")
	private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public VoiceMessage(Map<String, String> requestMap, Voice voice) {
		super(requestMap);
		this.setMsgType("voice");
		this.voice = voice;
	}

}
