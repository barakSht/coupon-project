package com.barak.coupons.logic;

import java.util.List;

import com.barak.coupons.beans.Company;
import com.barak.coupons.beans.Customer;
import com.barak.coupons.dao.CompaniesDAO;
import com.barak.coupons.dao.CompaniesDBDAO;
import com.barak.coupons.dao.CouponDBDAO;
import com.barak.coupons.dao.CustomersDBDAO;
import com.barak.coupons.exception.CouponException;


public class AdminFacade extends ClientFacade {

	
	
	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String Email, String Password) {
		String currectEmail = "admin@admin";
		String currectPassword = "admin";
		if (Email == currectEmail && Password == currectPassword) {
			System.out.println("Admin login successfully");
			return true;
		}
		System.out.println("Admin NOT login successfully");
		return false;
	}
	
	public void addCompany (Company company) throws CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		 try {
			 boolean companyNameExists = companiesDBDAO.isCompanyNameExists(company.getName());
			 boolean companyEmailExists = companiesDBDAO.isCompanyEmailExists(company.getEmail());
			 if (companyNameExists) {
				 System.out.println("company Name Exists, please change name");
				 return;
			 }
			 if (companyEmailExists) {
				 System.out.println("company Email Exists, please change email");
				 return;
			 }
			 companiesDBDAO.addCompany(company);
			 System.out.println("company add successfully");
		} catch (CouponException e1) {
			throw new CouponException("Company not added " ,e1);
		}
	}
	
	public void updateCompany(Company company) throws CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		companiesDBDAO.updateCompanyFoeAdminFacade(company);
		System.out.println("company update successfully");
	}
	
	public void deleteCompany(int companyId) throws CouponException {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		couponDBDAO.deleteFromCustomersVsCoupons(companyId);
		couponDBDAO.deleteCouponForeAdminFacade(companyId);
		companiesDBDAO.deleteCompany(companyId);
		System.out.println("company delete successfully");
		
	}
	
	
	public List<Company> getAllCompanies() throws CouponException {
		CompaniesDBDAO  companiesDBDAO = new CompaniesDBDAO();
		return companiesDBDAO.getAllCompanies();
		
	}
	public Company getOneCompany(int companyId) throws CouponException {
		CompaniesDAO  companiesDAO = new CompaniesDBDAO();
		return companiesDAO.getOneCompany(companyId);
	}
	
	public void addCustomer(Customer customer) throws CouponException   {
		CustomersDBDAO  customersDBDAO = new CustomersDBDAO();
		boolean customerEmailExists = customersDBDAO.isCustomerEmailExists(customer.getEmail());
		if (customerEmailExists) {
			 System.out.println("customer Email Exists, please change email");
			 return;
		 } 
		customersDBDAO.addCustomer(customer);
		System.out.println("customer add successfully");
	}
	
	public void updateCustomer(Customer customer) throws CouponException {
		CustomersDBDAO  customersDBDAO = new CustomersDBDAO();
		customersDBDAO.updateCustomer(customer);
		System.out.println("customer update successfully");
	}
	
	public void deleteCustomer(int customerId) throws CouponException {
		CustomersDBDAO  customersDBDAO = new CustomersDBDAO();
		customersDBDAO.deleteCustomerFromCustomersVSCoupons( customerId);
		System.out.println("customer delete successfully");
	}
	
	public List<Customer> getAllCustomers() throws CouponException{
		CustomersDBDAO  customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getAllCustomers();
	
	}
	
	public Customer getOneCustomer(int customerId) throws CouponException {
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();
		return customersDBDAO.getOneCustomer(customerId);
	}
}

