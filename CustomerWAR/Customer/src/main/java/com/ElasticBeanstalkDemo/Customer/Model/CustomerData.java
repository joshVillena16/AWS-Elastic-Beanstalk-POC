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
@Table(name="CustomerData")
public class CustomerData {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerId")
    private int id;
	@Column(name = "email")
    private String email;
	@Column(name = "first_name")
    private String first_name;
	@Column(name = "last_name")
    private String last_name;
	@Column(name = "gender")
    private String gender;
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
    private int postal;
	

	
	
}
