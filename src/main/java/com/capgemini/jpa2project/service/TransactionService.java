package com.capgemini.jpa2project.service;

import java.util.List;

import com.capgemini.jpa2project.domain.QTransactionEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.to.TransactionTo;
import com.querydsl.jpa.impl.JPAQuery;

public interface TransactionService {

	
	public List<TransactionEntity> findAll();
	
	public TransactionEntity findOne(Long id) throws BusinessException;
	
	public void createOne(TransactionEntity entity,TransactionTo transactionTo) throws BusinessException;
	
	public void updateOne(TransactionEntity entity,TransactionTo transactionTo) throws BusinessException;
	
	public void deleteOne(TransactionTo transactionTo) throws BusinessException;
}
