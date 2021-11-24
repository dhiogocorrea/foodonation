package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Institution;

public interface AddressRepository extends CrudRepository<Address, Long> {	
	Iterable<Address> getByInstitution(Institution institution);
}
