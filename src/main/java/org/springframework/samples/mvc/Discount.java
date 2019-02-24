package org.springframework.samples.mvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Discount {
	@Id @Size(min = 5, max = 9) @NotNull @Column(unique=true, updatable = false)
	private String discountCode;
	@Size(min = 5, max = 25) @NotNull
	private String offerName;
	@Min(0) @Max(100) 
	private Double discountpercentage;
	@NotNull
	private Integer minCartAmount;
	@NotNull
	private Integer allowedMaxDiscount;
	private String discountDescription;
	
	public Discount() {
		super();
	}
	
	
	public Discount(String discountCode, String offerName, Double discountpercentage, 
			Integer minCartAmount, Integer allowedMaxDiscount, String discountDescription) {
		//super();
		this.discountCode = discountCode;
		this.offerName = offerName;
		this.discountpercentage = discountpercentage;
		this.minCartAmount = minCartAmount;
		this.allowedMaxDiscount = allowedMaxDiscount;
		this.discountDescription = discountDescription;
	}
	
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public Double getDiscountpercentage() {
		return discountpercentage;
	}
	public void setDiscountpercentage(Double discountpercentage) {
		this.discountpercentage = discountpercentage;
	}
	
	public Integer getMinCartAmount() {
		return minCartAmount;
	}
	public void setMinCartAmount(Integer minCartAmount) {
		this.minCartAmount = minCartAmount;
	}
	public Integer getAllowedMaxDiscount() {
		return allowedMaxDiscount;
	}
	public void setAllowedMaxDiscount(Integer allowedMaxDiscount) {
		this.allowedMaxDiscount = allowedMaxDiscount;
	}
	public String getDiscountDescription() {
		return discountDescription;
	}
	public void setDiscountDescription(String discountDescription) {
		this.discountDescription = discountDescription;
	}
	
	
}
