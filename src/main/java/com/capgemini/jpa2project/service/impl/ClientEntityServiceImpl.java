package com.capgemini.jpa2project.service.impl;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jpa2project.dao.ClientEntityDao;
import com.capgemini.jpa2project.domain.ClientEntity;

@Service
@Transactional
public class ClientEntityServiceImpl implements ClientEntityService {

	@Autowired
	private ClientEntityDao clientDao;
	

	@Override
	public void save(ClientEntity entity) {
		clientDao.save(entity);
	}
	
}
