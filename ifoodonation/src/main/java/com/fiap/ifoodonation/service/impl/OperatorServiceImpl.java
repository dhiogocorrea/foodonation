package com.fiap.ifoodonation.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.ifoodonation.dao.Operator;
import com.fiap.ifoodonation.dao.Order;
import com.fiap.ifoodonation.dao.Picture;
import com.fiap.ifoodonation.dto.OperatorDto;
import com.fiap.ifoodonation.enums.OrderStatus;
import com.fiap.ifoodonation.repository.OperatorRepository;
import com.fiap.ifoodonation.repository.OrderRepository;
import com.fiap.ifoodonation.service.OperatorService;

@Service
public class OperatorServiceImpl implements OperatorService {

	OperatorRepository operatorRepository;
	
	OrderRepository orderRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public OperatorServiceImpl(OperatorRepository operatorRepository, OrderRepository orderRepository, PasswordEncoder passwordEncoder) {
		this.operatorRepository = operatorRepository;
		this.orderRepository = orderRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Operator getMe(long operatorId) {
		Optional<Operator> opt = operatorRepository.findById(operatorId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator not found.");
		}
		
		return opt.get();
	}

	@Override
	public Operator getByEmail(String email) {
		return operatorRepository.findByEmail(email);
	}

	@Override
	public Operator create(OperatorDto operatorDto) {
		if (this.getByEmail(operatorDto.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Operator already registered.");
		}
		
		if (!operatorDto.getPassword().equals(operatorDto.getPasswordConfirmation())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation Password doesn't match.");
		}
		
		Operator operator = new Operator();
		
		operator.setName(operatorDto.getName());
		operator.setLastName(operatorDto.getLastName());
		operator.setEmail(operatorDto.getEmail());
		operator.setCpf(operatorDto.getCpf());
		operator.setPixKey(operatorDto.getPixKey());
		operator.setPixKeyType(operatorDto.getPixKeyType());
		
		ArrayList<Picture> pictures = new ArrayList<>();
		pictures.add(new Picture(operatorDto.getFrontPicture()));
		pictures.add(new Picture(operatorDto.getSelfieWithDocumentPicture()));
		operator.setPictures(pictures);
		
		ArrayList<Picture> picturesDoc = new ArrayList<>();
		picturesDoc.add(new Picture(operatorDto.getFrontDocumentPicture()));
		picturesDoc.add(new Picture(operatorDto.getBackDocumentPicture()));
		operator.setDocPictures(picturesDoc);
		
		operator.setPassword(passwordEncoder.encode(operatorDto.getPassword()));
		
		operator = operatorRepository.save(operator);

		return operator;
	}

	@Override
	public void lockOrder(long orderId, long operatorId) {
		Order order = getOrder(orderId);
		Operator operator = getMe(operatorId);
		
		if (!order.getStatus().equals(OrderStatus.OPEN)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order already taken.");
		}
		
		order.setStatus(OrderStatus.LOCKED);
		order.setOperator(operator);
		
		orderRepository.save(order);
	}

	@Override
	public void sendDeliveryReceipt(long orderId, long operatorId, String picture) {
		Order order = getOrder(orderId);
		Operator operator = getMe(operatorId);
		
		if (order.getOperator().getOperatorId() != operator.getOperatorId()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator cannot perform this operation.");
		}
		
		order.setStatus(OrderStatus.DELIVERING);
		order.setRecepitDelivering(new Picture(picture));
		orderRepository.save(order);
	}

	@Override
	public void sendConcludedReceipt(long orderId, long operatorId, String picture) {
		Order order = getOrder(orderId);
		Operator operator = getMe(operatorId);
		
		if (order.getOperator().getOperatorId() != operator.getOperatorId()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Operator cannot perform this operation.");
		}
		
		order.setStatus(OrderStatus.COMPLETED);
		order.setRecepitConcluded(new Picture(picture));
		orderRepository.save(order);
	}

	private Order getOrder(long orderId) {
		Optional<Order> opt = orderRepository.findById(orderId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found.");
		}
		
		return opt.get();
	}

}
