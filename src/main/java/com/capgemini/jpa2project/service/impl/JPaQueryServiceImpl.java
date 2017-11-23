package com.capgemini.jpa2project.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.JPaQueryDao;
import com.capgemini.jpa2project.domain.ProductEntity;
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
	
	public List<String> uniqueShoppingListByClient(Long id) {
		return dao.uniqueShoppingListByClient(id);
	}

	@Override
	public List<ProductEntity> tenBestsellingProducts() {
		return dao.tenBestsellingProducts();
	}

}
