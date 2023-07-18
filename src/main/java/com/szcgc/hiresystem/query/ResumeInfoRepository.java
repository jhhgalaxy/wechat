package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.ResumeInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-29
 */
public interface ResumeInfoRepository extends PagingAndSortingRepository<ResumeInfo, Integer> {

  /**
   * 根据openid查询简历ID
   */
  public ResumeInfo findResumeInfoByOpenid(String openid);

  /**
   * 根据id查询简历信息
   * */
  public ResumeInfo findResumeInfoById(int id);

}
