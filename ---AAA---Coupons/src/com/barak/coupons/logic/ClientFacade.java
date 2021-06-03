package com.barak.coupons.logic;


import com.barak.coupons.dao.CompaniesDAO;
import com.barak.coupons.dao.CompaniesDBDAO;
import com.barak.coupons.dao.CouponDAO;
import com.barak.coupons.dao.CouponDBDAO;
import com.barak.coupons.dao.CustomersDAO;
import com.barak.coupons.dao.CustomersDBDAO;
import com.barak.coupons.exception.CouponException;

public abstract class ClientFacade {
	
	protected CompaniesDAO  companiesDAO = new CompaniesDBDAO();
	protected CustomersDAO  customersDAO = new CustomersDBDAO();
	protected CouponDAO  couponDAO = new CouponDBDAO();
	
	public abstract boolean login (String Email , String Password) throws CouponException;
}
