package com.bbtree.core.demo.service;

import com.bbtree.cache.CacheClientFactory;
import com.bbtree.cache.ICacheClient;

public class CoreCache {

    // 此处为演示 biz_key
    // 项目key的获取方式请参考 http://confluence.bbtree.com/pages/viewpage.action?pageId=3408630
    static final String BIZ_KEY = "biz_misc-mq-8230b0fdbe4241aea6fa8bc3a6daedd6";
    // CICD、线上环境 http://javaport.bbtree.com/paas/auth/service
    static final String AUTH_URL = "http://114.215.202.56:8081/paas/auth/service";

    public String get(String key){
        ICacheClient cc = CacheClientFactory.getClient(BIZ_KEY, AUTH_URL);
        String val = cc.get(key);
        return val;
    }

    public boolean set(String key, int seconds,String value){
        ICacheClient cc = CacheClientFactory.getClient(BIZ_KEY, AUTH_URL);
        String val = cc.setex(key,seconds,value);
        return "OK".equals(val);
    }
    
    public Object getObject(byte[] key){
        ICacheClient cc = CacheClientFactory.getClient(BIZ_KEY, AUTH_URL);
        Object val = cc.getObject(key);
        return val;
    }

    public boolean setObject(byte[] key, int seconds,Object serializable){
        ICacheClient cc = CacheClientFactory.getClient(BIZ_KEY, AUTH_URL);
        String val = cc.setObjectEx(key,seconds,serializable);
        return "OK".equals(val);
    }

}
