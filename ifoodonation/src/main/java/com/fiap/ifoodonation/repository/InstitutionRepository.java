package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Institution;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {	
	Institution findByEmail(String email);
}
