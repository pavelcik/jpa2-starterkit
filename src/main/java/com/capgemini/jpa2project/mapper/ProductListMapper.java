package com.capgemini.jpa2project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.to.ProductListTo;
@Component
public class ProductListMapper {
	
	public ProductListTo map(ProductListEntity productListEntity) {
		ProductListTo product = null;
				
				
		if (productListEntity != null) {
			product = new ProductListTo.Builder().numberOfItems(productListEntity.getNumberOfItems())
					.transaction(productListEntity.getTransaction()).build();
		}
		return product;
	}
	
	public ProductListEntity map(ProductListTo productListTo) {
		
		ProductListEntity productListEntity = new ProductListEntity();
		
		if (productListEntity != null) {
			productListEntity.setId(productListTo.getId());
			productListEntity.setNumberOfItems(productListTo.getNumberOfItems());
			productListEntity.setTransaction(productListEntity.getTransaction());
			productListEntity.setVersion(productListEntity.getVersion());
		}
		return productListEntity;
	}
	
	public List<ProductListTo> map2To(List<ProductListEntity> productListEntities) {
		return productListEntities.stream().map(c->map(c)).collect(Collectors.toList());
	}
}
