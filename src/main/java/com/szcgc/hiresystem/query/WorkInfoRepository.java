package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.WorkInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-29
 */
public interface WorkInfoRepository extends PagingAndSortingRepository<WorkInfo, Integer> {

  /**
   * 根据id查询数据
   */
  public WorkInfo findWorkInfoById(int id);

  /**
   * 根据openid查询数据
   */
  public List<WorkInfo> findWorkInfosByOpenid(String openid);

}
