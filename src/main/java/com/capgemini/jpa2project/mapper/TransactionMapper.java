package com.capgemini.jpa2project.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.dao.ProductListDao;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.to.TransactionTo;
@Component
public class TransactionMapper {
	


	public TransactionTo map(TransactionEntity transactionEntity) {
		TransactionTo transactionTo = null;
		
		if(transactionEntity !=null) {
			transactionTo=TransactionTo.builder().client(transactionEntity.getClient()).id(transactionEntity.getId())
					.Version(transactionEntity.getVersion()).orderDate(transactionEntity.getOrderDate())
					.productList(transactionEntity.getProductList()).status(transactionEntity.getStatus()).build();
		}
		return transactionTo;
	}
	
	public TransactionEntity map(TransactionEntity trasactionEntity,TransactionTo transactionTo) {
		
		if(trasactionEntity !=null) {

			trasactionEntity.setOrderDate(transactionTo.getOrderDate());
			trasactionEntity.setStatus(transactionTo.getStatus());
			return trasactionEntity;
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
