package com.fiap.ifoodonation.dto;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Order;

public class OrderCreateDto {
	Order order;
	Institution institution;
	Iterable<Address> address;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public Iterable<Address> getAddress() {
		return address;
	}
	public void setAddress(Iterable<Address> address) {
		this.address = address;
	}
}
