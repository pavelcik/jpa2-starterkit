package com.capgemini.jpa2project.service;

import java.util.List;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.to.ProductTo;

public interface ClientEntityService {

public ClientEntity findOne(Long id);
	
	public void createOne(ProductTo productTo);
	
	
	public void updateOne(ProductTo productTo);
	
	
	public void deleteOne(ProductTo productTo);
	
	List<ClientEntity> findAll();
}