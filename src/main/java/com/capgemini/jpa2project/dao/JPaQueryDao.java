package com.capgemini.jpa2project.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.ProductEntity;

public interface JPaQueryDao {

	public BigDecimal calculateTransaction(Long id);
	public List<String> uniqueShoppingListByClient(Long id);
	List<ProductEntity> tenBestsellingProducts();
	public List<ClientEntity> moneySpentByClient(Date from,Date to);
	List<ProductEntity> productsInRealisation();
	
	
}
