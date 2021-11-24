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

import com.fiap.ifoodonation.dao.Operator;
import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dto.OperatorDto;
import com.fiap.ifoodonation.service.OperatorService;
import com.fiap.ifoodonation.service.OrderService;
import com.fiap.ifoodonation.service.JwtUserDetailsService.Role;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin
public class OperatorController {

	@Autowired
	OperatorService operatorService;
	
	@Autowired
	OrderService orderService;
	
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/operator/me", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Operator getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return operatorService.getByEmail(auth.getName());
	}
	
	@RequestMapping(value = "/operator", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Operator create(@RequestBody OperatorDto operatorDto) {
		return operatorService.create(operatorDto);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/operator/orders", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getOrders() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long id = Long.parseLong(r.getAuthority());
		return orderService.getByOperator(id);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/operator/order/{orderId}/lock", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void lockOrder(@PathVariable long orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long id = Long.parseLong(r.getAuthority());
		operatorService.lockOrder(orderId, id);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/operator/order/{orderId}/deliveryreceipt", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendDeliveryReceipt(@PathVariable long orderId, @RequestBody String picture) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long id = Long.parseLong(r.getAuthority());
		operatorService.sendDeliveryReceipt(orderId, id, picture);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/operator/order/{orderId}/concludedreceipt", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendConcludedReceipt(@PathVariable long orderId, @RequestBody String picture) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long id = Long.parseLong(r.getAuthority());
		operatorService.sendConcludedReceipt(orderId, id, picture);
	}
	
	
}
