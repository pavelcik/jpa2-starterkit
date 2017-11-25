package com.capgemini.jpa2project.to;

import java.sql.Date;
import java.util.List;

import com.capgemini.jpa2project.domain.TransactionEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTo extends AbstractTo {
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	private Date dateOfBirth;
	private List<TransactionEntity> clientOrders;

	@Builder
	public ClientTo(Long id, long Version, String name, String surname, String email, String phone, String address,
			Date dateOfBirth, List<TransactionEntity> clientOrders) {
		super(id, Version);
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.clientOrders = clientOrders;
	}

}
