package com.capgemini.jpa2project.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_list")
public class ProductListEntity extends AbstractEntity {

	private int numberOfItems;
	
	@ManyToOne
	private TransactionEntity transaction;
	
	public int getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public TransactionEntity getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionEntity transaction) {
		this.transaction = transaction;
	}

	
	
	
}
