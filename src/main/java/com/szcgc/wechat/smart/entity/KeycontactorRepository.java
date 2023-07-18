/**
 * Project:szcgcWechatServer
 * File:KeycontactorRepository.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
public interface KeycontactorRepository extends PagingAndSortingRepository<Keycontactor, Integer>{

    List<Keycontactor> findDistinctCustomidByCellphone(String cellphone);

    List<Keycontactor> findDistinctCellphoneByCustomid(String customid);

}
