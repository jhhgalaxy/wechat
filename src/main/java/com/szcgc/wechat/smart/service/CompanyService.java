package com.szcgc.wechat.smart.service;

import com.szcgc.user.service.UserService;
import com.szcgc.wechat.smart.entity.Custom;
import com.szcgc.wechat.smart.entity.CustomRepository;
import com.szcgc.wechat.smart.entity.Keycontactor;
import com.szcgc.wechat.smart.entity.KeycontactorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private KeycontactorRepository keycontactorRepository;

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private UserService userService;

    public Custom findCustom(String id) {
        checkArgument(!id.isEmpty());
        Custom info = customRepository.findCustomByCustomCode(id);
        if (info!=null)
            return info;
        return null;
    }

    public void removeAllCustom(){
        customRepository.deleteAll();
    }

    public Iterable saveAllCustom(List<Custom> customs){
        return customRepository.saveAll(customs);
    }

    public List<Custom> getCustomsByCellphone(String cellphone){
        List<Custom> customList = new ArrayList<>();
        List<Keycontactor> contactorlist = keycontactorRepository.findDistinctCustomidByCellphone(cellphone);
        for(Keycontactor keycontactor : contactorlist){
            Custom custom = findCustom(keycontactor.getCustomid());
            customList.add(custom);
        }
        return customList;
    }

    public List<String> getCellPhonesByCustomcode(String customCode){
        List<String> cellPhonelist = new ArrayList<>();
        List<Keycontactor> contactorlist = keycontactorRepository.findDistinctCellphoneByCustomid(customCode);
        for(Keycontactor keycontactor : contactorlist){
            String cellphone = keycontactor.getCellphone();
            cellPhonelist.add(cellphone);
        }
        return cellPhonelist;
    }


}
