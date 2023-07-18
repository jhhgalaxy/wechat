/**
 * Project:szcgcWechatServer
 * File:User.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.user.entity;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user", schema = "wechatbusiness")
public class User {

	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(updatable = false, nullable = false, unique = true,length = 100)
	private String openid;
	
    @Column(length = 50)
	private String cellphone;
	
    @Column(length = 50)
	private String name;
	
    @Column(length = 50)
	private String idcard_num;
	
	@JsonIgnore
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime followtime;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;
	
	@JsonIgnore
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime modifytime;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the cellphone
	 */
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idcard_num
	 */
	public String getIdcard_num() {
		return idcard_num;
	}

	/**
	 * @param idcard_num the idcard_num to set
	 */
	public void setIdcard_num(String idcard_num) {
		this.idcard_num = idcard_num;
	}


	
	@PrePersist
	public void prePersist() {
		this.createtime = LocalDateTime.now();
		this.modifytime = this.createtime;
	}

	@PreUpdate
	public void preUpdate() {
		this.modifytime = LocalDateTime.now();
	}

	public LocalDateTime getFollowtime() {
		return followtime;
	}

	public void setFollowtime(LocalDateTime followtime) {
		this.followtime = followtime;
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
	
	
}
