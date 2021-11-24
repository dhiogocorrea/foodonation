package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {	
	Operator findByEmail(String email);
}
