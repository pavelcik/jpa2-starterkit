package com.capgemini.jpa2project.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.jpa2project.dao.ClientEntityDao;
import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.QClientEntity;
import com.capgemini.jpa2project.to.ProductTo;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class ClientEntityDaoImpl implements ClientEntityDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public ClientEntity findOne(Long id) {
		return em.find(ClientEntity.class, id);
	}

	@Override
	public void createOne(ProductTo productTo) {
		em.persist(productTo);
	}

	@Override
	public void updateOne(ProductTo productTo) {
		em.merge(productTo);
	}

	@Override
	public void deleteOne(ProductTo productTo) {
		em.remove(productTo);
	}

	@Override
	public List<ClientEntity> findAll() {
		QClientEntity clientEntity = QClientEntity.clientEntity;
		JPAQuery<ClientEntity> query = new JPAQuery<>(em);
		List<ClientEntity> result = query.select(clientEntity).from(clientEntity).fetch();
		return result;
	}

}
