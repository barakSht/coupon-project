package com.barak.coupons.test;


import java.time.LocalDate;
import java.util.List;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Company;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.beans.Customer;
import com.barak.coupons.dao.CouponDBDAO;
import com.barak.coupons.exception.CouponException;
import com.barak.coupons.job.CouponExpirationDailyJob;
import com.barak.coupons.logic.AdminFacade;
import com.barak.coupons.logic.CompanyFacade;
import com.barak.coupons.logic.CustomerFacade;
import com.barak.coupons.loginManager.ClientType;
import com.barak.coupons.loginManager.LoginManager;

public class Test {
	public void TestAll () {
		//job
		CouponExpirationDailyJob couponExpirationDailyJob = new CouponExpirationDailyJob();
		couponExpirationDailyJob.start();
		
		LoginManager loginManager = LoginManager.getInstance();
		
	//	 adminFacade Test
		try {
			AdminFacade adminFacade = (AdminFacade) loginManager.login("admin@admin", "admin", ClientType.Administrator);
			Company company = new Company("google", "google@gamil", "1234");
			adminFacade.addCompany(company);
			
			Company anotherCompany = new Company("facebook", "facebook@gamil", "mark.s");
			adminFacade.addCompany(anotherCompany);
			
			Company anotherCompany2 = new Company("whatsapp", "whatsapp@gamil", "ilom.mask");
			adminFacade.addCompany(anotherCompany2);
			
			Company companyUpdate = adminFacade.getOneCompany(117);
			companyUpdate.setEmail("change@");
			adminFacade.updateCompany(companyUpdate);
			
			List <Company> companies = adminFacade.getAllCompanies();
			System.out.println();
			System.out.println("******COMPANIES TABLE*******");
			for (Company company2 : companies) {
				System.out.println(company2);
			}
			System.out.println("**************************");
			System.out.println();
				
			Customer customer = new Customer("Haleli", "steiner","haleli@gmail", "abcd");
			adminFacade.addCustomer(customer);
			
			Customer customer2 = new Customer("Yair", "hatuka","Yair@gmail", "iwwa");
			adminFacade.addCustomer(customer2);
			
			Customer customer3 = adminFacade.getOneCustomer(15);
			customer3.setPassword("ilovejerusalem");
			adminFacade.updateCustomer(customer3);
			
			List <Customer> customers = adminFacade.getAllCustomers();
			System.out.println();
			System.out.println("******CUSTOMERS TABLE*******");
			for (Customer customer4 : customers) {
				System.out.println(customer4);
			}
			System.out.println("**************************");
			System.out.println();
			
			adminFacade.deleteCompany(118);
			
			adminFacade.deleteCustomer(16);
			
		
			
			System.out.println(adminFacade.getOneCustomer(15));
		} catch (CouponException e) {
			e.printStackTrace();
		} 
		
		//------------------------------------------------------------------------------------------
		// companyFacade Test
		
//		try {
//			Company company2 = new Company("osem", "osem@gmail" , "thatsGood");
//			AdminFacade adminFacade3 = new AdminFacade();
//			adminFacade3.addCompany(company2);
//			
//			CompanyFacade companyFacade = (CompanyFacade) loginManager.login("osem@gmail", "thatsGood", ClientType.Company);
//			
//			
//			CouponDBDAO couponDBDAO = new CouponDBDAO();
//			
//			Coupon coupon1 = new Coupon(117, Category.ELECTRICITY, "bamsba", "bagby", LocalDate.parse("2015-11-01"), LocalDate.parse("2025-11-01"), 500, 150, "yyy");
//			Coupon coupon3 = new Coupon(117, Category.ELECTRICITY, "aaskaa", "vvklv", LocalDate.parse("2015-11-01"), LocalDate.parse("2021-05-01"), 10, 30, "baxxxby");
//			Coupon coupon4 = new Coupon(117, Category.VACTION, "jdfghgfksjk", "mdfhmfm", LocalDate.parse("2015-11-01"), LocalDate.parse("2021-10-05"), 2000, 50, "qqq");
//			Coupon coupon5 = new Coupon(117, Category.VACTION, "jkasfsjk", "mfgasmfm", LocalDate.parse("2015-11-01"), LocalDate.parse("2018-10-10"), 2000, 50, "qqq");
//			Coupon coupon2 = new Coupon(117, Category.RESTAURANT, "bdfgggf", "aagggsa", LocalDate.parse("2015-11-01"), LocalDate.parse("2021-10-03"), 100, 1000, "zzz");
//			
//			companyFacade.addCoupon(coupon1);
//			companyFacade.addCoupon(coupon2);
//			companyFacade.addCoupon(coupon3);
//			companyFacade.addCoupon(coupon4);
//			companyFacade.addCoupon(coupon5);
//			
//			Coupon coupon6 =  couponDBDAO.getOneCoupon(35);
//			coupon6.setDescription("veryGood");
//			companyFacade.updateCoupon(coupon6);
//			
//			List <Coupon> coupons = companyFacade.getCompanyCoupons(companyFacade.getCompanyId());
//			System.out.println();
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : coupons) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//			
//			List <Coupon> coupons1 = companyFacade.getCompanyCoupons(companyFacade.getCompanyId(),Category.ELECTRICITY);
//			System.out.println();
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : coupons1) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//
//			
//			List <Coupon> coupons2 = companyFacade.getCompanyCoupons(companyFacade.getCompanyId(),100);
//			System.out.println();
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : coupons2) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//
//			companyFacade.deleteCoupon(44);
//			
//			System.out.println(companyFacade.getCompanyDetails());
//			
//			
//	} catch (CouponException e) {
//			e.printStackTrace();
//		}
		
		//------------------------------------------------------------------------------------------
				// CustomerFacade Test
