package com.ElasticBeanstalkDemo.Order.Model;

import lombok.Data;

@Data
public class Customer {
	
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String gender;
    private String street1;
    private String street2;
    private String city;
    private String province;
    private String country;
    private int postal;
	
}
