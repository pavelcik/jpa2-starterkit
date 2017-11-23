package com.capgemini.jpa2project.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.JPaQueryDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.QProductListEntity;
import com.capgemini.jpa2project.domain.QTransactionEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class JpaQueryDaoImpl implements JPaQueryDao {

	@PersistenceContext
	private EntityManager em;
	
	QTransactionEntity transaction =QTransactionEntity.transactionEntity;
	QProductListEntity productList = QProductListEntity.productListEntity;
	
	
	@Override
	public BigDecimal calculateTransaction(Long id) {
		JPAQuery<TransactionEntity> query = new JPAQuery<>(em);
		List<BigDecimal> unitCosts = query.select((productList.product.unitPrice).multiply(productList.numberOfItems)).from(productList).where(productList.transaction.id.eq(id)).fetch();
		BigDecimal result = unitCosts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return result;
	}

	
	public List<ProductEntity> uniqueShoppingListByClient(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
