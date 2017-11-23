package com.capgemini.jpa2project.dao;

import java.util.List;

import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.to.ProductListTo;

public interface ProductListDao {
	List<ProductListTo> findAll();

	ProductListEntity findOne(Long id);

	void createOne(ProductListEntity entity,ProductListTo productListTo);

	void updateOne(ProductListEntity entity,ProductListTo productListTo);

	void deleteOne(ProductListTo productListTo);

	List<ProductListEntity> findAllForProductId(Long id);

	List<ProductListEntity> findAllForTransactionId(Long id);
}
