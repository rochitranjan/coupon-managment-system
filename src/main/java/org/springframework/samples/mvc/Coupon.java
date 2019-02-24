package org.springframework.samples.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Coupon {

	@Id @Size(min = 5, max = 9) @NotNull @Column(unique=true, updatable = false) 
	private String couponCode;
	@Size(min = 5, max = 25) @NotNull
	private String offerName;
	@FutureOrPresent @NotNull @JsonFormat(pattern="dd/MM/yyyy")
	private Date effecttiveDt;
	@Future @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy")  @JsonFormat(pattern="dd/MM/yyyy")
	private Date expiryDt;
	@Size(min = 5, max = 150)
	private String couponDescription;
	private Integer maxUsages;
	
	//Coupon() {}
	
	public Coupon(String couponCode, String offerName, Date effecttiveDt, Date expiryDt, String couponDescription,
			Integer maxUsages) {
	//	super();
		this.couponCode = couponCode;
		this.offerName = offerName;
		this.effecttiveDt = effecttiveDt;
		this.expiryDt = expiryDt;
		this.couponDescription = couponDescription;
		this.maxUsages = maxUsages;
	}
	
	public Coupon() {
		super();
	}

	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public Date getEffecttiveDt() {
		return effecttiveDt;
	}
	public void setEffecttiveDt(Date effecttiveDt) {
			this.effecttiveDt = effecttiveDt;
	}
	public Date getExpiryDt() {
			return expiryDt;
		
	}
	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public Integer getMaxUsages() {
		return maxUsages;
	}
	public void setMaxUsages(Integer maxUsages) {
		this.maxUsages = maxUsages;
	}
	
}
