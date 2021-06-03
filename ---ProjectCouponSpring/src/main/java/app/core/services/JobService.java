package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.repository.CouponRepository;

@Service
@Transactional
public class JobService {

	@Autowired
	private CouponRepository repository;

	public void deleteCoupon(int couponId) {
		repository.deleteById(couponId);
	}

	public List<Coupon> findAllCoupons() {
		return repository.findAll();
	}

}
