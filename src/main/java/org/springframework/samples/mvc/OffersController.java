package org.springframework.samples.mvc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class OffersController {

	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/offers/coupons") @ResponseBody
	public List<Coupon> getAllCoupons(){
		return offersService.getAllCoupon();
	}
	
	@RequestMapping("/offers/discounts") @ResponseBody
	public List<Discount> getAllDiscounts(){
		return offersService.getAllDiscount();
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/offers/coupons")
	public void addCoupon(@Valid @RequestBody Coupon coupon) {
		offersService.addCoupon(coupon);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/offers/discounts")
	public void addDiscount(@Valid @RequestBody Discount discount) {
		offersService.addDiscount(discount);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/offers/coupons")
	public void updateCoupon(@Valid @RequestBody Coupon updatedCoupon) {
	Coupon coupon = offersService.getCoupon(updatedCoupon.getCouponCode());
	if(!coupon.getOfferName().equalsIgnoreCase(updatedCoupon.getOfferName()) || !coupon.getCouponDescription().equalsIgnoreCase(updatedCoupon.getCouponDescription()) || 
			!coupon.getEffecttiveDt().equals(updatedCoupon.getEffecttiveDt()) ||  !coupon.getExpiryDt().equals(updatedCoupon.getExpiryDt())||
			!coupon.getMaxUsages().equals(updatedCoupon.getMaxUsages()))
		offersService.addCoupon(updatedCoupon);
	else
		throw new HttpClientErrorException(HttpStatus.CONFLICT, "Duplicate Entry found");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value = "/offers/discounts")
	public void updateDiscount(@Valid @RequestBody Discount updatedDiscount) {
	Discount discount = offersService.getDiscount(updatedDiscount.getDiscountCode());
	if(!discount.getDiscountDescription().equalsIgnoreCase(updatedDiscount.getDiscountDescription())
	|| !discount.getDiscountpercentage().equals(updatedDiscount.getDiscountpercentage()) || !discount.getMinCartAmount().equals(updatedDiscount.getMinCartAmount())
	|| !discount.getOfferName().equals(updatedDiscount.getOfferName()) ||  !discount.getAllowedMaxDiscount().equals(updatedDiscount.getAllowedMaxDiscount()))
		offersService.addDiscount(updatedDiscount);
	else
		throw new HttpClientErrorException(HttpStatus.CONFLICT, "Duplicate Entry found");
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/offers/coupons")
	public void deleteCoupon(@Valid @RequestBody Coupon coupon) {
			offersService.deleteCoupon(coupon);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/offers/discounts")
	public void deleteDiscount(@Valid @RequestBody Discount discount) {
		offersService.deleteDiscount(discount);
	}
}