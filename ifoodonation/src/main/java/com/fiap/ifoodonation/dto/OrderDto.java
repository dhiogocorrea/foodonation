package com.fiap.ifoodonation.dto;

import java.util.List;

public class OrderDto {

	private List<Long> wishListItemIds;

	public List<Long> getWishListItemIds() {
		return wishListItemIds;
	}

	public void setWishListItemIds(List<Long> wishListItemIds) {
		this.wishListItemIds = wishListItemIds;
	}
}
