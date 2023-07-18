package com.szcgc.wechat.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Auther chenxinli
 * @Date 2020.4.15
 * 缓存类工具
 */
public class CacheUtil {
    private static Cache<String, String> cacheCode = CacheBuilder.newBuilder().maximumSize(500).expireAfterAccess(10, TimeUnit.MINUTES).build();

    public static void putObject(String key,String value){
        cacheCode.put(key,value);
    }

    public static String getObject(String key){
        return cacheCode.getIfPresent(key);
    }
}
