package com.isa.dto;

public class ClinicSearchDTO {
	
	private ClinicDTO clinic;
	private PriceDTO price;
	
	
	public ClinicSearchDTO() {}


	public ClinicDTO getClinic() {
		return clinic;
	}

	public void setClinic(ClinicDTO clinic) {
		this.clinic = clinic;
	}

	public PriceDTO getPrice() {
		return price;
	}

	public void setPrice(PriceDTO price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ClinicSearchDTO [clinic=" + clinic + ", price=" + price + "]";
	}	
}
