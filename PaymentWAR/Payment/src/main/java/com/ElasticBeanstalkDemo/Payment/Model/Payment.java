package com.ElasticBeanstalkDemo.Payment.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Payment")
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payment_id")
	private int paymentId;
	
	@Column(name="customer_id")
	private int orderId;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="payment_type")
	private String paymentType;
	
	@Column(name="payment_date")
	private String paymentDate;
	
	@Column(name="status")
	private String status;

}
