package com.fiap.ifoodonation.service;

import com.fiap.ifoodonation.dao.Operator;
import com.fiap.ifoodonation.dto.OperatorDto;

public interface OperatorService {

	Operator getMe();
	Operator getByEmail(String email);
	Operator create(OperatorDto operatorDto);
	void lockOrder(long orderId, long operatorId);
	void sendDeliveryReceipt(long orderId, long operatorId, String picture);
	void sendConcludedReceipt(long orderId, long operatorId, String picture);
}
