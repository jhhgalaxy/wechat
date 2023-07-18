package com.szcgc.hiresystem.service;

import com.szcgc.hiresystem.entity.ResumeInfo;
import com.szcgc.hiresystem.query.ResumeInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-10-08
 */
@Service
public class ResumeService {

  @Resource
  private ResumeInfoRepository resumeInfoRepository;

  public List<ResumeInfo> findAllResumeInfo() {
    return (List<ResumeInfo>) resumeInfoRepository.findAll();
  }

  public int findResumeIdByOpenid(String openid) {
    if (resumeInfoRepository.findResumeInfoByOpenid(openid) != null) {
      return resumeInfoRepository.findResumeInfoByOpenid(openid).getId();
    } else {
      return 0;
    }

  }

  public ResumeInfo findResumeInfoById(int id) {
    return resumeInfoRepository.findResumeInfoById(id);
  }

  public ResumeInfo findResumeInfoByOpenid(String openid) {
    return resumeInfoRepository.findResumeInfoByOpenid(openid);
  }

  public ResumeInfo saveResumeInfo(ResumeInfo resumeInfo) {
    return resumeInfoRepository.save(resumeInfo);
  }

}
