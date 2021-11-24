package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {	
	Customer findByEmail(String email);
}
