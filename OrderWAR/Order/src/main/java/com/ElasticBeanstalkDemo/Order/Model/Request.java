package com.ElasticBeanstalkDemo.Order.Model;


import lombok.Data;

@Data
public class Request {
	
	private Transaction transaction;
	private Customer customer;
}
