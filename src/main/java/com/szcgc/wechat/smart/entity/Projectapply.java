/**
 * Project:szcgcWechatServer
 * File:Projectapply.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import java.math.BigDecimal;
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
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */

@Entity
@Table(name = "projectapply",schema = "wechatbusiness")
public class Projectapply {

	public static final byte NO = 0;
	public static final byte YES = 1;
	
	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,length = 50)
	private String corpname;
	
	@Column(nullable = false,length = 20)
	private String contactor;
	
	@Column(nullable = false,length = 20)
	private String cellphone;
	
	@Column(nullable = false)
	private int industry;
	
	@Column(nullable = false)
	private int empolyee_num;
	
	@Column(nullable = false)
	private int area;
	
	@Column(nullable = false,columnDefinition = "DECIMAL(15,2)")
	private BigDecimal income;
	
	@Column(nullable = false)
	private byte knowledge;
	
	@Column(nullable = false)
	private byte invest;
	
	@Column(nullable = false)
	private byte loanrecord;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;
	
	@PrePersist
	public void prePersist() {
		this.createtime = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public int getIndustry() {
		return industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public int getEmpolyee_num() {
		return empolyee_num;
	}

	public void setEmpolyee_num(int empolyee_num) {
		this.empolyee_num = empolyee_num;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public byte getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(byte knowledge) {
		this.knowledge = knowledge;
	}

	public byte getInvest() {
		return invest;
	}

	public void setInvest(byte invest) {
		this.invest = invest;
	}

	public byte getLoanrecord() {
		return loanrecord;
	}

	public void setLoanrecord(byte loanrecord) {
		this.loanrecord = loanrecord;
	}

	public LocalDateTime getCreatetime() {
		return createtime;
	}

	public void setCreatetime(LocalDateTime createtime) {
		this.createtime = createtime;
	}
	
	
}
