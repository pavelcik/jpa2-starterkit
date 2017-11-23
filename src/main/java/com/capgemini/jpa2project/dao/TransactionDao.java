
package com.capgemini.jpa2project.dao;

import java.util.List;

import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.to.TransactionTo;

public interface TransactionDao {

	List<TransactionEntity> findAll();

	TransactionEntity findOne(Long id);

	void createOne(TransactionEntity entity,TransactionTo transactionTo);

	void updateOne(TransactionEntity entity,TransactionTo transactionTo);

	void deleteOne(TransactionTo transactionTo);

}
