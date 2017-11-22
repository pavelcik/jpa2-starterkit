package com.capgemini.jpa2project.to;

import javax.persistence.ManyToOne;

import com.capgemini.jpa2project.domain.TransactionEntity;

public class ProductListTo extends AbstractTo{

	private int numberOfItems;
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

	public static class Builder {
		private int numberOfItems;
		private TransactionEntity transaction;

		public Builder numberOfItems(int numberOfItems) {
			this.numberOfItems = numberOfItems;
			return this;
		}

		public Builder transaction(TransactionEntity transaction) {
			this.transaction = transaction;
			return this;
		}

		public ProductListTo build() {
			return new ProductListTo(this);
		}
	}

	private ProductListTo(Builder builder) {
		this.numberOfItems = builder.numberOfItems;
		this.transaction = builder.transaction;
	}
}
