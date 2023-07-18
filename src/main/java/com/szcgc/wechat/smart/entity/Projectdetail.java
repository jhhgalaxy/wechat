/**
 * Project:szcgcWechatServer
 * File:Projectdetail.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import com.szcgc.wechat.util.CustomJsonLocalDateDeserializer;
import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.szcgc.wechat.util.LocalDateAttributeConverter;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
@Entity
@Table(name = "projectdetail",schema = "wechatbusiness")
public class Projectdetail {

	public static final int UNLOAN = 0;//未放款
	public static final int LOANING = 1;//放款中
	public static final int LOANED = 2;//已解保

	@JsonIgnore
	@Id
	@Column(name = "id", length = 11)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonProperty(value = "mainid")
	@Column(length = 20)
	private int mainid;

	@Column(nullable = false,length = 20)
	private String customid;

	@Column(nullable = false,length= 20)
	private String projectcode;
	
	@Column(nullable = false,length = 50)
	private String projectname;

	@JsonProperty(value = "business")
	@Column(nullable = false,length = 50)
	private String businessclass;

	@JsonProperty(value = "amount")
	@Column(nullable = false,columnDefinition = "DECIMAL(15,2)")
	private BigDecimal loanamount;

	@JsonProperty(value = "projectAmount")
	@Column(nullable = false,columnDefinition = "DECIMAL(15,2)")
	private BigDecimal projectamount;

	@JsonProperty(value = "startDate")
	@JsonDeserialize(using = CustomJsonLocalDateDeserializer.class)
	@Convert(converter = LocalDateAttributeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate startdate;

	@JsonProperty(value = "endDate")
	@JsonDeserialize(using = CustomJsonLocalDateDeserializer.class)
	@Convert(converter = LocalDateAttributeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate enddate;

	@JsonProperty(value = "releaseDate")
	@JsonDeserialize(using = CustomJsonLocalDateDeserializer.class)
	@Convert(converter = LocalDateAttributeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private LocalDate releasedate;
	
	@Column(length = 20)
	private String manager;
	
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate next_principal_date;
	
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate next_interest_date;
	
	@Column(columnDefinition = "DECIMAL(15,2)")
	private BigDecimal next_principal_amount;
	
	@Column(columnDefinition = "DECIMAL(15,2)")
	private BigDecimal next_interest_amount;


	@Column(length = 20)
	private String phoneNum;

	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime createtime;
	
	@JsonIgnore
	@Column(updatable = false)
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	private LocalDateTime modifytime;

	@Column(updatable = false)
	private int guaranteeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMainid() {
		return mainid;
	}

	public void setMainid(int mainid) {
		this.mainid = mainid;
	}

	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getBusinessclass() {
		return businessclass;
	}

	public void setBusinessclass(String businessclass) {
		this.businessclass = businessclass;
	}

	public BigDecimal getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(BigDecimal loanamount) {
		this.loanamount = loanamount;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public LocalDate getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(LocalDate releasedate) {
		this.releasedate = releasedate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public LocalDate getNext_principal_date() {
		return next_principal_date;
	}

	public void setNext_principal_date(LocalDate next_principal_date) {
		this.next_principal_date = next_principal_date;
	}

	public LocalDate getNext_interest_date() {
		return next_interest_date;
	}

	public void setNext_interest_date(LocalDate next_interest_date) {
		this.next_interest_date = next_interest_date;
	}

	public BigDecimal getNext_principal_amount() {
		return next_principal_amount;
	}

	public void setNext_principal_amount(BigDecimal next_principal_amount) {
		this.next_principal_amount = next_principal_amount;
	}

	public BigDecimal getNext_interest_amount() {
		return next_interest_amount;
	}

	public void setNext_interest_amount(BigDecimal next_interest_amount) {
		this.next_interest_amount = next_interest_amount;
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

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public BigDecimal getProjectamount() {
		return projectamount;
	}

	public void setProjectamount(BigDecimal projectamount) {
		this.projectamount = projectamount;
	}

	public int getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(int guaranteeId) {
		this.guaranteeId = guaranteeId;
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
}
