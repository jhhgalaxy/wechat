package com.szcgc.hiresystem.service;

import com.szcgc.hiresystem.entity.DeliveryRecords;
import com.szcgc.hiresystem.query.DeliveryRecordsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-10-29
 */
@Service
public class DeliveryRecordsService {

  @Resource
  private DeliveryRecordsRepository deliveryRecordsRepository;

  public DeliveryRecords getDeliveryInfo(String openid, int positionid) {
    return deliveryRecordsRepository.findDeliveryRecordsByOpenidAndPositionId(openid, positionid);
  }

  public void saveDeliveryInfo(DeliveryRecords entity){
    deliveryRecordsRepository.save(entity);
  }

}
