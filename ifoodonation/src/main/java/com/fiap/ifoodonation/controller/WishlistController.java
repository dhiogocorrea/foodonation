package com.fiap.ifoodonation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ifoodonation.dao.Wishlist;
import com.fiap.ifoodonation.dao.WishlistItem;
import com.fiap.ifoodonation.dto.WishlistDto;
import com.fiap.ifoodonation.dto.WishlistItemDto;
import com.fiap.ifoodonation.service.WishlistService;
import com.fiap.ifoodonation.service.JwtUserDetailsService.Role;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Wishlist> getAll() {
		return wishlistService.getAll();
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/institution/{institutionId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Wishlist> getByInstitution(@PathVariable long institutionId) {
		return wishlistService.getByInstitution(institutionId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/{wishlistId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Wishlist getOne(@PathVariable long wishlistId) {
		return wishlistService.getOne(wishlistId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Wishlist create(@RequestBody WishlistDto wishlistDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long id = Long.parseLong(r.getAuthority());
		return wishlistService.create(wishlistDto, id);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/{wishlistId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long wishlistId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		wishlistService.remove(wishlistId, institutionId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/{wishlistId}/item", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Iterable<WishlistItem> getWishlistItems(@PathVariable long wishlistId) {
		return wishlistService.getWishlistItems(wishlistId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/{wishlistId}/item", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public WishlistItem addItem(@PathVariable long wishlistId, @RequestBody WishlistItemDto wishlistItemDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		return wishlistService.addItem(wishlistId, wishlistItemDto, institutionId);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/wishlist/{wishlistId}/item/{wishlistItemId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable long wishlistId, @PathVariable long wishlistItemId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Role r = (Role)auth.getAuthorities().toArray()[0];
		long institutionId = Long.parseLong(r.getAuthority());
		wishlistService.removeItem(wishlistId, wishlistItemId, institutionId);
	}

}
