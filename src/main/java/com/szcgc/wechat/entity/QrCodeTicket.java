package com.szcgc.wechat.entity;

import java.util.Map;

/**
 * 带参数的临时二维码tiket
 * 
 * @author yangyc
 *
 */
public class QrCodeTicket {

	private String expire_seconds;
	private String action_name = "QR_STR_SCENE";
	private Map<String, SceneData> action_info;

	public String getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public String getAction_name() {
		return action_name;
	}

	public Map<String, SceneData> getAction_info() {
		return action_info;
	}

	public void setAction_info(Map<String, SceneData> action_info) {
		this.action_info = action_info;
	}

	public QrCodeTicket(String expire_seconds, Map<String, SceneData> action_info) {
		super();
		this.expire_seconds = expire_seconds;
		this.action_info = action_info;
	}

}
