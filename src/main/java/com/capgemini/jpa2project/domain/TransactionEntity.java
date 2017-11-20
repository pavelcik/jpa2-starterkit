package com.capgemini.jpa2project.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class TransactionEntity extends AbstractEntity {

	private OrderStatus status;
	private Date orderDate;
	@ManyToOne
	private ClientEntity client;
	@ManyToOne
	private ProductEntity product;
	
}
