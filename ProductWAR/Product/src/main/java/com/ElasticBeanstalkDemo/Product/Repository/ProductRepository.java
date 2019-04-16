package com.ElasticBeanstalkDemo.Product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ElasticBeanstalkDemo.Product.Model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
