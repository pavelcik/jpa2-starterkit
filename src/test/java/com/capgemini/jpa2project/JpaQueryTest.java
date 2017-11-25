package com.capgemini.jpa2project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.domain.OrderStatus;
import com.capgemini.jpa2project.domain.ProductEntity;
import com.capgemini.jpa2project.service.JPaQueryService;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
		"spring.datasource.password=Qwerty123" })
public class JpaQueryTest {

	@Autowired
	private JPaQueryService service;

	@Test
	public void shouldCalculateTransaction() {
		// given
		Long id = 2L;
		BigDecimal result = new BigDecimal("290.00");
		// when
		BigDecimal calculationResult = service.calculateTransaction(id);
		// then
		assertEquals(result, calculationResult);
	}

	@Test
	public void shouldCalculateTransactionWithMorethanOneItemBought() {
		// given
		Long id = 18L;
		BigDecimal result = new BigDecimal("262.71");
		// when
		BigDecimal calculationResult = service.calculateTransaction(id);
		// then
		assertEquals(result, calculationResult);
	}

	@Test
	public void shouldFindUniqueListOfProductsByClient() {
		Long clientId = 1L;

		// when
		List<String> productList = service.uniqueShoppingListByClient(clientId);

		assertEquals("Lamb - Bones", productList.get(0));
		assertEquals("Pastry - Apple Large", productList.get(1));
	}

	@Test
	public void shouldFindTop10() {
		List<ProductEntity> top10 = service.tenBestsellingProducts();
		assertEquals(10, top10.size());
		int first = top10.get(0).getOrderedProducts().size();
		int second = top10.get(1).getOrderedProducts().size();

		int sumOFBoughtProductsforfirst = 0;
		for (int i = 0; i < first; i++) {
			sumOFBoughtProductsforfirst += top10.get(0).getOrderedProducts().get(i).getNumberOfItems();
		}
		int sumOfBoughtProductsForSecond = 0;
		for (int i = 0; i < second; i++) {
			sumOfBoughtProductsForSecond += top10.get(1).getOrderedProducts().get(i).getNumberOfItems();
		}

		assertTrue(sumOFBoughtProductsforfirst > sumOfBoughtProductsForSecond);

	}

	@Test
	public void shouldFind3ClientsWithPriciestCart() {
		LocalDate fromLocal = LocalDate.of(2016, 3, 1);
		LocalDate toLocal = LocalDate.of(2016, 3, 31);
		Long id = 17L;
		Date from = Date.valueOf(fromLocal);
		Date to = Date.valueOf(toLocal);
		List<ClientEntity> result = service.moneySpentByClient(from, to);
		assertEquals(3, result.size());
		assertEquals(id, result.get(0).getId());
		assertEquals(Date.valueOf("2016-03-08"), result.get(0).getClientOrders().get(0).getOrderDate());
	}

	@Test
	public void shouldFindProductsWithProcessingStatus() {
		Long id = 20L;
		List<ProductEntity> productsInProcessingStatus = service.productsInRealisation();
		assertEquals(16, productsInProcessingStatus.size());
		assertEquals(id, productsInProcessingStatus.get(0).getId());
		assertEquals(OrderStatus.PROCESSING,
				productsInProcessingStatus.get(0).getOrderedProducts().get(0).getTransaction().getStatus());
	}

	@Test
	public void shouldCalculateProfitInMonthWhenThereAreNoOrdersForTransactionInDatabase() {
		LocalDate fromLocal = LocalDate.of(2016, 1, 1);
		LocalDate toLocal = LocalDate.of(2016, 1, 31);
		BigDecimal profitFromDatabase = new BigDecimal("0");
		Date from = Date.valueOf(fromLocal);
		Date to = Date.valueOf(toLocal);
		BigDecimal profitInMonth = service.profitInMonth(from, to);
		assertEquals(profitFromDatabase, profitInMonth);
	}

	@Test
	public void shouldCalculateProfitForMonth() {
		LocalDate fromLocal = LocalDate.of(2016, 2, 1);
		LocalDate toLocal = LocalDate.of(2016, 2, 28);
		BigDecimal profitFromDatabase = new BigDecimal("22.57351500");
		Date from = Date.valueOf(fromLocal);
		Date to = Date.valueOf(toLocal);
		BigDecimal profitInMonth = service.profitInMonth(from, to);
		assertEquals(profitFromDatabase, profitInMonth);
	}

	@Test
	public void shouldFindClientsWithProcessingStatusOfTransaction() {

		List<ClientEntity> clientsWithProcessingStatus = service.clientsWithProcessingStatus();
		assertEquals(11, clientsWithProcessingStatus.size());
		assertEquals(OrderStatus.PROCESSING, clientsWithProcessingStatus.get(0).getClientOrders().get(0).getStatus());
		assertEquals(OrderStatus.PROCESSING, clientsWithProcessingStatus.get(0).getClientOrders().get(0).getStatus());
		assertEquals(OrderStatus.PROCESSING, clientsWithProcessingStatus.get(0).getClientOrders().get(0).getStatus());
		assertEquals(OrderStatus.PROCESSING, clientsWithProcessingStatus.get(0).getClientOrders().get(0).getStatus());
		assertTrue(clientsWithProcessingStatus.get(0).getId() != clientsWithProcessingStatus.get(1).getId());
	}
}