//		try {
//			AdminFacade adminFacade = (AdminFacade) loginManager.login("admin@admin", "admin", ClientType.Administrator);
//			Customer customer = new Customer("aaaa", "ffff","123", "321");
//			adminFacade.addCustomer(customer);
//			
//			CustomerFacade customerFacade = (CustomerFacade) loginManager.login("123", "321",
//					ClientType.Customer);
//
//			CompanyFacade companyFacade = (CompanyFacade) loginManager.login("osem@gmail", "thatsGood",
//					ClientType.Company);
//
//			Coupon coupon1 = new Coupon(117, Category.ELECTRICITY, "bbaabqbaaasamba", "babqsbbaaaaby",
//					LocalDate.parse("2015-11-01"), LocalDate.parse("2022-05-01"), 500, 150, "yyy");
//			companyFacade.addCoupon(coupon1);
//			
//			Coupon couponPurchase1 = customerFacade.getCoupon(37);
//			customerFacade.purchaseCoupon(couponPurchase1);
//			Coupon couponPurchase2 = customerFacade.getCoupon(38);
//			customerFacade.purchaseCoupon(couponPurchase2);
//			Coupon couponPurchase3 = customerFacade.getCoupon(39);
//			customerFacade.purchaseCoupon(couponPurchase3);
//			Coupon couponPurchase4 = customerFacade.getCoupon(40);
//			customerFacade.purchaseCoupon(couponPurchase4);
//			
//			
//			List<Coupon> Coupons1 = customerFacade.getCustomerCoupons(customerFacade.getCustomerId());
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : Coupons1) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//			
//			List<Coupon> Coupons2 = customerFacade.getCustomerCoupons(customerFacade.getCustomerId(), Category.ELECTRICITY);
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : Coupons2) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//			
//			List<Coupon> Coupons3 = customerFacade.getCustomerCoupons(customerFacade.getCustomerId(), 100);
//			System.out.println("******COUPONS TABLE*******");
//			for (Coupon coupon : Coupons3) {
//				System.out.println(coupon);
//			}
//			System.out.println("**************************");
//			System.out.println();
//			
//			
//			System.out.println(customerFacade.getCustomerDetails());
//
//		} catch (
//
//		CouponException e) {
//			e.printStackTrace();
//		}
//		end job
		try {
			Thread.sleep(10000);
			couponExpirationDailyJob.stop();
			//Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("********** END PROGRAM **********");
	}
}
