package com.ElasticBeanstalkDemo.Order.Model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import com.ElasticBeanstalkDemo.Order.Service.StringListConverter;

import lombok.Data;

@Entity
@Data
@Table(name="Transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="transaction_id")
	private int transactionId;

	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="transaction_bill")
	private double transactionBill;
	
	@Column(name="prod_id_list")
	@Convert(converter = StringListConverter.class)
	private List<String> prodIdList;
	
	@Column(name="prod_qty_list")
	@Convert(converter = StringListConverter.class)
	private List<String> prodQtyList;
		
	@Column(name="transaction_date")
	private String transactionDate;
	
	@Column(name="payment_method")
	private String transactionPaymentMethod;
	
	@Column(name="payment_status")
	private String paymentStatus;

}