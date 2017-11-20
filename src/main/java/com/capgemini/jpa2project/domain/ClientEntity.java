package com.capgemini.jpa2project.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Client")
public class ClientEntity extends AbstractEntity {

	private String name;
	private String surname;
	private String email;
	private String phone;
	private String address;
	private Date dateOfBirth;
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<TransactionEntity> clientOrders;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<TransactionEntity> getClientOrders() {
		return clientOrders;
	}
	public void setClientOrders(List<TransactionEntity> clientOrders) {
		this.clientOrders = clientOrders;
	}
	
	
}
