package com.capgemini.jpa2project.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.ProductEntityDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.QProductEntity;
import com.capgemini.jpa2project.mapper.ProductMapper;
import com.capgemini.jpa2project.to.ProductTo;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class ProductDaoImpl implements ProductEntityDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ProductMapper mapper;

	public ProductEntity toEntity(ProductEntity productEntity, ProductTo productTo) {
		return mapper.map(productEntity, productTo);
	}

	public ProductTo toTo(ProductEntity entity) {
		return mapper.map(entity);
	}

	@Override
	public List<ProductEntity> findAll() {
		QProductEntity product = QProductEntity.productEntity;
		JPAQuery<ProductEntity> query = new JPAQuery<>(em);
		List<ProductEntity> result = query.select(product).from(product).fetch();
		return result;
	}

	@Override
	public ProductEntity findOne(Long id) {
		return em.find(ProductEntity.class, id);
	}

	@Override
	public void createOne(ProductEntity entity, ProductTo productTo) {
		ProductEntity productEntity = toEntity(entity, productTo);
		em.persist(productEntity);
		em.flush();
	}

	@Override
	public void updateOne(ProductEntity entity, ProductTo productTo) {
		ProductEntity productEntity = toEntity(entity, productTo);
		em.merge(productEntity);
	}

	@Override
	public void deleteOne(ProductTo productTo) {
		ProductEntity productEntity = em.find(ProductEntity.class, productTo.getId());
		em.remove(productEntity);
	}

}
