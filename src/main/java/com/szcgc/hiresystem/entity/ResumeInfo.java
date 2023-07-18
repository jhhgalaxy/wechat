package com.szcgc.hiresystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author JINLINGXUAN
 * @create 2020-09-28
 * 招聘系统-简历信息数据表
 */
@Entity
@Table(name = "hire_resumeinfo", schema = "wechatbusiness")
public class ResumeInfo {

  @JsonProperty(value = "address")
  @Column(length = 300)
  private String address;//现住址
  @JsonProperty(value = "birthday")
  @Column(length = 100)
  private String birthday;//出生日期
  @JsonProperty(value = "computer")
  @Column(length = 100)
  private String computer;//电脑能力
  @JsonProperty(value = "driver")
  @Column(length = 100)
  private String driver;//驾照情况
  @JsonProperty(value = "driverAge")
  @Column(length = 100)
  private String driverAge;//驾龄
  @JsonProperty(value = "email")
  @Column(length = 100)
  private String email;//电子邮箱
  @JsonProperty(value = "emergency")
  @Column(length = 100)
  private String emergency;//紧急联系人
  @JsonProperty(value = "emergencyTel")
  @Column(length = 100)
  private String emergencyTel;//紧急联系电话
  @JsonProperty(value = "english")
  @Column(length = 300)
  private String english;//英语能力
  @JsonProperty(value = "fertility")
  @Column(length = 100)
  private String fertility;//生育状况
  @JsonProperty(value = "health")
  @Column(length = 100)
  private String health;//健康情况
  @JsonProperty(value = "height")
  @Column(length = 100)
  private String height;//身高(cm)
  @JsonProperty(value = "hobbies")
  @Column(length = 999)
  private String hobbies;//爱好特长
  @JsonIgnore
  @Id
  @Column(name = "id", length = 11)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @JsonProperty(value = "idCardNo")
  @Column(length = 100)
  private String idCardNo;//身份证号码
  @JsonProperty(value = "marriage")
  @Column(length = 100)
  private String marriage;//婚姻状况
  @JsonProperty(value = "name")
  @Column(length = 100)
  private String name;//姓名
  @JsonProperty(value = "nation")
  @Column(length = 100)
  private String nation;//民族
  @JsonProperty(value = "notes")
  @Column(length = 5000)
  private String notes;//备注
  @JsonProperty(value = "openid")
  @Column(length = 100)
  private String openid;//微信openid
  @JsonProperty(value = "others")
  @Column(length = 5000)
  private String others;//补充信息
  @JsonProperty(value = "pay")
  @Column(length = 100)
  private String pay;//薪资要求
  @JsonProperty(value = "photo")
  @Column(length = 500)
  private String photo;//个人照片路径
  @JsonProperty(value = "political")
  @Column(length = 100)
  private String political;//政治面貌
  @JsonProperty(value = "positions")
  @Column(length = 999)
  private String positions;//已投递的岗位
  @JsonProperty(value = "qq")
  @Column(length = 100)
  private String qq;//QQ号码
  @JsonProperty(value = "qualifications")
  @Column(length = 999)
  private String qualifications;//从业资格/技术职称
  @JsonProperty(value = "region")
  @Column(length = 100)
  private String region;//籍贯
  @JsonProperty(value = "relationship")
  @Column(length = 100)
  private String relationship;//与紧急联系人关系
  @JsonProperty(value = "residence")
  @Column(length = 100)
  private String residence;//户口所在地
  @JsonProperty(value = "sex")
  @Column(length = 100)
  private String sex;//性别
  @JsonProperty(value = "source")
  @Column(length = 100)
  private String source;//出生地
  @JsonProperty(value = "tel")
  @Column(length = 100)
  private String tel;//联系电话
  @JsonProperty(value = "updateDate")
  @Column(length = 100)
  private String updateDate;//更新日期
  @JsonProperty(value = "updateTime")
  @Column(length = 100)
  private String updateTime;//更新时间
  @JsonProperty(value = "workDate")
  @Column(length = 100)
  private String workDate;//参加工作时间
  @JsonProperty(value = "workExperience")
  @Column(length = 100)
  private String workExperience;//有无全职工作经验
  @JsonProperty(value = "workplace")
  @Column(length = 20)
  private String workplace;//生育状况

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getComputer() {
    return computer;
  }

  public void setComputer(String computer) {
    this.computer = computer;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getDriverAge() {
    return driverAge;
  }

  public void setDriverAge(String driverAge) {
    this.driverAge = driverAge;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmergency() {
    return emergency;
  }

  public void setEmergency(String emergency) {
    this.emergency = emergency;
  }

  public String getEmergencyTel() {
    return emergencyTel;
  }

  public void setEmergencyTel(String emergencyTel) {
    this.emergencyTel = emergencyTel;
  }

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getFertility() {
    return fertility;
  }

  public void setFertility(String fertility) {
    this.fertility = fertility;
  }

  public String getHealth() {
    return health;
  }

  public void setHealth(String health) {
    this.health = health;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getHobbies() {
    return hobbies;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getMarriage() {
    return marriage;
  }

  public void setMarriage(String marriage) {
    this.marriage = marriage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNation() {
    return nation;
  }

  public void setNation(String nation) {
    this.nation = nation;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getOthers() {
    return others;
  }

  public void setOthers(String others) {
    this.others = others;
  }

  public String getPay() {
    return pay;
  }

  public void setPay(String pay) {
    this.pay = pay;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getPolitical() {
    return political;
  }

  public void setPolitical(String political) {
    this.political = political;
  }

  public String getPositions() {
    return positions;
  }

  public void setPositions(String positions) {
    this.positions = positions;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getQualifications() {
    return qualifications;
  }

  public void setQualifications(String qualifications) {
    this.qualifications = qualifications;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getRelationship() {
    return relationship;
  }

  public void setRelationship(String relationship) {
    this.relationship = relationship;
  }

  public String getResidence() {
    return residence;
  }

  public void setResidence(String residence) {
    this.residence = residence;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getWorkDate() {
    return workDate;
  }

  public void setWorkDate(String workDate) {
    this.workDate = workDate;
  }

  public String getWorkExperience() {
    return workExperience;
  }

  public void setWorkExperience(String workExperience) {
    this.workExperience = workExperience;
  }

  public String getWorkplace() {
    return workplace;
  }

  public void setWorkplace(String workplace) {
    this.workplace = workplace;
  }

}
