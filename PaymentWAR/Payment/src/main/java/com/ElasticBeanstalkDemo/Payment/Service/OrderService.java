package com.ElasticBeanstalkDemo.Payment.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ElasticBeanstalkDemo.Payment.Model.Payment;


@Service
public class OrderService {
	
	@Value("${ORDER_SERVICE_SERVER}")
	private final String IP = "";
	private String BASEPATH = "/order";
	
	public boolean updateOrderStatus(int orderId,String status) throws 
	NullPointerException{
		
		boolean isUpdated = false;
		
		new RestTemplate().put(IP + BASEPATH 
							+ "/updateStatus/" + orderId + "/" + status,
							Payment.class,Boolean.class);
		
		return isUpdated;
	}
	
	
}
