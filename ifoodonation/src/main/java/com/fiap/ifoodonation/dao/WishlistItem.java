package com.fiap.ifoodonation.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wishlist_item")
@SequenceGenerator(name = "WISHLIST_ITEM_SEQ", sequenceName = "WISHLIST_ITEM_SEQ", initialValue = 1, allocationSize = 1)
public class WishlistItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISHLIST_ITEM_SEQ")
	private long wishlistItemId;
	
	@ManyToOne  
	private Wishlist wishlist;
	
	@ManyToOne  
	private Order order;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	private String productName;
	
	private String productDescription;
	
	private String imageUrl;
	
	private float price;
	
	private boolean bought;
	
	@Column(nullable = true)
	private int quantity = 1;
	
	private String merchantName;
	
	
	public WishlistItem() {
	}
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getWishlistItemId() {
		return wishlistItemId;
	}

	public void setWishlistItemId(long wishlistItemId) {
		this.wishlistItemId = wishlistItemId;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

}