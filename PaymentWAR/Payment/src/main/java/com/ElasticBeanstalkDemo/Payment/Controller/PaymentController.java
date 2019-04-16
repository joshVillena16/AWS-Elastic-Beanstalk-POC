package com.ElasticBeanstalkDemo.Payment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ElasticBeanstalkDemo.Payment.Model.Payment;
import com.ElasticBeanstalkDemo.Payment.Service.PaymentService;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/")
	public List<Payment> getPaymentList(){
		return paymentService.getPaymentList();
	}
	
	@GetMapping("/getByCustomerId/{orderId}")
	public Payment getByOrderId(@PathVariable("orderId") int orderId) {
		return paymentService.getPaymentByOrderId(orderId);
	}
	
	@GetMapping("/getById/{paymentId}")
	public Payment getById(@PathVariable("paymentId") int paymentId) {
		return paymentService.getPayment(paymentId);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Payment addPayment(@RequestBody Payment payment) {
		return paymentService.createPayment(payment) ? payment : null;
	}
	
	@PatchMapping("/updateStatus/{id}/{status}")
	@ResponseBody
	public Payment updateStatus(@PathVariable("id") int paymentId, String status) {	
		return paymentService.updatePaymentStatus(paymentId,status);
	}
	
	@PutMapping("/updateStatusByOrder/{id}/{status}")
	@ResponseBody	
	public Payment updateStatusByOrder(@PathVariable("id") int orderId, 
									   @PathVariable("status") String status) {	
		return paymentService.updatePaymentStatusByOrder(orderId,status);
	}
	
	@DeleteMapping("/deletePayment/{id}")
	public Boolean updateStatusOrder(@PathVariable("id") int id) {
		return paymentService.deletePayment(id);
	}
	
}