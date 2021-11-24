package com.fiap.ifoodonation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ifoodonation.config.JwtTokenUtil;
import com.fiap.ifoodonation.dao.JwtRequest;
import com.fiap.ifoodonation.dao.JwtResponse;
import com.fiap.ifoodonation.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	
	@RequestMapping(value = "/auth/customer", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenCustomer(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername("customer_____" + authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails, "customer");
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/auth/institution", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenInstitution(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername("institution_____" + authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails, "institution");
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/auth/operator", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenOperator(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername("operator_____" + authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails, "operator");
		return ResponseEntity.ok(new JwtResponse(token));
	}
}