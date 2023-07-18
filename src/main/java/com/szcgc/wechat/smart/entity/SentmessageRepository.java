/**
 * Project:szcgcWechatServer
 * File:SentmessageRepository.java
 * Date:2020年4月8日
 * Author:chenxinli
 * Description:
 */
package com.szcgc.wechat.smart.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author chenxinli
 * @date 2020年4月8日
 * 
 */
public interface SentmessageRepository extends PagingAndSortingRepository<Sentmessage, Integer> {

    public Page<Sentmessage> findByReceiver(int receiver, Pageable pageable);
}
