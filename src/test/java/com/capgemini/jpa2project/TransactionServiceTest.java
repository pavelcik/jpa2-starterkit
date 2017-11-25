package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.OrderStatus;
import com.capgemini.jpa2project.domain.TransactionEntity;
import com.capgemini.jpa2project.exceptions.BusinessException;
import com.capgemini.jpa2project.exceptions.ExceptionMessages;
import com.capgemini.jpa2project.mapper.TransactionMapper;
import com.capgemini.jpa2project.service.TransactionService;
import com.capgemini.jpa2project.to.TransactionTo;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
		"spring.datasource.password=Qwerty123" })
@Transactional
public class TransactionServiceTest {

	@Autowired
	private TransactionService service;

	@Autowired
	private TransactionMapper mapper;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldCheckIfMapperWorks() throws BusinessException {
		// given
		LocalDate localOrderdate = LocalDate.of(2012, 10, 9);
		Date dateFrom = Date.valueOf(localOrderdate);
		TransactionTo transactionTo = TransactionTo.builder().orderDate(dateFrom).status(OrderStatus.IN_ORDER).build();
		TransactionEntity entity = service.findOne(20L);
		// when
		TransactionEntity mappedEntity = mapper.map(entity, transactionTo);
		assertEquals(mappedEntity.getId(), entity.getId());
		assertEquals(mappedEntity.getOrderDate(), transactionTo.getOrderDate());
		assertEquals(mappedEntity.getStatus(), transactionTo.getStatus());

	}

	@Test
	public void shouldCheckIfMapperWorksForTo() throws BusinessException {
		// given
		TransactionEntity entity = service.findOne(20L);
		// when
		TransactionTo transactionTo = mapper.map(entity);
		assertEquals(transactionTo.getId(), entity.getId());
		assertEquals(transactionTo.getClient(), entity.getClient());
		assertEquals(transactionTo.getOrderDate(), entity.getOrderDate());
		assertEquals(transactionTo.getProductList(), entity.getProductList());
		assertEquals(transactionTo.getStatus(), entity.getStatus());
		assertEquals(transactionTo.getVersion(), entity.getVersion());
	}

	@Test
	public void shouldFindAllTransactions() {
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
		TransactionEntity transactionEntity = service.findOne(id);
		// then
		assertEquals(id, transactionEntity.getId());

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

		Long id = 101L;
		TransactionEntity entity = new TransactionEntity();
		LocalDate localOrderdate = LocalDate.of(2012, 10, 9);
		Date orderDate = Date.valueOf(localOrderdate);
		TransactionTo transactionTo = TransactionTo.builder().orderDate(orderDate).status(OrderStatus.PROCESSED)
				.build();
		// when
		service.createOne(entity, transactionTo);
		int productListSize = service.findAll().size();
		// then
		assertEquals(101, productListSize);
		assertEquals(id, entity.getId());
	}

	@Test
	public void shouldNotCreateNewProductListAndAddItToDatabase() throws BusinessException {
		// given
		TransactionTo transactionTo = TransactionTo.builder().id(20L).status(OrderStatus.PROCESSED).build();
		// when
		TransactionEntity entity = new TransactionEntity();
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_SET_MANUALLY);
		// then
		service.createOne(entity, transactionTo);
	}

	@Test
	public void shouldUpdateProductList() throws BusinessException {
		// given
		Long entityId = 36L;
		TransactionEntity entity = service.findOne(entityId);
		LocalDate localOrderdate = LocalDate.of(2012, 10, 9);
		Date orderDate = Date.valueOf(localOrderdate);
		TransactionTo transactionTo = TransactionTo.builder().orderDate(orderDate).status(OrderStatus.PROCESSED)
				.build();
		// when
		service.updateOne(entity, transactionTo);
		// then
		assertEquals(entityId, entity.getId());

	}

	@Test
	public void shouldCheckIfUpdateUpdatesProductListAndNotAddsNew() throws BusinessException {
		// given

		Long entityId = 36L;
		TransactionEntity entity = service.findOne(entityId);
		LocalDate localOrderdate = LocalDate.of(2012, 10, 9);
		Date orderDate = Date.valueOf(localOrderdate);
		TransactionTo transactionTo = TransactionTo.builder().orderDate(orderDate).status(OrderStatus.PROCESSED)
				.build();
		// when
		service.updateOne(entity, transactionTo);
		int transactionListSize = service.findAll().size();
		// then
		assertEquals(100, transactionListSize);
	}

	@Test
	public void shouldNotUpdateProductList() throws BusinessException {
		// given
		Long entityId = 136L;
		LocalDate localOrderdate = LocalDate.of(2012, 10, 9);
		Date orderDate = Date.valueOf(localOrderdate);
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.OBJECT_NOT_FOUND);
		TransactionEntity entity = service.findOne(entityId);
		TransactionTo transactionTo = TransactionTo.builder().orderDate(orderDate).status(OrderStatus.PROCESSED)
				.build();
		// when
		service.updateOne(entity, transactionTo);

	}

	@Test
	public void shouldDeleteProductList() throws BusinessException {
		// given
		TransactionTo to = TransactionTo.builder().id(36L).build();
		int transactionListSize = service.findAll().size();
		// when
		service.deleteOne(to);
		int newTransactionListSize = service.findAll().size();
		// then
		assertEquals(transactionListSize - 1, newTransactionListSize);
	}

	@Test
	public void shouldNotDeleteProductList() throws BusinessException {
		// given
		TransactionTo to = TransactionTo.builder().id(256L).build();
		int transactionListSize = service.findAll().size();
		// when
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage(ExceptionMessages.ID_NOT_VALID);
		service.deleteOne(to);

		int newTransactionListSize = service.findAll().size();
		// then
		assertEquals(transactionListSize - 1, newTransactionListSize);
	}

}
