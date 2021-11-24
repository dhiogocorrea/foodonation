package com.fiap.ifoodonation.dto;

public class AddressDto {
	
private String country;
	
	private String state;
	private String city;
	private String postalCode;
	private String street;
	private String streetNumber;
	private String complement;
	private boolean isPrincipal;

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

}
