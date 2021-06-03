package com.barak.coupons.logic;

import java.util.List;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Company;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.dao.CompaniesDBDAO;
import com.barak.coupons.dao.CouponDBDAO;
import com.barak.coupons.exception.CouponException;


public class CompanyFacade extends ClientFacade {

	private int companyId;
	
	public CompanyFacade() {
		super();
	}

	
	
	public int getCompanyId() {
		return companyId;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	@Override
	public boolean login(String Email, String Password) throws CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		try {
		Boolean companyExists = companiesDAO.isCompanyExists(Email, Password);
		if(companyExists) {
			Company company = companiesDBDAO.getCompanyByEmailAndPassword(Email, Password);
			this.companyId = company.getId();
				System.out.println("company login successfully");
				return true;
			}
			else {
				System.out.println("company email or company password are not match");
				return false;
			}
		}
		
		catch (CouponException e){
			throw new CouponException("login method - failed " ,e);
		}
	}
	
	public void addCoupon (Coupon coupon) throws CouponException {
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		 try {
			 boolean titleExists = couponDBDAO.isTitleExists(coupon.getTitle(),coupon.getCompanyId());
			 if (titleExists) {
				 return;
			 }
			 couponDBDAO.addCoupon(coupon);
			 System.out.println("coupon add successfully");
		} catch (CouponException e1) {
			throw new CouponException("addCoupon method - failed " ,e1);
		}
	}
	
	public void updateCoupon (Coupon coupon) throws CouponException {
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		couponDBDAO.updateCouponForCompanyFacade (coupon);
		System.out.println("coupon update successfully");
	}
	
//	
	public void deleteCoupon(int couponId) throws CouponException {
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		couponDBDAO.deleteFromCustomersVsCouponsForCompanyFacade(couponId); 
		couponDBDAO.deleteCoupon(couponId);
		System.out.println("coupon delete successfully");
	}
	
	
	public List<Coupon> getCompanyCoupons( int companyId) throws  CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		return companiesDBDAO.getCompanyCoupons(companyId);
			}
	
	public List<Coupon> getCompanyCoupons( int companyId , Category caterory) throws  CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		return companiesDBDAO.getCompanyCoupons(companyId , caterory);
	}
	
	public List<Coupon> getCompanyCoupons( int companyId , double maxPrice) throws  CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		return companiesDBDAO.getCompanyCoupons(companyId, maxPrice);
	}
	
	public Company getCompanyDetails() throws  CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		return companiesDBDAO.getOneCompany(this.companyId);
		  
	}
}

