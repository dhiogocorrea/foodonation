package com.fiap.ifoodonation.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "address")
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1)
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
	private long addressId;
	
	@ManyToOne
	Institution institution;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String postalCode;
	
	private String street;
	
	private String streetNumber;
	
	private String complement;
	
	private boolean isPrincipal;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	public Address() {}


	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}


	public long getAddressId() {
		return addressId;
	}

	public Institution getInstitution() {
		return institution;
	}


	public void setInstitution(Institution institution) {
		this.institution = institution;
	}


	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	public String getComplement() {
		return complement;
	}


	public void setComplement(String complement) {
		this.complement = complement;
	}


	public boolean isPrincipal() {
		return isPrincipal;
	}


	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}


	public Date getCreatedIn() {
		return createdIn;
	}


	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}
	
}
