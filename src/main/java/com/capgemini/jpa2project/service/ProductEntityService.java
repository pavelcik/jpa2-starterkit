package com.capgemini.jpa2project.service;

import java.util.List;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.to.ProductTo;

public interface ProductEntityService {
	
	public ProductEntity findOne(Long id) throws BusinessException;
	
	public void createOne(ProductTo productTo);
	
	public void updateOne(ProductTo productTo);
	
	public void deleteOne(ProductTo productTo);
	
	public List<ProductEntity> findAll();
	
}
