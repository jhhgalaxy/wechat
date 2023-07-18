package com.szcgc.assets.repository;

import com.szcgc.assets.entity.AssetInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author JINLINGXUAN
 * @create 2021-06-07
 */

public interface AssetInfoRepository extends PagingAndSortingRepository<AssetInfo, Integer> {

    AssetInfo findByAssetCode(String code);

}
