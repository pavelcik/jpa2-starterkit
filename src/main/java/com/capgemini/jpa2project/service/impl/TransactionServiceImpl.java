package com.capgemini.jpa2project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.TransactionDao;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.service.TransactionService;
import com.capgemini.jpa2project.to.TransactionTo;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao dao;
	
	@Override
	public List<TransactionEntity> findAll() {
		return dao.findAll();
	}

	@Override
	public TransactionEntity findOne(Long id) throws BusinessException {
		TransactionEntity entity = dao.findOne(id);
		if (dao.findOne(id)==null) {
			throw new BusinessException(ExceptionMessages.OBJECT_NOT_FOUND);
	} else return entity; 
	}
	

	@Override
	public void createOne(TransactionEntity entity,TransactionTo transactionTo) throws BusinessException {
		if(transactionTo.getId()!=null) {
			throw new BusinessException(ExceptionMessages.ID_SET_MANUALLY);
		}
		dao.createOne(entity,transactionTo);
	}

	@Override
	public void updateOne(TransactionEntity entity,TransactionTo transactionTo) throws BusinessException {
		if(entity.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.OBJECT_NOT_FOUND);
		}
		dao.updateOne(entity,transactionTo);
		
	}

	@Override
	public void deleteOne(TransactionTo transactionTo) throws BusinessException {
		if(transactionTo.getId()>findAll().size()) {
			throw new BusinessException(ExceptionMessages.ID_NOT_VALID);
		}	
		dao.deleteOne(transactionTo);
	}

}
