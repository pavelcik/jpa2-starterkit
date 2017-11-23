package com.capgemini.jpa2project.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.JPaQueryDao;
import com.capgemini.jpa2project.service.JPaQueryService;
@Transactional
@Service
public class JPaQueryServiceImpl implements JPaQueryService{

	@Autowired
	private JPaQueryDao dao;
	@Override
	public BigDecimal calculateTransaction(Long id) {
		return dao.calculateTransaction(id);
	}

}
