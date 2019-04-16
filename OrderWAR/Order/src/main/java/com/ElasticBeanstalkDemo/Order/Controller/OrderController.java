package com.ElasticBeanstalkDemo.Order.Controller;

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

import com.ElasticBeanstalkDemo.Order.Model.Customer;
import com.ElasticBeanstalkDemo.Order.Model.Request;
import com.ElasticBeanstalkDemo.Order.Model.Transaction;
import com.ElasticBeanstalkDemo.Order.Service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	TransactionService transactionService;
	
	@GetMapping("/")
	@ResponseBody
	public List<Transaction> getTransactions(){
		return transactionService.getTransactions();
	}	
	
	@PostMapping("/add")
	@ResponseBody
	public Request addOrder(@RequestBody Request request) {
		
		System.out.println(request);
		Transaction transaction = request.getTransaction();
		Customer customer = request.getCustomer();
		
		Transaction newTransaction 
			= transactionService.addTransaction(transaction,customer);
		
		request.setTransaction(newTransaction);
		
		return request;
	}
	
	@DeleteMapping("/delete/{orderId}")
	public Boolean deleteOrder(@PathVariable("orderId") int orderId){
		return transactionService.deleteTransaction(orderId);
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Transaction updateOrder(@RequestBody Transaction transaction) {
		return transactionService.updateTransaction(transaction) ? transaction : null;
	}
		
	@DeleteMapping("/cancelOrder/{orderId}")
	public Boolean cancelOrder(@PathVariable("orderId") int orderId) {
		return transactionService.cancelOrder(orderId);
	}
	
	@PutMapping("/updateStatus/{orderId}/{status}")
	public Boolean updateStatus(@PathVariable("orderId") int orderId,
									@PathVariable("status") String status) {
		return transactionService.updateStatus(orderId, status).getPaymentStatus() != null;
	}
	
	
}
