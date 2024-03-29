package com.capgemini.jpa2project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.ProductEntityDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.service.ProductEntityService;
import com.capgemini.jpa2project.to.ProductTo;
@Service
@Transactional
public class ProductEntityServiceImpl implements ProductEntityService {

	@Autowired
	private ProductEntityDao dao;
	
	
	
	@Override
	public ProductEntity findOne(Long id) throws BusinessException {
		ProductEntity entity = dao.findOne(id);
		if (dao.findOne(id)==null) {
			throw new BusinessException(ExceptionMessages.OBJECT_NOT_FOUND);
		} else return entity;
	}
	
	@Override
	public void createOne(ProductEntity entity,ProductTo productTo) throws BusinessException {
		if(productTo.getId()!=null) {
			throw new BusinessException(ExceptionMessages.ID_SET_MANUALLY);
		}
		else 
		dao.createOne(entity,productTo);
	}
	
	@Override
	public void updateOne(ProductEntity entity,ProductTo productTo) throws BusinessException {
		if(entity.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.ID_NOT_VALID); 
		} else
		dao.updateOne(entity,productTo);
	}
	
	@Override
	public void deleteOne(ProductTo productTo) throws BusinessException {
		if(productTo.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.ID_NOT_VALID);
		} else dao.deleteOne(productTo);
	}
	@Override
	public List<ProductEntity> findAll() {
		return dao.findAll();
	}
}
