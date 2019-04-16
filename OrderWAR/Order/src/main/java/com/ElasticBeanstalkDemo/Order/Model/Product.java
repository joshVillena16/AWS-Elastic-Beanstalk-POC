package com.ElasticBeanstalkDemo.Order.Model;


import lombok.Data;

@Data
public class Product {
	
	private int prodId;
	private Transaction transaction;
	private String prodName;
	private String prodDesc;
	private double prodPrice;
	private int prodStock;
	private String prodImage;
}
