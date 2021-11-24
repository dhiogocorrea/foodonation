package com.fiap.ifoodonation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.fiap.ifoodonation.enums.OrderStatus;

@Entity
@Table(name = "\"order\"")
@SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
	private long orderId;
	
	@OneToMany
	List<WishlistItem> wishlistItems;
	
	@ManyToOne
	Customer customer;
	
	@ManyToOne
	Operator operator;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Picture recepitDelivering;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Picture recepitConcluded;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Picture recepitPaymentToOperator;
	
	private float totalPrice;
	
	
	public Order() {
	}
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public List<WishlistItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Picture getRecepitDelivering() {
		return recepitDelivering;
	}

	public void setRecepitDelivering(Picture recepitDelivering) {
		this.recepitDelivering = recepitDelivering;
	}

	public Picture getRecepitConcluded() {
		return recepitConcluded;
	}

	public void setRecepitConcluded(Picture recepitConcluded) {
		this.recepitConcluded = recepitConcluded;
	}

	public Picture getRecepitPaymentToOperator() {
		return recepitPaymentToOperator;
	}

	public void setRecepitPaymentToOperator(Picture recepitPaymentToOperator) {
		this.recepitPaymentToOperator = recepitPaymentToOperator;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	
	
}