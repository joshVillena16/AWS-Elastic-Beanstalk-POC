package com.ElasticBeanstalkDemo.Payment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ElasticBeanstalkDemo.Payment.Model.Payment;


public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	
	public Payment findByOrderId(int orderId);

}
