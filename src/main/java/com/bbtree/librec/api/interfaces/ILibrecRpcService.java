package com.bbtree.librec.api.interfaces;

import com.bbtree.librec.api.vo.LibrecVo;
import com.bbtree.framework.page.PaginationSupport;

public interface ILibrecRpcService {

	String sayHello(String name);

	PaginationSupport queryPage(LibrecVo vo, PaginationSupport ps);

	LibrecVo queryById(Integer id);
	
	
	
}

