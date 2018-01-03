package com.bbtree.core.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



public class CoreSearch {
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreSearch.class);

//	@Autowired
//	private ISolrSearcher<CoreModel> iSolrSearcher;
	
	public String query() {
		long count = 0;
		/**
		String collection = "core";
		try {
			// 搜索 
			Results<CoreModel> res = iSolrSearcher.searchDocsInfo(new String[] {}, new String[] {}, 0, 10,
					new String[] {}, new Boolean[] {}, CoreModel.class,collection);
			count = res.getCount();
			
			List<CoreModel> docs = res.getDocs();
		
		}	catch (Exception e) {
			LOGGER.error("",e);
			return "fail";
		}
		**/
		return "SUCCESS:" + count;
	}


}
