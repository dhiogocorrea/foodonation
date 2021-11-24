package com.fiap.ifoodonation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Picture;
import com.fiap.ifoodonation.dao.Wishlist;
import com.fiap.ifoodonation.dao.WishlistItem;
import com.fiap.ifoodonation.dto.WishlistDto;
import com.fiap.ifoodonation.dto.WishlistItemDto;
import com.fiap.ifoodonation.repository.InstitutionRepository;
import com.fiap.ifoodonation.repository.WishlistItemRepository;
import com.fiap.ifoodonation.repository.WishlistRepository;
import com.fiap.ifoodonation.service.WishlistService;


@Service
public class WishlistServiceImpl implements WishlistService {
	
	private WishlistRepository wishlistRepository;
	private WishlistItemRepository wishlistItemRepository;
	
	private InstitutionRepository institutionRepository;
	
	@Autowired
	public WishlistServiceImpl(WishlistRepository wishlistRepository, InstitutionRepository institutionRepository,
			WishlistItemRepository wishlistItemRepository) {
		this.wishlistRepository = wishlistRepository;
		this.institutionRepository = institutionRepository;
		this.wishlistItemRepository = wishlistItemRepository;
	}

	@Override
	public Iterable<Wishlist> getAll() {
		return wishlistRepository.findAll();
	}

	@Override
	public Iterable<Wishlist> getByInstitution(long institutionId) {
		Institution institution = getInstitution(institutionId);
		return wishlistRepository.getByInstitution(institution);
	}
	
	@Override
	public Wishlist getOne(long wishlistId) {
		Wishlist wishlist =  wishlistRepository.findById(wishlistId).get();
		
		if (wishlist == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wishlist not found.");
		}
		
		return wishlist;
	}

	@Override
	public Wishlist create(WishlistDto wishlistDto, long institutionId) {
		Institution institution = getInstitution(institutionId);

		Wishlist wishlist = new Wishlist();
		wishlist.setName(wishlistDto.getName());
		wishlist.setDescription(wishlistDto.getDescription());
		wishlist.setPicture(new Picture(wishlistDto.getPicture()));
		wishlist.setInstitution(institution);

		wishlist = wishlistRepository.save(wishlist);
		return wishlist;
	}

	@Override
	public void remove(long wishlistId, long institutionId) {
		Institution institution = getInstitution(institutionId);
		Wishlist wishlist = getWishlistCheckingInstitutionOwner(wishlistId, institution);
		wishlistRepository.delete(wishlist);
	}
	
	@Override
	public Iterable<WishlistItem> getWishlistItems(long wishlistId) {
		Wishlist wishlist = getOne(wishlistId);
		return wishlistItemRepository.getByWishlist(wishlist);
	}

	@Override
	public WishlistItem addItem(long wishlistId, WishlistItemDto wishlistItemDto, long institutionId) {
		Institution institution = getInstitution(institutionId);
		Wishlist wishlist = getWishlistCheckingInstitutionOwner(wishlistId, institution);
		
		WishlistItem wishlistItem = new WishlistItem();
		wishlistItem.setProductName(wishlistItemDto.getProductName());
		wishlistItem.setProductDescription(wishlistItemDto.getProductDescription());
		wishlistItem.setImageUrl(wishlistItemDto.getImageUrl());
		wishlistItem.setPrice(wishlistItemDto.getPrice());
		
		wishlistItem.setWishlist(wishlist);		
		wishlistItemRepository.save(wishlistItem);
		
		
		return wishlistItem;
	}

	@Override
	public void removeItem(long wishlistId, long wishlistItemId, long institutionId) {
		Institution institution = getInstitution(institutionId);
		getWishlistCheckingInstitutionOwner(wishlistId, institution);
		
		wishlistItemRepository.deleteById(wishlistItemId);
	}
	
	private Institution getInstitution(long institutionId) {
		Optional<Institution> opt = institutionRepository.findById(institutionId);
		
		if (!opt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found.");
		}
		return opt.get();
	}
	
	private Wishlist getWishlistCheckingInstitutionOwner(long wishlistId, Institution institution) {
		Wishlist wishlist = getOne(wishlistId);
		
		if (wishlist.getInstitution().getInstitutionId() != institution.getInstitutionId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User cannot perform this operation.");
		}
		
		return wishlist;
	}

}
