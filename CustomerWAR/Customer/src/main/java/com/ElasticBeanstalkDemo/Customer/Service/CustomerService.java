package com.ElasticBeanstalkDemo.Customer.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ElasticBeanstalkDemo.Customer.Model.CustomerData;
import com.ElasticBeanstalkDemo.Customer.Repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<CustomerData> getAll() {
		log.info(customerRepository.findAll().toString());
		return customerRepository.findAll();
	}
	
	public CustomerData getById(int id) {
		log.info(customerRepository.findById(id).toString());
		return customerRepository.findById(id).get();
	}
	
	public boolean deleteCustomer(int customerId) { 
		
		boolean isDeleted = false;
		try {
		  if(customerRepository.findById(customerId).get() != null) { 		  
			  customerRepository.deleteById(customerId);
				 isDeleted = true;
				 log.info("Customer Deleted");
		  }
		}catch(NoSuchElementException ex) {
			log.error(ex.getMessage());
		}
	  
		return isDeleted;
	}
	
	public boolean updateCustomer(CustomerData newcustomer) { 
		
		boolean isSaved = false;
	  
		try {
		  if((newcustomer != null) 
				  && (customerRepository.findById(newcustomer.getId()).get() != null)) { 	
			  
				 CustomerData customer = customerRepository.findById(newcustomer.getId()).get();
				 customer.setEmail(newcustomer.getEmail());
				 customer.setFirst_name(newcustomer.getFirst_name());
				 customer.setLast_name(newcustomer.getLast_name());
				 customer.setGender(newcustomer.getGender());
				 customer.setStreet1(newcustomer.getStreet1());
				 customer.setStreet2(newcustomer.getStreet2());
				 customer.setCity(newcustomer.getCity());
				 customer.setProvince(newcustomer.getProvince());
				 customer.setCountry(newcustomer.getCountry());
				 customer.setPostal(newcustomer.getPostal());
				 customerRepository.saveAndFlush(customer);
				 
				 isSaved = true;
				 log.info("Customer Updated");
		  }
		 }catch(NoSuchElementException ex ) {
			 log.info("Failed to Update " + ex.getMessage());
		 }
	  
		return isSaved;
	}
	
	public boolean saveCustomer(CustomerData customer) { 
		
		boolean isSaved = false;
	  
		try {
		  if(customer != null) { 		
			  customerRepository.save(customer);
			  isSaved = true;
			  log.info("Customer Added");
		  }
		}catch(NullPointerException ex) {
			log.error(ex.getMessage() + ". customer cannot be empty");
		}
	  
		return isSaved;
	}
}