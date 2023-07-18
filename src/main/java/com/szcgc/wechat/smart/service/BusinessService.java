package com.szcgc.wechat.smart.service;

import com.szcgc.wechat.smart.entity.Projectapply;
import com.szcgc.wechat.smart.entity.ProjectapplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: chenxinli
 * @Date: 2020/6/30 17:45
 * @Description:
 */

@Service
public class BusinessService {

    @Autowired
    private ProjectapplyRepository projectapplyRepository;

    public Projectapply addProjectApply(Projectapply projectapply){
        if(projectapply!=null){
            return projectapplyRepository.save(projectapply);
        }else{
            return null;
        }
    }
}
