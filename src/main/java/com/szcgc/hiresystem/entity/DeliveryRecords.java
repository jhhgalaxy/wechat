package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.szcgc.wechat.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author JINLINGXUAN
 * @create 2020-10-29
 * 招聘系统-投递记录数据表
 */
@Entity
@Table(name = "hire_deliveryRecords", schema = "wechatbusiness")
public class DeliveryRecords {

  @JsonProperty(value = "deliveryDate")
  @Column(nullable = false, length = 50)
  private String deliveryDate;//投递日期
  @JsonProperty(value = "deliveryTime")
  @Column(nullable = false, length = 50)
  private String deliveryTime;//投递时间
  @JsonIgnore
  @Id
  @Column(name = "id", length = 11)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @JsonIgnore
  @Column(updatable = false)
  @Convert(converter = LocalDateTimeAttributeConverter.class)
  private LocalDateTime modifytime;
  @JsonProperty(value = "openid")
  @Column(nullable = false, length = 50)
  private String openid;//openid
  @JsonProperty(value = "positionId")
  @Column(nullable = false, length = 20)
  private int positionId;//OA中的岗位ID

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(String deliveryTime) {
    this.deliveryTime = deliveryTime;
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

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public int getPositionId() {
    return positionId;
  }

  public void setPositionId(int positionId) {
    this.positionId = positionId;
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
