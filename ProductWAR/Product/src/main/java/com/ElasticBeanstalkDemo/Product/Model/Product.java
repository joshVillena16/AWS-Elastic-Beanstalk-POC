package com.ElasticBeanstalkDemo.Product.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="prod_id")
	private int prodId;
	
	@Column(name="prod_name")
	private String prodName;
	
	@Column(name="prod_desc")
	private String prodDesc;
	
	@Column(name="prod_price")
	private double prodPrice;
	
	@Column(name="prod_stock")
	private int prodStock;
	
	@Column(name="prod_image")
	private String prodImage;
}