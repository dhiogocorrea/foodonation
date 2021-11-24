package com.fiap.ifoodonation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dto.OrderCreateDto;
import com.fiap.ifoodonation.dto.OrderDto;
import com.fiap.ifoodonation.service.OrderService;
import com.fiap.ifoodonation.service.JwtUserDetailsService.Role;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getAll() {
		return orderService.getAll();
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Order create(@RequestBody OrderDto orderDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long customerId = Long.parseLong(r.getAuthority());
		return orderService.create(orderDto, customerId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public OrderCreateDto getOne(@PathVariable long orderId) {
		return orderService.getOne(orderId);
	}
}
