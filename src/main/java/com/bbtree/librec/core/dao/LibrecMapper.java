package com.bbtree.librec.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bbtree.librec.core.model.Librec;
import com.bbtree.framework.page.PaginationSupport;

public interface LibrecMapper {
	
	int insert(Librec record);
	
	int deleteById(Integer id);

	int updateById(Librec record);

	Librec selectById(Integer id);

	List<Librec> queryPage(
			@Param("pageBean") PaginationSupport ps,
			@Param("vo") Librec record);
	
	Map getAnswers(Librec record);

}