package org.springframework.samples.mvc;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, String> {
	public List<Discount> findBydiscountCode(String discountCode);
	public List<Discount> findBydiscountpercentage(String discountpercentage);	
	public List<Discount> findByminCartAmount(Integer minCartAmount);
	public List<Discount> findBydiscountDescription(String discountDescription);
	public List<Discount> findByallowedMaxDiscount(String allowedMaxDiscount);
}
