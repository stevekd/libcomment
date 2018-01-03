package com.bbtree.librec.functional.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bbtree.librec.api.interfaces.ILibrecRpcService;
import com.bbtree.librec.api.vo.LibrecVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/dubbo/consumer/consumer.xml" })
public class TestILibrecRpcService {
	@Reference
	private ILibrecRpcService LibrecService;
	
	@Test
	public void sayHello() {
		//System.out.println(LibrecService.sayHello("com.bbtree.librec"));
	}
	
	@Test
	public void query() {
	//	LibrecVo res =LibrecService.queryById(140);
	//	System.out.println(res);
	}

}
