package com.ElasticBeanstalkDemo.Order.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ElasticBeanstalkDemo.Order.Model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
