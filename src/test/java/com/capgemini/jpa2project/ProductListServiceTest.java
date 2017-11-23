package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.domain.ProductListEntity;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.mapper.ProductListMapper;
import com.capgemini.jpa2project.service.ProductEntityService;
import com.capgemini.jpa2project.service.ProductListEntityService;
import com.capgemini.jpa2project.service.TransactionService;
import com.capgemini.jpa2project.to.ProductListTo;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
		"spring.datasource.password=Qwerty123" })
public class ProductListServiceTest {

	@Autowired
	private ProductListMapper mapper;
	@Autowired
	private ProductListEntityService service;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ProductEntityService productService;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldCheckIfMapperWorks() throws BusinessException {
		// given

		ProductListTo productListTo = ProductListTo.builder().numberOfItems(2).build();
		ProductListEntity entity = service.findOne(39L);
		// when
		ProductListEntity productListEntity = mapper.map(entity, productListTo);
		assertEquals(productListEntity.getNumberOfItems(),productListTo.getNumberOfItems());
	}

	@Test
	public void shouldCheckIfMapperWorksForTo() throws BusinessException {
		// given
		Long id = 39L;
		ProductListEntity productListEntity = service.findOne(id);
		// when
		ProductListTo productListTo = mapper.map(productListEntity);

		assertEquals(productListTo.getNumberOfItems(),productListEntity.getNumberOfItems());
		assertEquals(productListTo.getId(),productListEntity.getId());
		assertEquals(productListTo.getProduct(),productListEntity.getProduct());
		assertEquals(productListTo.getTransaction(),productListEntity.getTransaction());
		assertEquals(productListTo.getVersion(),productListEntity.getVersion());
	}

	@Test
	public void shouldFindAllProductLists() {
		// given
		int size = 100;
		// when
		int listSize = service.findAll().size();
		// then
		assertEquals(size, listSize);

	}

	@Test
	public void shouldNotFindTheProductListSize() {
		// given
		int size = 50;
		// when
		int listSize = service.findAll().size();
		// then
		assertNotEquals(size, listSize);

	}

	@Test
	public void shouldFindOneProduct() throws BusinessException {
		// given
		Long id = 7L;
		// when
		ProductListEntity productList = service.findOne(id);
		// then
		assertEquals(id, productList.getId());

	}

	@Test
	public void shouldNotFindOneProduct() throws BusinessException {
		// given
		Long id = 2777L;
		// when
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.OBJECT_NOT_FOUND);
		service.findOne(id);
	}

	@Test
	public void shouldCreateNewProductListAndAddItToDatabase() throws BusinessException {
		// given
		TransactionEntity transaction = transactionService.findOne(20L);
		ProductEntity product = productService.findOne(22L);
		Long id = 101L;
		ProductListEntity entity = new ProductListEntity();
		ProductListTo newProduct = ProductListTo.builder().numberOfItems(5).transaction(transaction).product(product)
				.build();
		// when
		service.createOne(entity, newProduct);
		int productListSize = service.findAll().size();
		// then
		assertEquals(101, productListSize);
		assertEquals(id, entity.getId());
	}

	@Test
	public void shouldNotCreateNewProductListAndAddItToDatabase() throws BusinessException {
		// given
		ProductListTo newProduct = ProductListTo.builder().numberOfItems(5).id(200L).build();
		// when
		ProductListEntity entity = new ProductListEntity();
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_SET_MANUALLY);
		// then
		service.createOne(entity, newProduct);
	}
	
	@Test
	public void shouldUpdateProductList() throws BusinessException {
		// given
		Long entityId = 36L;
		ProductListEntity entity = service.findOne(entityId);
		ProductListTo newEntity = ProductListTo.builder().numberOfItems(5)
				.build();
		// when
		service.updateOne(entity, newEntity);
		// then
		assertEquals(entityId, entity.getId());
		assertEquals(entity.getNumberOfItems(), newEntity.getNumberOfItems());
	}
	
	@Test
	public void shouldCheckIfUpdateUpdatesProductListAndNotAddsNew() throws BusinessException {
		// given
		TransactionEntity transaction = transactionService.findOne(20L);
		ProductEntity product = productService.findOne(22L);
		Long entityId = 36L;
		Long id = 101L;
		ProductListEntity entity = service.findOne(entityId);
		ProductListTo newProduct = ProductListTo.builder().numberOfItems(5).transaction(transaction).product(product)
				.build();
		// when
		service.updateOne(entity, newProduct);
		int productListSize = service.findAll().size();
		// then
		assertEquals(100, productListSize);
	}

	@Test
	public void shouldNotUpdateProductList() throws BusinessException {
		// given
		TransactionEntity transaction = transactionService.findOne(20L);
		ProductEntity product = productService.findOne(22L);
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.OBJECT_NOT_FOUND);
		Long entityId = 200L;
		
		ProductListEntity entity = service.findOne(entityId);
		ProductListTo newEntity = ProductListTo.builder().numberOfItems(5).transaction(transaction).product(product)
				.build();
		// when
		service.updateOne(entity, newEntity);

	}
	
	@Test
	public void shouldDeleteProductList() throws BusinessException {
		// given
		
		ProductListTo to = ProductListTo.builder().id(20L).build();
		int productListSize = service.findAll().size();
		// when
		service.deleteOne(to);
		int newProductListSize = service.findAll().size();
		// then
		assertEquals(productListSize-1, newProductListSize);
	}
	
	@Test
	public void shouldNotDeleteProductList() throws BusinessException {
		// given
		ProductListTo to = ProductListTo.builder().id(236L).build();
		int productListSize = service.findAll().size();
		// when
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_NOT_VALID);
		service.deleteOne(to);
	
		int newProductListSize = service.findAll().size();
		// then
		assertEquals(productListSize-1, newProductListSize);
	}
	
	
}
