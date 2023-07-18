package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.EducationInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-29
 */
public interface EducationInfoRepository extends PagingAndSortingRepository<EducationInfo, Integer> {

  /**
   * 根据id查询数据
   */
  public EducationInfo findEducationInfoById(int id);

  /**
   * 根据openid查询数据
   */
  public List<EducationInfo> findEducationInfosByOpenid(String openid);

  /**
   * 根据openid和学历查询数据
   */
  public List<EducationInfo> findEducationInfosByOpenidAndDegree(String openid, String degree);

}
