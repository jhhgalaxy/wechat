/**
 * Project:szcgcWechatServer
 * File:Custom.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */

@Entity
@Table(name = "custom",schema = "wechatbusiness")
public class Custom {

	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 50)
	private String customName;
	
	@JsonIgnore
	@Column(length = 20)
	private String customCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
}
