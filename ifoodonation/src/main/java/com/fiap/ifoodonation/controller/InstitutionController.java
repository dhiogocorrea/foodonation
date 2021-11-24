package com.fiap.ifoodonation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ifoodonation.dao.Address;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Wishlist;
import com.fiap.ifoodonation.dto.AddressDto;
import com.fiap.ifoodonation.dto.InstitutionDto;
import com.fiap.ifoodonation.service.InstitutionService;
import com.fiap.ifoodonation.service.JwtUserDetailsService.Role;
import com.fiap.ifoodonation.service.WishlistService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class InstitutionController {
	
	@Autowired
	InstitutionService institutionService;
	
	@Autowired
	WishlistService wishlistService;
	
	@RequestMapping(value = "/institution", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Institution> getAll() {
		return institutionService.getAll();
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/institution/me", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Institution getMe() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return institutionService.getByEmail(auth.getName());
	}

	@RequestMapping(value = "/institution", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Institution create(@RequestBody InstitutionDto institutionDto) {
		return institutionService.create(institutionDto);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/institution/address", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Address> getAddresses() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		return institutionService.getAddresses(institutionId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/institution/address", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Address addAddress(@RequestBody AddressDto addressDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		return institutionService.addAddress(addressDto, institutionId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/institution/wishlist", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Wishlist> getWishlists() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		return wishlistService.getByInstitution(institutionId);
	}

}
