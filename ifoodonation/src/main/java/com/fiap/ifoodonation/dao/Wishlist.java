package com.fiap.ifoodonation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wishlist")
@SequenceGenerator(name = "WISHLIST_SEQ", sequenceName = "WISHLIST_SEQ", initialValue = 1, allocationSize = 1)
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISHLIST_SEQ")
	private long wishlistId;
	
	@OneToMany
	List<WishlistItem> wishlistItems;
	
	@ManyToOne
	Institution institution;
	
	private String name;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Picture picture;
	
	@Column(length=10485760)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	
	public Wishlist() {
	}
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public List<WishlistItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}

}