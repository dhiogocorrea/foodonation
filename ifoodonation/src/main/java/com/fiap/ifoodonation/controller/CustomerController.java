package com.fiap.ifoodonation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ifoodonation.service.CustomerService;
import com.fiap.ifoodonation.service.OrderService;
import com.fiap.ifoodonation.service.JwtUserDetailsService.Role;

import io.swagger.annotations.ApiOperation;

import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dto.CustomerDto;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/customer/me", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Customer getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return customerService.getByEmail(auth.getName());
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@RequestBody CustomerDto customerDto) {
		return customerService.create(customerDto);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/customer/orders", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getOrders() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long customerId = Long.parseLong(r.getAuthority());
		return orderService.getByCustomer(customerId);
	}
}
