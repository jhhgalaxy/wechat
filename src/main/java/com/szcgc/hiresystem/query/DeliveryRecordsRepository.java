package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.DeliveryRecords;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-10-29
 */

public interface DeliveryRecordsRepository extends PagingAndSortingRepository<DeliveryRecords,Integer> {

  /**
   * 查询用户是否投递过该岗位
   * */
  public DeliveryRecords findDeliveryRecordsByOpenidAndPositionId(String openid,int positionid);

}
