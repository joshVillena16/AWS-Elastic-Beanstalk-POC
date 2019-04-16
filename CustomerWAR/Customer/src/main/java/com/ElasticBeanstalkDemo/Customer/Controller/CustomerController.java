package com.ElasticBeanstalkDemo.Customer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ElasticBeanstalkDemo.Customer.Model.CustomerData;
import com.ElasticBeanstalkDemo.Customer.Service.CustomerService;


@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

	 @Autowired
	 private CustomerService customerService;

		
	
	
	@PostMapping("/add")
	@ResponseBody
	public CustomerData addCustomer(@RequestBody CustomerData customer) {
		return customerService.saveCustomer(customer) ? customer : null;
	}
	
	@GetMapping("/get")
	public List<CustomerData> getAllProducts() {
		return customerService.getAll();
	}
	
	@GetMapping("/get/{customerId}")
	public CustomerData customer(@PathVariable
			(name="customerId") Integer customerId){
		return customerService.getById(customerId);
	}
	
	
	@DeleteMapping("/delete/{customerId}")
	public boolean deleteCustomer(@PathVariable
			(name="customerId") Integer customerId) {
		return customerService.deleteCustomer(customerId);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public CustomerData updateCustomer(@RequestBody CustomerData customer) {
		return customerService.updateCustomer(customer) ? customer : null;
	}
	
	
}