package com.fiap.ifoodonation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Operator;
import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dao.WishlistItem;
import com.fiap.ifoodonation.dto.OrderCreateDto;
import com.fiap.ifoodonation.dto.OrderDto;
import com.fiap.ifoodonation.enums.OrderStatus;
import com.fiap.ifoodonation.repository.AddressRepository;
import com.fiap.ifoodonation.repository.CustomerRepository;
import com.fiap.ifoodonation.repository.OperatorRepository;
import com.fiap.ifoodonation.repository.OrderRepository;
import com.fiap.ifoodonation.repository.WishlistItemRepository;
import com.fiap.ifoodonation.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private CustomerRepository customerRepository;
	private WishlistItemRepository wishlistItemRepository;
	private AddressRepository addressRepository;
	private OperatorRepository operatorRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, WishlistItemRepository wishlistItemRepository, AddressRepository addressRepository, OperatorRepository operatorRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.wishlistItemRepository = wishlistItemRepository;
		this.addressRepository = addressRepository;
		this.operatorRepository = operatorRepository;
	}
	
	@Override
	public Iterable<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Iterable<Order> getByCustomer(long customerId) {
		Customer customer = getCustomer(customerId);
		return orderRepository.getByCustomer(customer);
	}
	
	@Override
	public Iterable<Order> getByOperator(long operatorId) {
		Operator operator = getOperator(operatorId);
		return orderRepository.getByOperator(operator);
	}

	@Override
	public OrderCreateDto getOne(long orderId) {
		Optional<Order> opt = orderRepository.findById(orderId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
		}
		Order order = opt.get();
		
		OrderCreateDto orderCreateDto = new OrderCreateDto();
		orderCreateDto.setOrder(order);
		
		WishlistItem items = (WishlistItem) order.getWishlistItems().toArray()[0];
		Institution institution = items.getWishlist().getInstitution();
		orderCreateDto.setInstitution(institution);
		orderCreateDto.setAddress(addressRepository.getByInstitution(institution));
		
		return orderCreateDto;
	}

	@Override
	public Order create(OrderDto orderDto, long customerId) {
		Customer customer = getCustomer(customerId);
		
		Order order = new Order();
		
		List<WishlistItem> wishlistItems = orderDto.getWishListItemIds().stream().map(id -> getWishlistItem(id)).collect(Collectors.toList());
		
		Optional<Float> totalPriceOpt = wishlistItems.stream().map(x -> x.getPrice()* (x.getQuantity() == 0 ? 1 : x.getQuantity())).reduce((x, y) -> x + y);
		
		order.setWishlistItems(wishlistItems);
		order.setStatus(OrderStatus.OPEN);
		order.setCustomer(customer);
		
		if(totalPriceOpt.isPresent()) {
			order.setTotalPrice(totalPriceOpt.get());
		}		
		orderRepository.save(order);
		
		return null;
	}
	
	private Customer getCustomer(long customerId) {
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found.");
		}
		return opt.get();
	}
	
	private Operator getOperator(long operatorId) {
		Optional<Operator> opt = operatorRepository.findById(operatorId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator not found.");
		}
		return opt.get();
	}
	
	private WishlistItem getWishlistItem(long wishlistItemId) {
		Optional<WishlistItem> opt = wishlistItemRepository.findById(wishlistItemId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wishlist item not found.");
		}
		return opt.get();
	}

}
