package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author JINLINGXUAN
 * @create 2020-09-26
 * 招聘系统-招聘岗位数据表
 */
@Entity
@Table(name = "hire_positionlist", schema = "wechatbusiness")
public class PositionList {

  @JsonProperty(value = "duty")
  @Column(nullable = false, columnDefinition = "TEXT")
  private String duty;//岗位职责
  @JsonProperty(value = "education")
  @Column(nullable = false, length = 50)
  private String education;//学历要求
  @JsonIgnore
  @Id
  @Column(name = "id", length = 11)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @JsonIgnore
  @Column(updatable = false)
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime modifytime;
  @JsonProperty(value = "name")
  @Column(nullable = false, length = 50)
  private String name;//岗位名称
  @JsonProperty(value = "num")
  @Column(nullable = false, length = 50)
  private String num;//招聘人数
  @JsonProperty(value = "positionid")
  @Column(length = 20)
  private int positionid;//从OA中同步过来的ID
  @JsonProperty(value = "request")
  @Column(nullable = false, columnDefinition = "TEXT")
  private String request;//岗位要求
  @JsonProperty(value = "type")
  @Column(nullable = false, length = 10)
  private String type;//招聘类型
  @JsonProperty(value = "valid")
  @Column(nullable = false, length = 2)
  private int valid;//是否生效
  @JsonProperty(value = "workplace")
  @Column(nullable = false, length = 50)
  private String workplace;//工作地点

  public String getDuty() {
    return duty;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getModifytime() {
    return modifytime;
  }

  public void setModifytime(LocalDateTime modifytime) {
    this.modifytime = modifytime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public int getPositionid() {
    return positionid;
  }

  public void setPositionid(int positionid) {
    this.positionid = positionid;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getValid() {
    return valid;
  }

  public void setValid(int valid) {
    this.valid = valid;
  }

  public String getWorkplace() {
    return workplace;
  }

  public void setWorkplace(String workplace) {
    this.workplace = workplace;
  }

  @PrePersist
  public void prePersist() {
    this.modifytime = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.modifytime = LocalDateTime.now();
  }

}
