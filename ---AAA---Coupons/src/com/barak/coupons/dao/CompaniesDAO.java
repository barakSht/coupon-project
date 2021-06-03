package com.barak.coupons.dao;


import com.barak.coupons.beans.Company;
import com.barak.coupons.exception.CouponException;

public interface CompaniesDAO {
public boolean isCompanyExists (String Email , String password) throws CouponException;
public void addCompany(Company company) throws CouponException;
public void updateCompany(Company company) throws CouponException;
public void deleteCompany(int companyId) throws CouponException;
public Company getOneCompany(int companyId) throws CouponException;

}
