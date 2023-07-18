/**
 * Project:szcgcWechatServer
 * File:Suggestion.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
@Entity
@Table(name = "suggestion",schema = "wechatbusiness")
public class Suggestion {
	
	public static final String NORMAL_CUSTOM ="普通客户";
	public static final String GUARANTEE_CUSTOM ="在保客户";

	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonIgnore
	@Column(nullable = false,length = 11)
	private int complainant;

	@Column(nullable = false,length = 20)
	private String cellphone;
	
	@Column(length = 1000)
	private String content;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime modifytime;
	
	@Column(length = 1000)
	private String reply;
	
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime replytime;
	
	@JsonIgnore
	@Column(length = 50)
	private String custom_corp;
	
	@JsonIgnore
	@Column(length = 20)
	private String custom_type;
	
	
	
	
	@PrePersist
	public void prePersist() {
		this.createtime = LocalDateTime.now();
		this.modifytime = this.createtime;
	}

	@PreUpdate
	public void preUpdate() {
		this.modifytime = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComplainant() {
		return complainant;
	}

	public void setComplainant(int complainant) {
		this.complainant = complainant;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public LocalDateTime getModifytime() {
		return modifytime;
	}

	public void setModifytime(LocalDateTime modifytime) {
		this.modifytime = modifytime;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public LocalDateTime getReplytime() {
		return replytime;
	}

	public void setReplytime(LocalDateTime replytime) {
		this.replytime = replytime;
	}

	public String getCustom_corp() {
		return custom_corp;
	}

	public void setCustom_corp(String custom_corp) {
		this.custom_corp = custom_corp;
	}

	public String getCustom_type() {
		return custom_type;
	}

	public void setCustom_type(String custom_type) {
		this.custom_type = custom_type;
	}
}
