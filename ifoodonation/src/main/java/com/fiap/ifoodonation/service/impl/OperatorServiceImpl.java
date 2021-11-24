package com.fiap.ifoodonation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ifoodonation.dao.Operator;
import com.fiap.ifoodonation.dto.OperatorDto;
import com.fiap.ifoodonation.repository.OperatorRepository;
import com.fiap.ifoodonation.service.OperatorService;

@Service
public class OperatorServiceImpl implements OperatorService {

	OperatorRepository operatorRepository;
	
	@Autowired
	public OperatorServiceImpl(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}
	
	@Override
	public Operator getMe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operator getByEmail(String email) {
		return operatorRepository.findByEmail(email);
	}

	@Override
	public Operator create(OperatorDto operatorDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockOrder(long orderId, long operatorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendDeliveryReceipt(long orderId, long operatorId, String picture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendConcludedReceipt(long orderId, long operatorId, String picture) {
		// TODO Auto-generated method stub
		
	}

}
