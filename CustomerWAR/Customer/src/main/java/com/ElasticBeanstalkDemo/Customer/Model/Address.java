package com.ElasticBeanstalkDemo.Customer.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Address")
public class Address {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerId")
	private int id;
	@Column(name = "street1")
    private String street1;
	@Column(name = "street2")
    private String street2;
	@Column(name = "city")
    private String city;
	@Column(name = "province")
    private String province;
	@Column(name = "country")
    private String country;
	@Column(name = "postal")
    private String postal;
	
}