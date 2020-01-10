package com.isa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "clinics")
public class Clinic {
	
	private Integer id;
	
	private String name;
	private String address;
	private Double latitude;
	private Double longitude;
	private String description;
	
	private Double ratingAverage;
	private Integer ratingWeight;
	
	
	
}
