package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author JINLINGXUAN
 * @create 2020-11-02
 * 招聘系统-教育信息数据表
 */

@Entity
@Table(name = "hire_educationinfo", schema = "wechatbusiness")
public class EducationInfo {

  @JsonIgnore
  @Id
  @Column(name = "id", length = 11)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @JsonProperty(value = "openid")
  @Column(length = 100)
  private String openid;//用户唯一标识
  @JsonProperty(value = "startDate")
  @Column(length = 20)
  private String startDate;//开始日期
  @JsonProperty(value = "endDate")
  @Column(length = 20)
  private String endDate;//结束日期
  @JsonProperty(value = "school")
  @Column(length = 100)
  private String school;//毕业院校（全称）
  @JsonProperty(value = "professional")
  @Column(length = 100)
  private String professional;//专业
  @JsonProperty(value = "degree")
  @Column(length = 20)
  private String degree;//学位
  @JsonProperty(value = "contact")
  @Column(length = 50)
  private String contact;//证明人
  @JsonProperty(value = "tel")
  @Column(length = 50)
  private String tel;//联系方式

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getSchool() {
    return school;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public String getProfessional() {
    return professional;
  }

  public void setProfessional(String professional) {
    this.professional = professional;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }
}
