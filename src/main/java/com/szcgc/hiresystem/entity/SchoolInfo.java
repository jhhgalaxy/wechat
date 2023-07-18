package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author JINLINGXUAN
 * @create 2020-11-03
 * 招聘系统-985、211学校名单
 */

@Entity
@Table(name = "hire_schoolinfo", schema = "wechatbusiness")
public class SchoolInfo {
  @JsonIgnore
  @Id
  @Column(name = "id", length = 11)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @JsonProperty(value = "type")
  @Column(length = 20)
  private String type;//类型
  @JsonProperty(value = "school")
  @Column(length = 50)
  private String school;//学校名称
}
