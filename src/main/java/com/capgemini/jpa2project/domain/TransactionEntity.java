package com.capgemini.jpa2project.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class TransactionEntity extends AbstractEntity {
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	private Date orderDate;
	@ManyToOne
	private ClientEntity client;
	@OneToMany
	@JoinColumn(name="transaction_id")
	private List<ProductListEntity> productList;
	
	
	
}
