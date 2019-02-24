package org.springframework.samples.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class OffersService{

	@Autowired
	private DiscountRepository discountRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	private List<Coupon> listofcoupon = new ArrayList<>(Arrays.asList(
			new Coupon("couponcd1","Holi Offer",setDate("01/03/2019"), setDate("07/03/2019"), "Holi Bonanza", 5),
			new Coupon("couponcd2","Diwali Offer",setDate("05/03/2019"), setDate("15/03/2019"), "Diwali Bonanza", 5)
			));
	private List<Discount> listofDiscount = new ArrayList<>(Arrays.asList(
			new Discount("discCd1","Holi Offer",Double.parseDouble("50"), 1000 , 50, "Holi Discount"),
			new Discount("discCd2","Diwali Offer",Double.parseDouble("50"), 1000 , 50, "Diwali Discount")
			));
	private static Boolean couponflag = false;
	private static Boolean discountflag = false;
	
	private Date setDate(String date){
		try {
			DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRENCH);
			return dateFormatter.parse(date.split("/")[0]+"/"+date.split("/")[1]+"/"+date.split("/")[2]);
		}
		catch(ParseException ex) {
			ex.getMessage();
			return null;
		}
	}
	
	public List<Coupon> getAllCoupon(){
		if(!couponflag) {
		couponRepository.saveAll(listofcoupon);
		couponflag = true;}
		List<Coupon> couponlist = new ArrayList<>();
		couponRepository.findAll().iterator().forEachRemaining(couponlist::add);
		return couponlist;
	}
	
	public Coupon getCoupon(String couponCode){
		return getAllCoupon().stream().filter(t->t.getCouponCode().equals(couponCode)).findFirst().get();
	}
	
	public Discount getDiscount(String discountCode){
		return getAllDiscount().stream().filter(t->t.getDiscountCode().equals(discountCode)).findFirst().get();
	}
	
	public List<Discount> getAllDiscount(){
		if(!discountflag) {
		discountRepository.saveAll(listofDiscount);
		discountflag = true;}
		List<Discount> discountlist = new ArrayList<>();
		discountRepository.findAll().iterator().forEachRemaining(discountlist::add);
		return discountlist;
	}
	
	
	public void addCoupon(Coupon coupon) {
		Assert.isTrue(coupon.getExpiryDt().after(coupon.getEffecttiveDt()), "Expiry Date lesser than Effective Date");
		if(couponRepository.existsById(coupon.getCouponCode()))
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "Duplicate Entry found");
		else 
			couponRepository.save(coupon);
	}
	
	public void addDiscount(Discount discount) {
		if(discountRepository.existsById(discount.getDiscountCode()))
			throw new HttpClientErrorException(HttpStatus.CONFLICT, "Duplicate Entry found");
		else if(discount.getAllowedMaxDiscount().compareTo(discount.getMinCartAmount())>0)
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Allowed Maximum Discount greater than Minimum Cart Amount");
		else 
			discountRepository.save(discount);
	}
	
	public void deleteCoupon(Coupon coupon) {
		if(couponRepository.existsById(coupon.getCouponCode())) {
		List<Coupon> couponList = new ArrayList<>();
		couponRepository.findBycouponCode(coupon.getCouponCode()).iterator().forEachRemaining(couponList::add);
		couponRepository.deleteAll(couponList);}
		else
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Entry found. Please Check your input");
	}
	
	public void deleteDiscount(Discount discount) {
		if(discountRepository.existsById(discount.getDiscountCode())) {
		List<Discount> discountList = new ArrayList<>();
		discountRepository.findBydiscountCode(discount.getDiscountCode()).iterator().forEachRemaining(discountList::add);
		discountRepository.deleteAll(discountList);}
		else
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No Entry found. Please Check your input");
	}
}
