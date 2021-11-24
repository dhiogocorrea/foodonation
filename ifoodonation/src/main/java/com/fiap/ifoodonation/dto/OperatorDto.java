package com.fiap.ifoodonation.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class OperatorDto {
	
	private String name;
	private String lastName;
	private String email;

	private String password;
	private String passwordConfirmation;
	
	private String cpf;
	
	String frontPicture;
	
	String selfieWithDocumentPicture;
	
	String frontDocumentPicture;
	String backDocumentPicture;

	private String pixKey;
	private String pixKeyType;

	public OperatorDto() {}

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

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFrontPicture() {
		return frontPicture;
	}

	public void setFrontPicture(String frontPicture) {
		this.frontPicture = frontPicture;
	}

	public String getFrontDocumentPicture() {
		return frontDocumentPicture;
	}

	public void setFrontDocumentPicture(String frontDocumentPicture) {
		this.frontDocumentPicture = frontDocumentPicture;
	}

	public String getSelfieWithDocumentPicture() {
		return selfieWithDocumentPicture;
	}

	public void setSelfieWithDocumentPicture(String selfieWithDocumentPicture) {
		this.selfieWithDocumentPicture = selfieWithDocumentPicture;
	}

	public String getBackDocumentPicture() {
		return backDocumentPicture;
	}

	public void setBackDocumentPicture(String backDocumentPicture) {
		this.backDocumentPicture = backDocumentPicture;
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
}
