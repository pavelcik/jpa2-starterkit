package com.capgemini.jpa2project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.dao.ProductListDao;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.QProductListEntity;
import com.capgemini.jpa2project.to.ProductTo;

@Component
public class ProductMapper {

	@Autowired
	private ProductListDao dao;

	public ProductTo map(ProductEntity productEntity) {
		ProductTo productTo = null;

		if (productEntity != null) {
			productTo = ProductTo.builder().id(productEntity.getId()).margin(productEntity.getMargin())
					.productName(productEntity.getProductName()).Version(productEntity.getVersion()).unitPrice(productEntity.getUnitPrice()).build();
		}
		return productTo;
	}

	public ProductEntity map(ProductTo productTo) {

		ProductEntity productEntity = new ProductEntity();

		if (productEntity != null) {
			productEntity.setId(productTo.getId());
			productEntity.setMargin(productTo.getMargin());
			productEntity.setProductName(productTo.getProductName());
			productEntity.setUnitPrice(productTo.getUnitPrice());
			productEntity.setVersion(productTo.getVersion());
			productEntity.setOrderedProducts(dao.findAllForProductId(productTo.getId()));
		}
		return productEntity;
	}

	public List<ProductTo> map2To(List<ProductEntity> productEntities) {
		return productEntities.stream().map(c -> map(c)).collect(Collectors.toList());
	}
}
