package app.core.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exception.CouponException;
import app.core.repository.CompanyRepository;
import app.core.repository.CouponRepository;
import app.core.repository.CustomerRepository;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerService implements ClientService{
	
	private Integer customerId;
	
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CouponRepository couponRepo;
	
	
	public boolean login(String email, String password) throws CouponException  {
		try {
		Optional<Customer> optEmail = customerRepo.findByEmail(email);

		Optional<Customer> optpass = customerRepo.findByPassword(password);

		if (optEmail.isPresent() && optpass.isPresent()) {
			Customer customer1 = optEmail.get();
			Customer customer2 = optpass.get();
			if (customer1.getId() == customer2.getId()) {
				this.customerId = customer1.getId();
				System.out.println("customer login successfully");
				return true;
			} else {
				System.out.println("customer email or customer password are not match");
				return false;
			}
		} 
		} catch (Exception e) {
			throw new CouponException("Method login failed", e);
		}
		return false;
	}

	public Coupon purchaseCoupon (Coupon coupon) throws CouponException  {
		try {
			
		List<Coupon> isCouponPurchase = customerRepo.getOne(customerId).getCoupons();
		for (Coupon coupon2 : isCouponPurchase) {
			if (coupon.equals(coupon2)) {
				throw new CouponException("You have already purchased this coupon. purchaseCoupon Method - failed");
			}
		}
		
		if (coupon.getAmount() == 0) {
			throw new CouponException("The coupon sold out. purchaseCoupon Method - failed");
		}
		
		LocalDate now = LocalDate.now();
			if (now.isAfter(coupon.getEndDate())) {
				throw new CouponException("The coupon expired. purchaseCoupon Method - failed");
		}
		Customer customer = customerRepo.getOne(customerId);	
		customer.addCoupons(coupon);
		coupon.setAmount(coupon.getAmount() - 1);
		return coupon;
		} 
		catch (Exception e) {
			throw new CouponException("The coupon expired. purchaseCoupon Method - failed" ,e);
		}
	}
	
	public List<Coupon> getCustomerCoupons() throws CouponException  {
		try {
			List<Coupon> coupons = customerRepo.getOne(customerId).getCoupons();
			if (coupons.isEmpty()) {
				System.out.println("No coupons to the customer ");
				return null;
			}
			else {
				System.out.println("===============Company Coupons===============");
				for (Coupon coupon : coupons) {
					System.out.println(coupon);
				}
				System.out.println("=============================================");
				return coupons;
			}
		} catch (Exception e) {
			throw new CouponException("Method getCustomerCoupons failed", e);
		}
	}
	
	public List<Coupon> getCustomerCouponCategory(Category caterory) throws CouponException {
		try {
			List<Coupon> list = new ArrayList<Coupon>();
			List<Coupon> coupons = customerRepo.getOne(customerId).getCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getCategory().equals(caterory)) {
					list.add(coupon);
				}
			}
			System.out.println(list);
			return list;

		} catch (Exception e) {
			throw new CouponException("getCustomerCouponCategory methd failed", e);
		}
	}

	public List<Coupon> getCustomerCouponCategoryMaxPrice(Integer maxPrice) throws CouponException {
		try {
			List<Coupon> list = new ArrayList<Coupon>();
			List<Coupon> coupons = customerRepo.getOne(customerId).getCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getPrice() <= maxPrice) {
					list.add(coupon);
				}
			}
			System.out.println(list);
			return list;

		} catch (Exception e) {
			throw new CouponException("getCustomerCouponCategory methd failed", e);
		}
	}
	
	


	
	public Customer getCustomerDetails() throws CouponException {
		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("Customer not found: " + customerId);
	}
	
	
	
	public Coupon getOneCoupon(Integer id) throws CouponException {
		Optional<Coupon> opt = couponRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("coupon not found: " + id);
	}
	
}
