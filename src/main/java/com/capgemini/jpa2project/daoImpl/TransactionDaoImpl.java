package com.capgemini.jpa2project.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.TransactionDao;
import com.capgemini.jpa2project.domain.QTransactionEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.mapper.TransactionMapper;
import com.capgemini.jpa2project.to.TransactionTo;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionMapper mapper;

	public TransactionEntity toEntity(TransactionEntity entity, TransactionTo to) {
		return mapper.map(entity, to);
	}

	@Override
	public List<TransactionEntity> findAll() {
		QTransactionEntity transaction = QTransactionEntity.transactionEntity;
		JPAQuery<TransactionEntity> query = new JPAQuery<>(em);
		List<TransactionEntity> result = query.select(transaction).from(transaction).fetch();
		return result;
	}

	@Override
	public TransactionEntity findOne(Long id) {
		return em.find(TransactionEntity.class, id);
	}

	@Override
	public void createOne(TransactionEntity entity, TransactionTo transactionTo) {
		TransactionEntity newEntity = toEntity(entity, transactionTo);
		em.persist(newEntity);
	}

	@Override
	public void updateOne(TransactionEntity entity, TransactionTo transactionTo) {
		TransactionEntity newEntity = toEntity(entity, transactionTo);
		em.merge(newEntity);
	}

	@Override
	public void deleteOne(TransactionTo transactionTo) {
		TransactionEntity entity = em.find(TransactionEntity.class, transactionTo.getId());
		em.remove(entity);
	}
}
