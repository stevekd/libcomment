package com.bbtree.librec.core.service;

import com.bbtree.librec.core.model.Librec;
import com.bbtree.framework.page.PaginationSupport;

public interface ILibrecService {

	void save(Librec record);

	void update(Librec record);

	PaginationSupport queryPage(Librec record, PaginationSupport ps);

	Librec selectById(Integer id);


	
}
