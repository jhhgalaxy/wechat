package com.szcgc.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 被动回复用户消息，回复语音消息类中的Voice类，
 * 
 * @author yangyc
 * @date 2020-3-23
 */
public class Voice {

	@XStreamAlias("MedidaId")
	private String medidaId;

	public String getMedidaId() {
		return medidaId;
	}

	public void setMedidaId(String medidaId) {
		this.medidaId = medidaId;
	}

	public Voice(String medidaId) {
		super();
		this.medidaId = medidaId;
	}

}
