package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.PracticeInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-29
 */
public interface PracticeInfoRepository extends PagingAndSortingRepository<PracticeInfo, Integer> {

  /**
   * 根据id查询数据
   */
  public PracticeInfo findPracticeInfoById(int id);

  /**
   * 根据openid查询数据
   */
  public List<PracticeInfo> findPracticeInfosByOpenid(String openid);

}
