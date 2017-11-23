package com.capgemini.jpa2project.dao;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.jpa2project.domain.ProductEntity;

public interface JPaQueryDao {

	public BigDecimal calculateTransaction(Long id);
	public List<String> uniqueShoppingListByClient(Long id);
	List<ProductEntity> tenBestsellingProducts();
	
	
}
