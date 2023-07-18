package com.szcgc.assets.service;

import com.szcgc.assets.entity.AssetInfo;
import com.szcgc.assets.repository.AssetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author JINLINGXUAN
 * @create 2021-06-07
 */
@Service
public class AssetService {

    @Autowired
    private AssetInfoRepository repository;

    public Optional<AssetInfo> find(Integer integer){
        return repository.findById(integer);
    }

    public AssetInfo findByCode(String code){
        return repository.findByAssetCode(code);
    }

    public AssetInfo insert(AssetInfo info){
        return repository.save(info);
    }

    public AssetInfo update(AssetInfo info){
        return repository.save(info);
    }

}
