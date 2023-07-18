package com.szcgc.hiresystem.query;

import com.szcgc.hiresystem.entity.PositionList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author JINLINGXUAN
 * @create 2020-09-27
 */

public interface PositionListRepository extends PagingAndSortingRepository<PositionList,Integer> {
  /**
   * 查询完整的招聘岗位信息
   */
  public PositionList findPositionListByPositionid(int positionid);

  /**
   * 查询粗略的招聘岗位信息
   */
  @Query(value = "select positionid,name,num,type,case when workplace like '%、%' then CONCAT(SUBSTRING_INDEX(workplace,'、',1),'等') else workplace end 'workplace',education from hire_positionlist where type=?1 and valid=0", nativeQuery=true)
  public List<Object> getSimplePositionLists(String type);

}
