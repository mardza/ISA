package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prices")
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "clinic_id", referencedColumnName = "id", unique = false, nullable = false)
	private Clinic clinic;
	
	@ManyToOne
	@JoinColumn(name = "appointmenttype_id", referencedColumnName = "id", unique = false, nullable = false)
	private AppointmentType appointmentType;
	
	@Column(name = "price", unique = false, nullable = false)
	private Integer price;
	
	@Column(name = "discount", unique = false, nullable = false)
	private Integer discount;
	
	@OneToOne(mappedBy = "price")
	private Appointment appointment;
	
	
	public Price() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
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
		return "Price [id=" + id + ", appointmentType=" + appointmentType + ", price=" + price
				+ ", discount=" + discount + "]";
	}	
}
