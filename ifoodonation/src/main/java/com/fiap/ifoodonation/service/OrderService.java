package com.fiap.ifoodonation.service;

import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dto.OrderCreateDto;
import com.fiap.ifoodonation.dto.OrderDto;

public interface OrderService {

	Iterable<Order> getAll();
	Iterable<Order> getByCustomer(long customerId);
	OrderCreateDto getOne(long orderId);
	Order create(OrderDto orderDto, long customerId);
}
