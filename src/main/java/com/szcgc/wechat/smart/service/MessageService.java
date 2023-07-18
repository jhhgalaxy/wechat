package com.szcgc.wechat.smart.service;

import com.szcgc.wechat.smart.entity.Unsendmessage;
import com.szcgc.wechat.smart.entity.UnsendmessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.szcgc.wechat.smart.entity.Sentmessage;
import com.szcgc.wechat.smart.entity.SentmessageRepository;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @Author: chenxinli
 * @Date: 2020/4/17 18:58
 * @Description: 消息管理
 */

@Service
public class MessageService {

    @Autowired
    private SentmessageRepository sentmessageRepository;

    @Autowired
    private UnsendmessageRepository unsendmessageRepository;

    public Map<String,Object> listMsg(int receiver, int page, int size){
        Map<String,Object> map = new HashMap<>();
        Page<Sentmessage> sentmessagePage = sentmessageRepository.findByReceiver(receiver,dftPage(page,size));
        Long count = sentmessagePage.getTotalElements();
        map.put("count",count);
        map.put("data",sentmessagePage);
        return map;
    }

    public Sentmessage getSentmessageByID(int id){
        checkArgument(id > 0);
        Optional<Sentmessage> info = sentmessageRepository.findById(id);
        if (info.isPresent())
            return info.get();
        return null;

    }

    public Unsendmessage saveUnsendmessage(Unsendmessage unsendmessage){
        if(unsendmessage!=null){
            return unsendmessageRepository.save(unsendmessage);
        }
        return null;
    }

    public Iterable<Unsendmessage> saveUnsendmessages(List<Unsendmessage> unsendmessageList){
        if(unsendmessageList!=null&&unsendmessageList.size()>0){
            return unsendmessageRepository.saveAll(unsendmessageList);
        }
        return null;
    }

    public List<Unsendmessage> send(){
        List<Unsendmessage> sentList = new ArrayList<>();
        //TODO
        //查询所有待发列表
        Iterable<Unsendmessage> all = unsendmessageRepository.findAll();
        //分类发送所有信息
        while(all.iterator().hasNext()){
            Unsendmessage next = all.iterator().next();

        }
        //返回已发消息列表

        return sentList;
    }

    public void transferUnsendToSent(List<Unsendmessage> list){
        //将已发送信息插入已发消息表中
        List<Sentmessage> sentList = new ArrayList<>();
        for(Unsendmessage unsendmessage:list){
            Sentmessage sentmessage = new Sentmessage();
            sentmessage.setReceiver(unsendmessage.getReceiver());
            sentmessage.setMsgtitle(unsendmessage.getMsgtitle());
            sentmessage.setMsgcontent(unsendmessage.getMsgcontent());
            sentmessage.setMsgtype(unsendmessage.getMsgtype());
            sentmessage.setLinkitem(unsendmessage.getLinkitem());
            sentmessage.setProject(unsendmessage.getProject());
            sentList.add(sentmessage);
        }
        sentmessageRepository.saveAll(sentList);
        //删除待发消息表数据
        unsendmessageRepository.deleteAll(list);
    }

    private static final Sort DFTSORT = Sort.by(Sort.Direction.DESC, "id");

    private Pageable dftPage(int page, int size) {
        checkArgument(page >= 0);
        checkArgument(size > 0);
        return PageRequest.of(page, size, DFTSORT);
    }
}
