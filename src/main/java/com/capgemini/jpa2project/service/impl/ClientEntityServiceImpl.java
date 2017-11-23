package com.capgemini.jpa2project.service.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.ClientEntityDao;
import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.service.ClientEntityService;
import com.capgemini.jpa2project.to.ProductTo;

@Service
@Transactional
public class ClientEntityServiceImpl implements ClientEntityService {

	@Autowired
	private ClientEntityDao clientDao;

	@Override
	public ClientEntity findOne(Long id) {
		return clientDao.findOne(id);
	}

	@Override
	public void createOne(ProductTo productTo) {
		clientDao.createOne(productTo);
		
	}

	@Override
	public void updateOne(ProductTo productTo) {
		clientDao.updateOne(productTo);
	}

	@Override
	public void deleteOne(ProductTo productTo) {
	clientDao.deleteOne(productTo);
	}

	@Override
	public List<ClientEntity> findAll() {
		return clientDao.findAll();
	}

	
	
	
}
