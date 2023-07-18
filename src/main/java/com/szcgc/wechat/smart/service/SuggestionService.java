package com.szcgc.wechat.smart.service;

import com.szcgc.wechat.smart.entity.Suggestion;
import com.szcgc.wechat.smart.entity.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @Author: chenxinli
 * @Date: 2020/4/21 17:01
 * @Description:
 */

@Service
public class SuggestionService {

    @Autowired
    private SuggestionRepository repository;

    public Suggestion saveSuggestion(Suggestion suggestion){
        if(suggestion!=null){
            return repository.save(suggestion);
        }
        return null;
    }

    public List<Suggestion> getComplaintList(int userid){
        return repository.getSuggestionsByComplainant(userid);
    }

    public Suggestion find(int id){
        checkArgument(id > 0);
        Optional<Suggestion> info = repository.findById(id);
        if (info.isPresent())
            return info.get();
        return null;
    }
}
