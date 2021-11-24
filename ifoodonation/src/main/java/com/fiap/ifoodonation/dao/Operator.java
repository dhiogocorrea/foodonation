package com.fiap.ifoodonation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "operator")
@SequenceGenerator(name = "OPERATOR_SEQ", sequenceName = "OPERATOR_SEQ", initialValue = 1, allocationSize = 1)
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATOR_SEQ")
	private long operatorId;
	private String name;
	private String lastName;
	private String email;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String password;
	
	private String cpf;
	
	@OneToMany
	List<Order> orders;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	List<Picture> pictures;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	List<Picture> docPictures;
	
	private boolean active;

	private String pixKey;
	
	private String pixKeyType;


	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;

	public Operator() {}
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Picture> getDocPictures() {
		return docPictures;
	}

	public void setDocPictures(List<Picture> docPictures) {
		this.docPictures = docPictures;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPixKey() {
		return pixKey;
	}

	public void setPixKey(String pixKey) {
		this.pixKey = pixKey;
	}

	public String getPixKeyType() {
		return pixKeyType;
	}

	public void setPixKeyType(String pixKeyType) {
		this.pixKeyType = pixKeyType;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}	
}
