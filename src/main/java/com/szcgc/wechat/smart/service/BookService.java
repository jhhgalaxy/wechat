package com.szcgc.wechat.smart.service;

import com.szcgc.wechat.smart.entity.BookPurchase;
import com.szcgc.wechat.smart.entity.BookPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenxinli
 * @Date: 2020/7/31 15:24
 * @Description:
 */
@Service
public class BookService {

    @Autowired
    private BookPurchaseRepository bookPurchaseRepository;

    public BookPurchase insert(BookPurchase entity) {
        return bookPurchaseRepository.save(entity);
    }

    public List<BookPurchase> findUnPushList(){
        return bookPurchaseRepository.findBookPurchasesByPush((byte) 0);
    }

    public Iterable<BookPurchase> setListPushed(List<BookPurchase> list){
        for(BookPurchase bookPurchase : list){
            bookPurchase.setPush((byte) 1);
        }
        return bookPurchaseRepository.saveAll(list);
    }
}
