package com.barak.coupons.logic;

import java.time.LocalDate;
import java.util.List;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.beans.Customer;
import com.barak.coupons.dao.CouponDBDAO;
import com.barak.coupons.dao.CustomersDBDAO;
import com.barak.coupons.exception.CouponException;


public class CustomerFacade extends ClientFacade {

	private int customerId;
	
	public CustomerFacade() {
		super();
	}

	
	
	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	@Override
	public boolean login(String Email, String Password) throws CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		try {
		Boolean customerExists = customersDBDAO.isCustomerExists(Email, Password);
		if(customerExists) {
			Customer customer = customersDBDAO.getCustomerByEmailAndPassword(Email, Password);
			this.customerId = customer.getId();
				System.out.println("customer login successfully");
				return true;
			}
			else {
				System.out.println("customer email or customer password are not match");
				return false;
			}
		}
		catch (CouponException e){
			throw new CouponException("login method - failed " ,e);
		}
	}
	
	public void purchaseCoupon (Coupon coupon) throws CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		boolean isPurchase = customersDBDAO.isCouponPurchase(customerId, coupon.getId());
		if (isPurchase) {
			System.out.println("coupon Already purchased by this client ");
			return;
		}
		
		if (coupon.getAmount()==0) {
			System.out.println("coupon amount is: 0");
			return;
		}
		
		LocalDate now = LocalDate.now();
			if (now.isAfter(coupon.getEndDate())) {
			System.out.println("The coupon end date allready pass");
			return;
		}
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		couponDBDAO.addCouponPurchase(customerId, coupon.getId());
		coupon.setAmount(coupon.getAmount()-1);
		System.out.println(coupon.getAmount());
		System.out.println(coupon);
		couponDBDAO.updateCoupon(coupon);
	}

	public List<Coupon> getCustomerCoupons(int customerId) throws  CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getCustomerCoupons(customerId);
	}

	public List<Coupon> getCustomerCoupons( int customerId , Category caterory) throws  CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getCustomerCoupons(customerId, caterory);
}

	public List<Coupon> getCustomerCoupons( int customerId , double maxPrice) throws  CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getCustomerCoupons(customerId, maxPrice);
}
	
	public Customer getCustomerDetails() throws  CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getOneCustomer(customerId);
	}
	
	public Coupon getCoupon(int couponId) throws CouponException {
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		return couponDBDAO.getOneCoupon(couponId);
	}
	
}

