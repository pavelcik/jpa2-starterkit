package com.capgemini.jpa2project.to;

import java.sql.Date;
import java.util.List;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.OrderStatus;
import com.capgemini.jpa2project.domain.ProductListEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionTo extends AbstractTo {

	private OrderStatus status;
	private Date orderDate;
	private ClientEntity client;
	private List<ProductListEntity> productList;
	
	@Builder
	public TransactionTo(Long id, long Version, OrderStatus status, Date orderDate, ClientEntity client,
			List<ProductListEntity> productList) {
		super(id, Version);
		this.status = status;
		this.orderDate = orderDate;
		this.client = client;
		this.productList = productList;
	}
	
	
	
	
}
