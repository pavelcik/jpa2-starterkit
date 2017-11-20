package com.capgemini.jpa2project.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.jpa2project.domain.ClientEntity;

public interface ClientEntityDao extends CrudRepository<ClientEntity, Serializable>  {

}
