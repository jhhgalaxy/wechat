package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author JINLINGXUAN
 * @create 2020-11-02
 * 招聘系统-家庭信息数据表
 */

@Entity
@Table(name = "hire_familyinfo", schema = "wechatbusiness")
public class FamilyInfo {

    @JsonIgnore
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty(value = "openid")
    @Column(length = 100)
    private String openid;//用户唯一标识
    @JsonProperty(value = "relationship")
    @Column(length = 100)
    private String relationship;//关系
    @JsonProperty(value = "name")
    @Column(length = 50)
    private String name;//姓名
    @JsonProperty(value = "birth")
    @Column(length = 50)
    private String birth;//出生日期
    @JsonProperty(value = "work")
    @Column(length = 100)
    private String work;//工作单位、职务
    @JsonProperty(value = "political")
    @Column(length = 50)
    private String political;//政治面貌

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getRelationship() {
      return relationship;
    }

    public void setRelationship(String relationship) {
      this.relationship = relationship;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getBirth() {
      return birth;
    }

    public void setBirth(String birth) {
      this.birth = birth;
    }

    public String getWork() {
      return work;
    }

    public void setWork(String work) {
      this.work = work;
    }

    public String getPolitical() {
      return political;
    }

    public void setPolitical(String political) {
      this.political = political;
    }

    public String getOpenid() {
      return openid;
    }

    public void setOpenid(String openid) {
      this.openid = openid;
    }
}
