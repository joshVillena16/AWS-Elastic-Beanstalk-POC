package com.ElasticBeanstalkDemo.Order.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ElasticBeanstalkDemo.Order.Model.Product;

@Service
public class ProductService {

	@Value("${PRODUCT_SERVICE_SERVER}")
	private final String IP = "";
	private String BASEPATH = "/product";

	public Product getProduct(int prodId)
			throws NullPointerException {

		return new RestTemplate()
				.getForObject(IP + BASEPATH + 
						"/get/" + prodId, Product.class);	
		
	}
	
	public void updateProduct(Product product) 
			throws NullPointerException{

		new RestTemplate()
				.put(IP + BASEPATH + "/update", product,
				Boolean.class);
		
	}
	
	
}
