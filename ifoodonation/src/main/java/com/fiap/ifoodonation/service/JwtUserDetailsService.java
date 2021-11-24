package com.fiap.ifoodonation.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiap.ifoodonation.dao.Customer;
import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Operator;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	InstitutionService institutionService;
	
	@Autowired
	OperatorService operatorService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String[] splited = username.split("_____");
		
		if (splited[0].equals("customer")) {
			return loadForCustomer(splited[1]);
		} else if (splited[0].equals("operator")) {
			return loadForOperator(splited[1]);
		}
		
		return loadForInstitution(splited[1]);
	}
	
	private User loadForCustomer(String email) {
		Customer customer = customerService.getByEmail(email);

		if (customer != null && customer.getEmail().equals(email)) {
			ArrayList<Role> authorities = new ArrayList<>();
			authorities.add(new Role(customer.getCustomerId()));
			return new User(customer.getEmail(), customer.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("Customer not found with email: " + email);
		}
	}
	
	private User loadForInstitution(String email) {
		Institution institution = institutionService.getByEmail(email);
		
		if (institution != null && institution.getEmail().equals(email)) {
			ArrayList<Role> authorities = new ArrayList<>();
			authorities.add(new Role(institution.getInstitutionId()));
			return new User(institution.getEmail(), institution.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("Institution not found with email: " + email);
		}
	}
	
	private User loadForOperator(String email) {
		Operator operator = operatorService.getByEmail(email);
		
		if (operator != null && operator.getEmail().equals(email)) {
			ArrayList<Role> authorities = new ArrayList<>();
			authorities.add(new Role(operator.getOperatorId()));
			return new User(operator.getEmail(), operator.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("Operator not found with email: " + email);
		}
	}
	
	public class Role implements GrantedAuthority{

		private long id;
		
		public Role(long id) {
			this.id = id;
		}

		@Override
		public String getAuthority() {
			return String.valueOf(id);
		}

	}

	public UserDetails loadUser(String email, String userType) {
		if (userType.equals("customer")) {
			return loadForCustomer(email);
		} else if (userType.equals("operator")) {
			return loadForOperator(email);
		}
		
		return loadForInstitution(email);
	}
}
