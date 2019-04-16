package com.ElasticBeanstalkDemo.Product.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ElasticBeanstalkDemo.Product.Model.Product;
import com.ElasticBeanstalkDemo.Product.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	public List<Product> getAll() {
		log.info(prodRepo.findAll().toString());
		return prodRepo.findAll();
	}
	
	public Product getById(int id) {
		log.info(prodRepo.findById(id).toString());
		return prodRepo.findById(id).get();
	}
	
	public boolean deleteProduct(int prodId) { 
		
		boolean isDeleted = false;
		try {
		  if(prodRepo.findById(prodId).get() != null) { 		  
				 prodRepo.deleteById(prodId);
				 isDeleted = true;
				 log.info("Product Deleted");
		  }
		}catch(NoSuchElementException ex) {
			log.error(ex.getMessage());
		}
	  
		return isDeleted;
	}
	
	public boolean updateProduct(Product newProduct) { 
		
		boolean isSaved = false;
	  
		try {
		  if((newProduct != null) 
				  && (prodRepo.findById(newProduct.getProdId()).get() != null)) { 	
			  
				 Product product = prodRepo.findById(newProduct.getProdId()).get();
				 product.setProdName(newProduct.getProdName());
				 product.setProdDesc(newProduct.getProdDesc());
				 product.setProdPrice(newProduct.getProdPrice());
				 product.setProdStock(newProduct.getProdStock());
				 product.setProdImage(newProduct.getProdImage());
				 prodRepo.saveAndFlush(product);
				 
				 isSaved = true;
				 log.info("Product Updated");
		  }
		 }catch(NoSuchElementException ex ) {
			 log.info("Failed to Update " + ex.getMessage());
		 }
	  
		return isSaved;
	}
	
	public boolean saveProduct(Product product) { 
		
		boolean isSaved = false;
	  
		try {
		  if(product != null) { 		
			  prodRepo.save(product);
			  isSaved = true;
			  log.info("Product Added");
		  }
		}catch(NullPointerException ex) {
			log.error(ex.getMessage() + ". Product cannot be empty");
		}
	  
		return isSaved;
	}
}

