package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Wishlist;
import com.fiap.ifoodonation.dao.WishlistItem;

public interface WishlistItemRepository extends CrudRepository<WishlistItem, Long> {
	Iterable<WishlistItem> getByWishlist(Wishlist wishlist);
}
