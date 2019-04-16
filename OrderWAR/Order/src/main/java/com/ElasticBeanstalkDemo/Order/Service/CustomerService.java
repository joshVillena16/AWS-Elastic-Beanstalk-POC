package com.ElasticBeanstalkDemo.Order.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ElasticBeanstalkDemo.Order.Model.Customer;


@Service
public class CustomerService {
	
	@Value("${CUSTOMER_SERVICE_SERVICE}")
	private final String IP = "";
	
	private String BASEPATH = "/customer";
	
	public Customer getCustomer(int customerId) throws
	NullPointerException {
		return new RestTemplate().getForObject(IP + BASEPATH +
								"/get" + customerId, Customer.class);
	}
	
	public Customer addCustomer(Customer customer) throws 
	NullPointerException{

		return new RestTemplate().postForObject(IP + BASEPATH +
				"/add", customer, Customer.class);
	}

}
