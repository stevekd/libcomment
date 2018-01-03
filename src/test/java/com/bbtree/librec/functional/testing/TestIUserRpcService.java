package com.bbtree.librec.functional.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.annotation.Reference;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/dubbo/consumer/consumer.xml" })
public class TestIUserRpcService {

	//@Reference
	//private IUserRpcService userRpcService;  //need import
	
	@Test
	public void invokeOther() {
		//System.out.println(userRpcService.other());
	}


}
