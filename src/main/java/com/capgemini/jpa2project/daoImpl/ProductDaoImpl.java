package com.capgemini.jpa2project.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.ProductEntityDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.QProductEntity;
import com.capgemini.jpa2project.mapper.ProductListMapper;
import com.capgemini.jpa2project.mapper.ProductMapper;
import com.capgemini.jpa2project.to.ProductTo;
import com.querydsl.jpa.impl.JPAQuery;


@Repository
public class ProductDaoImpl implements ProductEntityDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ProductMapper mapper;
	@Autowired
	private ProductListMapper productListMapper;
	
	public ProductEntity toEntity(ProductTo productTo) {
		return mapper.map(productTo);
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
	public void createOne(ProductTo productTo) {
		ProductEntity entity = toEntity(productTo);
		em.persist(entity);
	}
	
	@Override
	public void updateOne(ProductTo productTo) {
		ProductEntity entity = mapper.map(productTo); 
		entity = findOne(entity.getId());
		em.merge(entity);
	}
	
	@Override
	public void deleteOne(ProductTo productTo) {
		em.remove(productTo);
	}

}
