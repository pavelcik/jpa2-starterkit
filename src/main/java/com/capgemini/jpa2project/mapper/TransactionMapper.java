package com.capgemini.jpa2project.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.dao.ClientEntityDao;
import com.capgemini.jpa2project.dao.ProductListDao;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.to.TransactionTo;
@Component
public class TransactionMapper {
	
	@Autowired
	private ClientEntityDao dao;
	@Autowired
	private ProductListDao productListDao;

	public TransactionTo map(TransactionEntity transactionEntity) {
		TransactionTo transactionTo = null;
		
		if(transactionEntity !=null) {
			transactionTo=TransactionTo.builder().client(transactionEntity.getClient()).id(transactionEntity.getId())
					.Version(transactionEntity.getVersion()).orderDate(transactionEntity.getOrderDate())
					.productList(transactionEntity.getProductList()).status(transactionEntity.getStatus()).build();
		}
		return transactionTo;
	}
	
	public TransactionEntity map(TransactionEntity transactionEntity,TransactionTo transactionTo) {
		
		if(transactionEntity !=null&&transactionEntity.getId()!=null) {

			transactionEntity.setOrderDate(transactionTo.getOrderDate());
			transactionEntity.setStatus(transactionTo.getStatus());
			transactionEntity.setClient(dao.findAllForTransactionId(transactionEntity.getId()));
			transactionEntity.setProductList(productListDao.findAllForTransactionId(transactionEntity.getId()));
			return transactionEntity;
	}
		else {
			TransactionEntity transactionEntity2 = new TransactionEntity();
			transactionEntity2.setOrderDate(transactionTo.getOrderDate());
			transactionEntity2.setStatus(transactionTo.getStatus());
			transactionEntity2.setClient(transactionTo.getClient());
			transactionEntity2.setProductList(transactionTo.getProductList());
			transactionEntity2.setVersion(transactionTo.getVersion());
			return transactionEntity2;
		}
	
	}
}
