package com.ElasticBeanstalkDemo.Product.Controllers;

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

import com.ElasticBeanstalkDemo.Product.Model.Product;
import com.ElasticBeanstalkDemo.Product.Service.ProductService;



@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService prodService;
	
	@PostMapping("/add")
	@ResponseBody
	public Product addProduct(@RequestBody Product product) {
		return prodService.saveProduct(product) ? product : null;
	}
	
	@GetMapping("/get")
	public List<Product> getAllProducts() {
		return prodService.getAll();
	}
	
	@GetMapping("/getSignal")
	public String getVerificationIfLive() {
		return "I'm here";
	}
	
	@GetMapping("/get/{prodId}")
	public Product getProduct(@PathVariable
			(name="prodId") Integer prodId){
		return prodService.getById(prodId);
	}
	
	
	@DeleteMapping("/delete/{prodId}")
	public boolean deleteProduct(@PathVariable
			(name="prodId") Integer prodId) {
		return prodService.deleteProduct(prodId);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Product updateProduct(@RequestBody Product product) {
		return prodService.updateProduct(product) ? product : null;
	}
}