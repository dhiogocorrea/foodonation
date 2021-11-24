package com.fiap.ifoodonation.service;

import com.fiap.ifoodonation.dao.Wishlist;
import com.fiap.ifoodonation.dao.WishlistItem;
import com.fiap.ifoodonation.dto.WishlistDto;
import com.fiap.ifoodonation.dto.WishlistItemDto;

public interface WishlistService {

	Iterable<Wishlist> getAll();
	Wishlist getOne(long wishlistId);
	Iterable<Wishlist> getByInstitution(long institutionId);
	Wishlist create(WishlistDto wishlist, long institutionId);
	void remove(long wishlistId, long institutionId);
	
	Iterable<WishlistItem> getWishlistItems(long wishlistId);
	WishlistItem addItem(long wishlistId, WishlistItemDto wishlistItemDto, long institutionId);
	void removeItem(long wishlistId, long wishlistItemId, long institutionId);
}
