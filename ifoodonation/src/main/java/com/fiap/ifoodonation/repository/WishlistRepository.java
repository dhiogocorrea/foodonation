package com.fiap.ifoodonation.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiap.ifoodonation.dao.Institution;
import com.fiap.ifoodonation.dao.Wishlist;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {	
	Iterable<Wishlist> getByInstitution(Institution institution);
}
