package com.isa.dto;

import com.isa.entity.Price;

public class PriceDTO {
	
	private Integer price;
	private Integer discount;
	
	
	public PriceDTO() {}
	
	public PriceDTO(Price price) {
		this.price = price.getPrice();
		this.discount = price.getDiscount();
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
		return "PriceDTO [price=" + price + ", discount=" + discount + "]";
	}
}
