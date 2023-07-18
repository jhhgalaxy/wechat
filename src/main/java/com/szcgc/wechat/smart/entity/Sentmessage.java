/**
 * Project:szcgcWechatServer
 * File:Sentmessage.java
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.szcgc.wechat.smart.MsgTypeEm;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
@Entity
@Table(name = "sentmessage",schema = "wechatbusiness")
public class Sentmessage {
	public static final int PROGRESS_MSG = 0;
	public static final int CHARGE_MSG = 1;
	public static final int RETURN_MSG = 2;
	public static final int SUGGEST_REPLY_MSG = 3;
	
	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 11)
	private int receiver;
	
	@Column(nullable = false)
	private int msgtype;
	
	@Column(length = 1000)
	private String msgcontent;
	
	@Column(length = 100)
	private String project;
	
	@Column(length = 20)
	private int linkitem;
	
	@Column(nullable = false,length = 50)
	private String msgtitle;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;

	@Column(length = 20)
	private String manager;

	@Column(length = 20)
	private String managerphone;

	private String typename;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
		this.typename = MsgTypeEm.getNameByKey(String.valueOf(this.msgtype));
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getLinkitem() {
		return linkitem;
	}

	public void setLinkitem(int linkitem) {
		this.linkitem = linkitem;
	}

	public String getMsgtitle() {
		return msgtitle;
	}

	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getManagerphone() {
		return managerphone;
	}

	public void setManagerphone(String managerphone) {
		this.managerphone = managerphone;
	}
	
	@PrePersist
	public void prePersist() {
		this.createtime = LocalDateTime.now();
	}
}
