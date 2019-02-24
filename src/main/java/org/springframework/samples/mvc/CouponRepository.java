package org.springframework.samples.mvc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon, String> {
	    public List<Coupon> findDistinctCouponBycouponCode(String couponCode);
		public List<Coupon> findBycouponCode(String couponCode);
		public List<Coupon> findByofferName(String offerName);
		public List<Coupon> findByeffecttiveDt(String effecttiveDt);	
		public List<Coupon> findByexpiryDt(String expiryDt);
		public List<Coupon> findBycouponDescription(String couponDescription);
		public List<Coupon> findBymaxUsages(Integer maxUsages);
}
