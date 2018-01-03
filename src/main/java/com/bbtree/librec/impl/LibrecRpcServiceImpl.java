package com.bbtree.librec.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.bbtree.librec.api.interfaces.ILibrecRpcService;
import com.bbtree.librec.api.vo.LibrecVo;
import com.bbtree.librec.core.model.Librec;
import com.bbtree.librec.core.service.ILibrecService;
import com.bbtree.framework.page.PaginationSupport;

@Service
public class LibrecRpcServiceImpl implements ILibrecRpcService {

	@Autowired
	private ILibrecService LibrecService;
	
	@Override
	public String sayHello(String name){
		return name+",welcome to rpc service.";
	}

	@Override
	public PaginationSupport queryPage(LibrecVo record, PaginationSupport ps) {
		Librec qa = new Librec();
		BeanUtils.copyProperties(record, qa);
		LibrecService.queryPage(qa, ps);
		if (ps.getItems() != null && !ps.getItems().isEmpty()) {
			List<LibrecVo> res = new ArrayList<LibrecVo>();
			for (Object obj : ps.getItems()) {
				Librec tempQa = (Librec) obj;
				LibrecVo tempVo = new LibrecVo();
				BeanUtils.copyProperties(tempQa, tempVo);
				res.add(tempVo);
			}
			ps.setItems(res);
		}
		return ps;

	}

	@Override
	public LibrecVo queryById(Integer id) {
		LibrecVo res = new LibrecVo();
		Librec record = LibrecService.selectById(id);
		if (record != null)
			BeanUtils.copyProperties(record, res);
		return res;
	}

}
