package com.bbtree.librec.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbtree.librec.core.dao.LibrecMapper;
import com.bbtree.librec.core.model.Librec;
import com.bbtree.librec.core.service.ILibrecService;
import com.bbtree.framework.page.PaginationSupport;

@Service
public class LibrecServiceImpl implements ILibrecService {

	@Autowired
	private LibrecMapper LibrecMapper;

	@Override
	@Transactional(readOnly=false)
	public void save(Librec record) {
		LibrecMapper.insert(record);
	}

	@Override
	@Transactional(readOnly=true)
	public PaginationSupport queryPage(Librec record, PaginationSupport ps) {
		List<Librec> suppliers = LibrecMapper.queryPage(ps, record);
		ps.setItems(suppliers);
		return ps;
	}

	@Override
	@Transactional(readOnly=true)
	public Librec selectById(Integer id) {
		return LibrecMapper.selectById(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void update(Librec record) {
		LibrecMapper.updateById(record);
	}
	

}
