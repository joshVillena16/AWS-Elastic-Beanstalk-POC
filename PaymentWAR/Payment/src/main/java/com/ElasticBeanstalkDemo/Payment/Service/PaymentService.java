package com.ElasticBeanstalkDemo.Payment.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ElasticBeanstalkDemo.Payment.Model.Payment;
import com.ElasticBeanstalkDemo.Payment.Repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	OrderService orderService;
	
	public List<Payment> getPaymentList(){
		return paymentRepo.findAll();
	}
	
	public Payment getPaymentByOrderId(int orderId) {	
		return paymentRepo.findByOrderId(orderId);
	}
	
	public Payment getPayment(int paymentId) {
		return paymentRepo.findById(paymentId).get();
	}
	
	public Boolean createPayment(Payment payment) {
		
		boolean isSaved = false;
		
		try {
			orderService.updateOrderStatus(payment.getOrderId(), payment.getStatus());
			paymentRepo.saveAndFlush(payment);
			isSaved = true;
		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		
		return isSaved;
	}
	
	public Payment updatePaymentStatus(int paymentId, String status) {

		Payment inventory = new Payment();
		
		try {
			inventory = paymentRepo.findById(paymentId).get();
			inventory.setStatus(status);
			paymentRepo.saveAndFlush(inventory);		
		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		
		return inventory;
	}
	
	public Payment updatePaymentStatusByOrder(int orderId, String status) {

		Payment inventory = new Payment();
		
		try {
			orderService.updateOrderStatus(orderId, status);
			inventory = paymentRepo.findByOrderId(orderId);
			paymentRepo.saveAndFlush(inventory);
			System.out.println("test3");
		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		
		return inventory;
	}
	
	public Boolean deletePayment(int paymentId) {
		
		boolean isDeleted = false;
		
		try {
			paymentRepo.deleteById(paymentId);
			isDeleted = true;
		}catch(Exception ex) {
			log.error(ex.getMessage());
		}
		
		return isDeleted;
	}
	
}

