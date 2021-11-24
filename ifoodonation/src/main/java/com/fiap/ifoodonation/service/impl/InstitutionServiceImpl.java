package com.fiap.ifoodonation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Picture;
import com.fiap.ifoodonation.dto.AddressDto;
import com.fiap.ifoodonation.dto.InstitutionDto;
import com.fiap.ifoodonation.service.InstitutionService;
import com.fiap.ifoodonation.repository.AddressRepository;
import com.fiap.ifoodonation.repository.InstitutionRepository;

@Service
public class InstitutionServiceImpl implements InstitutionService {
	
	private InstitutionRepository institutionRepository;
	private AddressRepository addressRepository;
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public InstitutionServiceImpl(InstitutionRepository institutionRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
		this.institutionRepository = institutionRepository;
		this.addressRepository = addressRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public Iterable<Institution> getAll() {
		return institutionRepository.findAll();
	}

	@Override
	public Institution getByEmail(String email) {
		return institutionRepository.findByEmail(email);
	}

	@Override
	public Institution create(InstitutionDto institutionDto) {
		if (this.getByEmail(institutionDto.getEmail()) != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered.");
		}
		
		if (!institutionDto.getPassword().equals(institutionDto.getPasswordConfirmation())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password and Confirmation Password doesn't match.");
		}

		Institution institution = new Institution();
		institution.setCnpj(institutionDto.getCnpj());
		institution.setEmail(institutionDto.getEmail());
		institution.setCompanyName(institutionDto.getCompanyName());
		institution.setTradingName(institutionDto.getTradingName());
		institution.setDescription(institutionDto.getDescription());
		institution.setPassword(passwordEncoder.encode(institutionDto.getPassword()));

		List<Picture> pictures = new ArrayList<Picture>();
		pictures.add(new Picture(institutionDto.getPicture()));
		institution.setPictures(pictures);
		
		institution = institutionRepository.save(institution);
		return institution;
	}

	@Override
	public Iterable<Address> getAddresses(long institutionId) {
		Institution institution = getOne(institutionId);
		return addressRepository.getByInstitution(institution);
	}

	@Override
	public Address addAddress(AddressDto addressDto, long institutionId) {
		Institution institution = getOne(institutionId);
		
		Address address = new Address();
		address.setCountry(addressDto.getCountry());
		address.setState(addressDto.getState());
		address.setCity(addressDto.getCity());
		address.setPostalCode(addressDto.getPostalCode());
		address.setStreet(addressDto.getStreet());
		address.setStreetNumber(addressDto.getStreetNumber());
		address.setComplement(addressDto.getComplement());
		address.setPrincipal(addressDto.isPrincipal());

		address.setInstitution(institution);
		
		address = addressRepository.save(address);
		
		return address;
	}
	
	private Institution getOne(long institutionId) {
		Optional<Institution> opt = institutionRepository.findById(institutionId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found.");
		}
		
		return opt.get();
	}

}
