package com.szcgc.wechat.smart.entity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @Author: chenxinli
 * @Date: 2020/7/31 15:24
 * @Description:
 */
public interface BookPurchaseRepository extends PagingAndSortingRepository<BookPurchase,Integer> {
    public List<BookPurchase> findBookPurchasesByPush(byte b);
}
