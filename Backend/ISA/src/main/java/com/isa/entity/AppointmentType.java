package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.dto.AppointmentTypeDTO;

@Entity
@Table(name = "appointmenttypes")
public class AppointmentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", unique = false, nullable = false, length = 65)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", unique = false, nullable = true)
	private Clinic clinic;
	
	@Column(name = "price", unique = false, nullable = false)
	private Integer price;
	
	@Column(name = "discount", unique = false, nullable = false)
	private Integer discount;
	
	
	public AppointmentType() {}
	
	public AppointmentType(AppointmentTypeDTO appointmentTypeDTO) {
		this.id = appointmentTypeDTO.getId();
		this.name = appointmentTypeDTO.getName();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "AppointmentType [id=" + id + ", name=" + name + ", clinic=" + clinic + ", price=" + price
				+ ", discount=" + discount + "]";
	}
}
