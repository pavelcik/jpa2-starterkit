package com.capgemini.jpa2project;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.service.impl.ClientEntityService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = { "spring.profiles.active=mysql", "spring.datasource.username=root",
"spring.datasource.password=Qwerty123" })
public class CarServiceTest {
	@Autowired
	private ClientEntityService service;

	@Test
	public void shouldSaveClient() {
		ClientEntity entity = new ClientEntity();
		entity.setAddress("Nowa 12 Wrocław");
		LocalDate date = LocalDate.of(1979, 10, 23);
		entity.setDateOfBirth(Date.valueOf(date));
		entity.setEmail("lol123@gmail.com");
		entity.setName("Marek");
		entity.setSurname("Kowalski");
		entity.setPhone("512314790");
		service.save(entity);
		
	}

}
