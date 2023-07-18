package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.FamilyInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-29
 */
public interface FamilyInfoRepository extends PagingAndSortingRepository<FamilyInfo, Integer> {

  /**
   * 根据openid查询数据
   */
  public List<FamilyInfo> findFamilyInfosByOpenid(String openid);

  /**
   * 根据id查询数据
   */
  public FamilyInfo findFamilyInfoById(int id);

}
