/**
 * Project:szcgcWechatServer
 * File:Keycontactor.java
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "keycontactor", schema = "wechatbusiness")
public class Keycontactor {

	public static final int REPRESENTATIVE = 0;
	public static final int ACTUAL_CONTROLLOR = 1;
	public static final int CFO = 0;
	
	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length=20)
	private String customid;
	
	@Column(nullable = false,length=20)
	private String cellphone;
	
	@Column(nullable = false)
	private int usertype;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}
	
	@PrePersist
	public void prePersist() {
		this.createtime = LocalDateTime.now();
	}


	
}
