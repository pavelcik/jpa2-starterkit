package com.capgemini.jpa2project.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.JPaQueryDao;
import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.domain.QClientEntity;
import com.capgemini.jpa2project.domain.QProductEntity;
import com.capgemini.jpa2project.domain.QProductListEntity;
import com.capgemini.jpa2project.domain.QTransactionEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class JpaQueryDaoImpl implements JPaQueryDao {

	@PersistenceContext
	private EntityManager em;
	
	QTransactionEntity transactionEntity =QTransactionEntity.transactionEntity;
	QProductListEntity productListEntity = QProductListEntity.productListEntity;
	QClientEntity clientEntity = QClientEntity.clientEntity;
	QProductEntity productEntity = QProductEntity.productEntity;
	
	@Override
	public BigDecimal calculateTransaction(Long id) {
		JPAQuery<ProductListEntity> query = new JPAQuery<>(em);
		List<BigDecimal> unitCosts = query.select((productListEntity.product.unitPrice).multiply(productListEntity.numberOfItems)).from(productListEntity).where(productListEntity.transaction.id.eq(id)).fetch();
		BigDecimal result = unitCosts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
		return result;
	}
	@Override
	public List<String> uniqueShoppingListByClient(Long id) {
		JPAQuery<ProductEntity> query = new JPAQuery<>(em);
//		List<ProductEntity> list = query.select(productListEntity.product).from(transaction).fetch();
	//	System.out.println(list.get(0).getProductName());
		List<String> result = query.select(productListEntity.transaction.client).from(productListEntity).where(productListEntity.transaction.client.id.eq(1L)).select(productListEntity.product.productName).distinct().fetch();
		return result;
	}
	@Override
	public List<ProductEntity> tenBestsellingProducts() {
		JPAQuery<ProductEntity> query = new JPAQuery<>(em);
		List<ProductEntity> top10 = query.select(productListEntity.product).from(productListEntity).groupBy(productListEntity.product)
			.orderBy(productListEntity.numberOfItems.desc()).limit(10L).fetch();
		return top10;
			
	}

	
	
}
