package com.capgemini.jpa2project.service;

import java.util.List;

import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.domain.QProductListEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.to.ProductListTo;
import com.querydsl.jpa.impl.JPAQuery;

public interface ProductListEntityService {
	
	public List<ProductListTo> findAll();
	
	public List<ProductListEntity> findAllForProductId(Long id);
	
	
	public List<ProductListEntity> findAllForTransactionId(Long id);
	
	
	public ProductListEntity findOne(Long id) throws BusinessException;
	
	
	public void createOne(ProductListEntity entity,ProductListTo productListTo) throws BusinessException;
	
	
	
	public void updateOne(ProductListEntity entity,ProductListTo productListTo) throws BusinessException;
	
	
	
	public void deleteOne(ProductListTo productListTo) throws BusinessException;
}
