package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dao.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {	
	Iterable<Order> getByCustomer(Customer customer);
}
