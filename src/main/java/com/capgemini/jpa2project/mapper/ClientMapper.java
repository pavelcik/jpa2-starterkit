package com.capgemini.jpa2project.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.capgemini.jpa2project.domain.ClientEntity;
import com.capgemini.jpa2project.to.ClientTo;

@Component
public class ClientMapper {

	public ClientTo map(ClientEntity clientEntity) {
		ClientTo clientTo = null;

		if (clientEntity != null) {
			clientTo = ClientTo.builder().address(clientEntity.getAddress()).clientOrders(clientEntity.getClientOrders())
					.dateOfBirth(clientEntity.getDateOfBirth()).email(clientEntity.getEmail())
					.id(clientEntity.getId()).name(clientEntity.getName()).surname(clientEntity.getSurname())
					.Version(clientEntity.getVersion()).build();
		}
		return clientTo;
	}

	public ClientEntity map(ClientEntity entity,ClientTo clientTo) {

		if(entity!=null) {
			entity.setAddress(clientTo.getAddress());
			entity.setDateOfBirth(clientTo.getDateOfBirth());
			entity.setEmail(clientTo.getEmail());
			entity.setId(clientTo.getId());
			entity.setName(clientTo.getName());
			entity.setSurname(clientTo.getSurname());
			entity.setPhone(clientTo.getPhone());
			entity.setVersion(clientTo.getVersion());
			return entity;
		} else {
			ClientEntity entity2 = new ClientEntity();
			entity2.setAddress(clientTo.getAddress());
			entity2.setClientOrders(clientTo.getClientOrders());
			entity2.setDateOfBirth(clientTo.getDateOfBirth());
			entity2.setEmail(clientTo.getEmail());
			entity2.setId(clientTo.getId());
			entity2.setName(clientTo.getName());
			entity2.setSurname(clientTo.getSurname());
			entity2.setPhone(clientTo.getPhone());
			entity2.setVersion(clientTo.getVersion());
			return entity2;
		}
	}

		

	public List<ClientTo> map2To(List<ClientEntity> clientEntities) {
		return clientEntities.stream().map(c -> map(c)).collect(Collectors.toList());
	}
}
