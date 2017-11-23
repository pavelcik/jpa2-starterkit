package com.capgemini.jpa2project.dao;

import java.util.List;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.to.ProductTo;

public interface ProductEntityDao {

	ProductEntity findOne(Long id);

	void createOne(ProductEntity entity,ProductTo productTo);

	void updateOne(ProductEntity entity,ProductTo productTo);
	
	public void deleteOne(ProductTo productTo);

	List<ProductEntity> findAll();
}
