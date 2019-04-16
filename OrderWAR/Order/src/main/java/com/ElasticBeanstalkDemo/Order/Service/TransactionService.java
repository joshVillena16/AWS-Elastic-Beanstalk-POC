package com.ElasticBeanstalkDemo.Order.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ElasticBeanstalkDemo.Order.Model.Customer;
import com.ElasticBeanstalkDemo.Order.Model.Product;
import com.ElasticBeanstalkDemo.Order.Model.Transaction;
import com.ElasticBeanstalkDemo.Order.Repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {

	@Autowired
	TransactionRepository transRepo;

	@Autowired
	ProductService prodService;
	
	@Autowired
	CustomerService customerService;

	public Transaction addTransaction(Transaction transaction, Customer customer) throws
	NullPointerException{


		List<String> prodIdList = transaction.getProdIdList();
		List<String> prodIdQty = transaction.getProdQtyList();

		int counter = 0;
		
		if(!checkStocks(prodIdList,prodIdQty)) {
			transaction = saveCustomer(customer,transaction);
			transRepo.saveAndFlush(transaction);
			for (String prodId : prodIdList) {
				reduceProduct(Integer.valueOf(prodId),
							  Integer.valueOf(prodIdQty.get(counter)));
				counter++;
			}		
			log.info("order successfull");
		}

		
		return transaction;
	}

	public void reduceProduct(int prodId, int prodQty) {
		
		Product inventory = prodService
				.getProduct(prodId);

		inventory.setProdStock(inventory.getProdStock() -
							prodQty);
		
		prodService.updateProduct(inventory);
	}

	
	 public boolean checkStocks(List<String> prodIdList,List<String> prodIdQty)
	 throws NullPointerException {
	  
		  boolean isOutOfStock = false;
		  int counter = 0;
		  for(String prodId : prodIdList) { 
			  Product inventory =
					prodService.getProduct(Integer.valueOf(prodId));
			  
			  if(inventory != null &&
				 inventory.getProdStock() < Integer.valueOf(prodIdQty.get(counter))) { 			  
				  isOutOfStock = true;
				  log.warn("Product "+ inventory.getProdName() 
				  + " is out of stock by " + Integer.valueOf(prodIdQty.get(counter)));
				  break;			  
			  } 
			  counter++;
		  }
		  
		  return isOutOfStock; 
	  }
	 
	/*
	 * public boolean checkCustomer(int customerId) {
	 * 
	 * boolean customerExists = false;
	 * 
	 * if(customerService .getCustomer(customerId) .toString().length() > 0)
	 * customerExists = true;
	 * 
	 * return customerExists; }
	 */
	 
	 public Transaction saveCustomer(Customer customer, Transaction transaction) {
		 	 
		try { 
				 customer = customerService.addCustomer(customer);	
				 log.info("Customer Added");
				 transaction.setCustomerId(customer.getId());

		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		 
		 return transaction;
	 }
	 
	 public boolean deleteTransaction(int transactionId) {
		 
		 boolean isDeleted = false;
		 try {
			 transRepo.deleteById(transactionId);
			 isDeleted = true;
		 }catch(Exception ex) {
			log.error(ex.getMessage());
		 }
	 
		 return isDeleted; 
	 }
	 
	 public Boolean updateTransaction(Transaction transaction) {
		 
		 boolean isUpdated = false;
		 
		 try {
			 Transaction inventory = transRepo
					 .findById(transaction.getTransactionId()).get();
			 
			 inventory.setCustomerId(transaction.getCustomerId());
			 inventory.setTransactionBill(transaction.getTransactionBill());
			 inventory.setProdIdList(transaction.getProdIdList());
			 inventory.setProdQtyList(transaction.getProdQtyList());
			 inventory.setTransactionDate(transaction.getTransactionDate());
			 inventory.setTransactionPaymentMethod(transaction.getTransactionPaymentMethod());
			 transRepo.saveAndFlush(inventory);	 
			 isUpdated = true;
			 
		 }catch(Exception ex) {
			 log.error(ex.getMessage());
		 }
		 	 
		 return isUpdated;
	 }
	 
	 public boolean cancelOrder(int orderId) {
		 
		 boolean isCanceled = false;
		 
		 try{
			 Transaction order = transRepo.findById(orderId).get();
		 
			 int counter = 0;
			 
			 for(String prodId: order.getProdIdList()) {
				 Product product = prodService.getProduct(Integer.valueOf(prodId));
				 product.setProdStock(product.getProdStock() 
						 + Integer.valueOf(order.getProdQtyList().get(counter)));
				 prodService.updateProduct(product);
				 counter++;
			 }
			 transRepo.deleteById(order.getTransactionId());
			 isCanceled = true;
			 
		 }catch(Exception ex) {
			 log.error(ex.getMessage());
		 }
		 	 
		 return isCanceled;
	 }
	 
	 public List<Transaction> getTransactions(){
		 return transRepo.findAll();
	 }
	 
	 public Transaction updateStatus(int orderId, String status) {

		 Transaction inventory = transRepo.findById(orderId).get();
		 inventory.setPaymentStatus(status.toUpperCase());
		 transRepo.saveAndFlush(inventory);

		 return inventory;
	 }
	 

	 
	 
}

