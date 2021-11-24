package com.fiap.ifoodonation.service;

import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dto.CustomerDto;

public interface CustomerService {

	Customer getByEmail(String email);
	Customer create(CustomerDto customerDto);
}
