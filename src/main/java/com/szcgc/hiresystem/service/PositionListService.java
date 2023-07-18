package com.szcgc.hiresystem.service;

import com.szcgc.hiresystem.entity.PositionList;
import com.szcgc.hiresystem.query.PositionListRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JINLINGXUAN
 * @create 2020-09-27
 */
@Service
public class PositionListService {

  @Resource
  private PositionListRepository positionListRepository;

  public PositionList findPositionById(int positionid) {
    return positionListRepository.findPositionListByPositionid(positionid);//根据OA的岗位ID查询岗位信息
  }

  public List<Map<String, String>> getSimplePositionList(String type) {
    List<Map<String, String>> returnList = new ArrayList<>();
    Map<String, String> map = new HashMap<>();
    List<Object> list = positionListRepository.getSimplePositionLists(type);
    for (Object row : list) {
      Object[] cells = (Object[]) row;
      map = new HashMap<>();
      map.put("id", String.valueOf((int) cells[0]));
      map.put("name", (String) cells[1]);
      map.put("num", (String) cells[2]);
      map.put("type", (String) cells[3]);
      map.put("workplace", (String) cells[4]);
      map.put("education", (String) cells[5]);
      returnList.add(map);
    }
    return returnList;
  }

  public void deleteAll(){
    positionListRepository.deleteAll();
  }

  public PositionList update(PositionList entity) {
    return positionListRepository.save(entity);
  }

}
