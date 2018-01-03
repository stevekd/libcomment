package com.bbtree.core.demo.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bbtree.mongo.MongoFactory;
import com.bbtree.mongo.base.interfaces.IMongoClient;
import com.bbtree.paas.auth.vo.AuthDescriptor;


public class CoreMongo {

    // 此处为演示 biz_key
    // 项目key的获取方式请参考 http://confluence.bbtree.com/pages/viewpage.action?pageId=3408630
    static final String BIZ_KEY = "biz_misc-mq-8230b0fdbe4241aea6fa8bc3a6daedd6";
    // CICD、线上环境 http://javaport.bbtree.com/paas/auth/service
    static final String AUTH_URL = "http://114.215.202.56:8081/paas/auth/service";
    static AuthDescriptor ad;

    private IMongoClient getMongo() throws Exception{
    		if(ad == null){
	    	    ad = new AuthDescriptor();
			ad.setServiceId(BIZ_KEY);
			ad.setAuthAdress(AUTH_URL);
		}
		return MongoFactory.getClient(ad);
    }
    public String insert(Map<String,String> con) throws Exception{
    		/**Map<String,String> con = new HashMap<>();
		con.put("name", "zs");
		con.put("code", "zs");
		con.put("title", "tt1");
		con.put("con", "haha");*/
    		String id = getMongo().insert(con);
    		return id;
    }

    public String find(Map<String,String> con) throws Exception{
    			/**Map<String,String> con = new HashMap<>();
			con.put("title", "tt1");*/
    			String content = getMongo().find(JSON.toJSONString(con));
			System.out.println(content);
			return content;
    }
   

}
