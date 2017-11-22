package com.capgemini.jpa2project.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.to.ProductTo;

@Repository
public class ClientEntityDaoImpl {

//	
//	@PersistenceContext
//	private EntityManager em;
//	
//	@Override
//	public ClientEntity findOne(Long id) {
//		return em.find(ClientEntity.class, id);
//	}
//	@Override
//	public void createOne(ProductTo productTo) {
//		em.persist(productTo);
//	}
//	
//	@Override
//	public void updateOne(ProductTo productTo) {
//		em.merge(productTo);
//	}
//	
//	@Override
//	public void deleteOne(ProductTo productTo) {
//		em.remove(productTo);
//	}
//	
	
}
