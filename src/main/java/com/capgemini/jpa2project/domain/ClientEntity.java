package com.capgemini.jpa2project.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Client")
public class ClientEntity extends AbstractEntity {

	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	private Date dateOfBirth;
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<TransactionEntity> clientOrders;

	
	
}
