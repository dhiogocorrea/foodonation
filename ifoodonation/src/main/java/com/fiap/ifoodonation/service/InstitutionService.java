package com.fiap.ifoodonation.service;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dto.AddressDto;
import com.fiap.ifoodonation.dto.InstitutionDto;

public interface InstitutionService {

	Iterable<Institution> getAll();
	Institution getByEmail(String email);
	Institution create(InstitutionDto institutionDto);
	Iterable<Address> getAddresses(long institutionId);
	Address addAddress(AddressDto addressDto, long institutionId);
}
