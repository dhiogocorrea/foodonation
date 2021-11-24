package com.fiap.ifoodonation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dao.Picture;
import com.fiap.ifoodonation.dto.CustomerDto;
import com.fiap.ifoodonation.service.CustomerService;
import com.fiap.ifoodonation.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
		this.customerRepository = customerRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Customer getByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Customer create(CustomerDto customerDto) {
		if (this.getByEmail(customerDto.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered.");
		}
		
		if (!customerDto.getPassword().equals(customerDto.getPasswordConfirmation())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation Password doesn't match.");
		}

		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setLastName(customerDto.getLastName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));

		List<Picture> pictures = new ArrayList<Picture>();
		pictures.add(new Picture(customerDto.getPicture()));
		customer.setPictures(pictures);
		
		customer = customerRepository.save(customer);
		return customer;
	}

}
