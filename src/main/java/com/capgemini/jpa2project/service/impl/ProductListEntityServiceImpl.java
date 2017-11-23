package com.capgemini.jpa2project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.ProductListDao;
import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.service.ProductListEntityService;
import com.capgemini.jpa2project.to.ProductListTo;

@Service
@Transactional
public class ProductListEntityServiceImpl implements ProductListEntityService {
	@Autowired
	private ProductListDao dao;
	@Override
	public List<ProductListTo> findAll() {
		return dao.findAll();
	}

	@Override
	public List<ProductListEntity> findAllForProductId(Long id) {
		return dao.findAllForProductId(id);
	}

	@Override
	public List<ProductListEntity> findAllForTransactionId(Long id) {
		return dao.findAllForTransactionId(id);
	}

	@Override
	public ProductListEntity findOne(Long id) throws BusinessException {
		ProductListEntity entity = dao.findOne(id);
		if (dao.findOne(id)==null) {
			throw new BusinessException(ExceptionMessages.OBJECT_NOT_FOUND);
	} else return entity; 
	}

	@Override
	public void createOne(ProductListEntity entity,ProductListTo productListTo) throws BusinessException {
	if(productListTo.getId()!=null) {
		throw new BusinessException(ExceptionMessages.ID_SET_MANUALLY);
	}
	else dao.createOne(entity,productListTo);
	}

	@Override
	public void updateOne(ProductListEntity entity,ProductListTo productListTo) throws BusinessException {
		if(entity.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.OBJECT_NOT_FOUND);
		} else
		dao.updateOne(entity,productListTo);
		
	}

	@Override
	public void deleteOne(ProductListTo productListTo) throws BusinessException {
		if(productListTo.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.ID_NOT_VALID);
		} else
		dao.deleteOne(productListTo);
		
	}

}
