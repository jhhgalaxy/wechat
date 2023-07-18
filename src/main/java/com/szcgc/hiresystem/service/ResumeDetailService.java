package com.szcgc.hiresystem.service;

import com.szcgc.hiresystem.entity.*;
import com.szcgc.hiresystem.query.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-11-02
 */
@Service
public class ResumeDetailService {

  @Resource
  private EducationInfoRepository educationInfoRepository;
  @Resource
  private FamilyInfoRepository familyInfoRepository;
  @Resource
  private PracticeInfoRepository practiceInfoRepository;
  @Resource
  private SchoolInfoRepository schoolInfoRepository;
  @Resource
  private WorkInfoRepository workInfoRepository;

  /**
   * 删除指定ID的教育信息
   */
  public void deleteEducationInfo(int id) {
    educationInfoRepository.deleteById(id);
  }

  /**
   * 删除指定ID的家庭信息
   */
  public void deleteFamilyInfo(int id) {
    familyInfoRepository.deleteById(id);
  }

  /**
   * 删除指定ID的实习信息
   */
  public void deletePracticeInfo(int id) {
    practiceInfoRepository.deleteById(id);
  }

  /**
   * 删除指定ID的工作信息
   */
  public void deleteWorkInfo(int id) {
    workInfoRepository.deleteById(id);
  }

  /**
   * 根据ID获取教育信息
   */
  public EducationInfo findEducationInfoById(int id) {
    return educationInfoRepository.findEducationInfoById(id);
  }

  /**
   * 根据openid查询教育信息
   */
  public List<EducationInfo> findEducationInfoByOpenid(String openid) {
    return educationInfoRepository.findEducationInfosByOpenid(openid);
  }

  /**
   * 根据ID获取家庭信息
   */
  public FamilyInfo findFamilyInfoById(int id) {
    return familyInfoRepository.findFamilyInfoById(id);
  }

  /**
   * 根据openid查询家庭信息
   */
  public List<FamilyInfo> findFamilyInfoByOpenid(String openid) {
    return familyInfoRepository.findFamilyInfosByOpenid(openid);
  }

  /**
   * 根据ID获取实习信息
   */
  public PracticeInfo findPracticeInfoById(int id) {
    return practiceInfoRepository.findPracticeInfoById(id);
  }

  /**
   * 根据openid查询实习信息
   */
  public List<PracticeInfo> findPracticeInfoByOpenid(String openid) {
    return practiceInfoRepository.findPracticeInfosByOpenid(openid);
  }

  /**
   * 查询院校信息
   */
  public List<SchoolInfo> findSchoolInfos() {
    return (List<SchoolInfo>) schoolInfoRepository.findAll();
  }

  /**
   * 根据openid查询工作信息
   */
  public List<WorkInfo> findWorkInfoByOpenid(String openid) {
    return workInfoRepository.findWorkInfosByOpenid(openid);
  }

  /**
   * 根据ID获取工作信息
   */
  public WorkInfo findWorkInfoInfoById(int id) {
    return workInfoRepository.findWorkInfoById(id);
  }

  /**
   * 根据openid查询是否有教育信息
   */
  public boolean hasEducationInfo(String openid) {
    if (educationInfoRepository.findEducationInfosByOpenid(openid).size()>0) {
      EducationInfo educationInfo = educationInfoRepository.findEducationInfosByOpenid(openid).get(0);
      if (educationInfo.getId() > 0) {
        return true;
      }
    }
    return false;

  }

  /**
   * 查询是否有对应学历的教育信息
   */
  public boolean hasEducationInfoByDegree(String openid, String degree) {
    if (educationInfoRepository.findEducationInfosByOpenidAndDegree(openid, degree).size()>0) {
      EducationInfo educationInfo = educationInfoRepository.findEducationInfosByOpenidAndDegree(openid, degree).get(0);
      if (educationInfo.getId() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * 根据openid查询是否有家庭信息
   */
  public boolean hasFamilyInfo(String openid) {
    if (familyInfoRepository.findFamilyInfosByOpenid(openid).size()>0) {
      FamilyInfo familyInfo = familyInfoRepository.findFamilyInfosByOpenid(openid).get(0);
      if (familyInfo.getId() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * 更新教育信息
   */
  public EducationInfo updateEducationInfo(EducationInfo educationInfo) {
    return educationInfoRepository.save(educationInfo);
  }

  /**
   * 更新家庭信息
   */
  public FamilyInfo updateFamilyInfo(FamilyInfo familyInfo) {
    return familyInfoRepository.save(familyInfo);
  }

  /**
   * 更新实习信息
   */
  public PracticeInfo updatePracticeInfo(PracticeInfo practiceInfo) {
    return practiceInfoRepository.save(practiceInfo);
  }

  /**
   * 更新工作信息
   */
  public WorkInfo updateWorkInfo(WorkInfo workInfo) {
    return workInfoRepository.save(workInfo);
  }

}
