package com.capgemini.jpa2project.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.ProductEntity;

public interface JPaQueryDao {

	BigDecimal calculateTransaction(Long id);

	List<String> uniqueShoppingListByClient(Long id);

	List<ProductEntity> tenBestsellingProducts();

	List<ClientEntity> moneySpentByClient(Date from, Date to);

	List<ProductEntity> productsInRealisation();

	BigDecimal profitInMonth(Date from, Date to);

	List<ClientEntity> clientsWithProcessingStatus();

}
