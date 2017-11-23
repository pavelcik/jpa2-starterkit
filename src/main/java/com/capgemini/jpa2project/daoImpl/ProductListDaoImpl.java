package com.capgemini.jpa2project.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.ProductListDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.domain.QProductEntity;
import com.capgemini.jpa2project.domain.QProductListEntity;
import com.capgemini.jpa2project.mapper.ProductListMapper;
import com.capgemini.jpa2project.mapper.ProductMapper;
import com.capgemini.jpa2project.to.ProductListTo;
import com.capgemini.jpa2project.to.ProductTo;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class ProductListDaoImpl implements ProductListDao {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ProductListMapper mapper;
	
	public ProductListEntity toEntity(ProductListEntity entity,ProductListTo productListTo) {
		return mapper.map(entity,productListTo);
	}
	
	public ProductListTo toTo(ProductListEntity entity) {
		return mapper.map(entity);
	}
	@Override
	public List<ProductListTo> findAll() {
		QProductListEntity product = QProductListEntity.productListEntity;
		JPAQuery<ProductListEntity> query = new JPAQuery<>(em);
		List<ProductListEntity> result = query.select(product).from(product).fetch();
		return mapper.map2To(result);
	}
	@Override
	public List<ProductListEntity> findAllForProductId(Long id) {
		QProductListEntity product = QProductListEntity.productListEntity;
		JPAQuery<ProductListEntity> query = new JPAQuery<>(em);
		List<ProductListEntity> result = query.select(product).from(product).where(product.product.id.eq(id)).select(product).fetch();
		return result;
	}
	
	@Override
	public List<ProductListEntity> findAllForTransactionId(Long id) {
		QProductListEntity product = QProductListEntity.productListEntity;
		JPAQuery<ProductListEntity> query = new JPAQuery<>(em);
		List<ProductListEntity> result = query.select(product).from(product).where(product.product.id.eq(id)).select(product).fetch();
		return result;
	}
	
	@Override
	public ProductListEntity findOne(Long id) {
		return em.find(ProductListEntity.class, id);
	}
	
	@Override
	public void createOne(ProductListEntity entity,ProductListTo productListTo) {
		ProductListEntity productListEntity = toEntity(entity,productListTo);
		em.persist(productListEntity);
		em.flush();
	}
	
	
	@Override
	public void updateOne(ProductListEntity entity,ProductListTo productListTo) {
		ProductListEntity productListEntity = toEntity(entity,productListTo);
		em.merge(productListEntity);
	}
	
	
	@Override
	public void deleteOne(ProductListTo productListTo) {
		ProductListEntity productListEntity= em.find(ProductListEntity.class, productListTo.getId());
		em.remove(productListEntity);
	}
}