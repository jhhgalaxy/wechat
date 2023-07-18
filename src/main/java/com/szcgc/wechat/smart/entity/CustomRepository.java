/**
 * Project:szcgcWechatServer
 * File:CustomRepository.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
public interface CustomRepository extends PagingAndSortingRepository<Custom, Integer> {

    public Custom findCustomByCustomCode(String customCode);
}
