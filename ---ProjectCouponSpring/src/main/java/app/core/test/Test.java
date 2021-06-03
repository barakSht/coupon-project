package app.core.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.loginManager.LoginManager;
import app.core.repository.CompanyRepository;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

@Component
public class Test {
	
//	@Autowired
//	ApplicationContext ctx;
//	@Autowired
//	LoginManager loginManager;
//	
//	@Autowired
//	AdminService adminService;
//	@Autowired
//	CompanyService companyService;
//	@Autowired
//	CustomerService customerService;
	
	
//	CompanyRepository companyRepo = ctx.getBean(CompanyRepository.class);
//	System.out.println(ctx);
//	System.out.println(companyRepo);

//	Company company1 = new Company(0, "google", "google@com", "55");
//	Company company2 = new Company(6, "elal", "elal@com", "99");
//	Company company3 = new Company(6, "elal", "aaaelal@com", "99");
//
//	companyRepo.save(company1);
//	companyRepo.save(company2);

//	companyRepo.deleteById(2);
//	Company company3= companyRepo.findByEmailAndPassword("google@com", "ggg");
//	System.out.println(company);

	// update

//	company.setPassword("ggg");
//	companyRepo.saveAndFlush(company);

	// delete

//	companyRepo.delete(company);

	// get all companies

//	List <Company> companies = companyRepo.findAll();
//	System.out.println(companies);

	// get one company

//	Company company4 = companyRepo.getOne(3);
//	System.out.println(company4);

//	List<Company> names = companyRepo.findByName("google");
//	 System.out.println(names);
//*************************************************************************** AdminService
//	AdminService adminService = ctx.getBean(AdminService.class);
//	
//	//login
//	
//	System.out.println(adminService.login("admin@admin", "admin"));
//	
//	//addCompany
//	
//	adminService.addCompany(company2);
//
//	try {
//		Company company4 = adminService.getOneCompany(2);
//		System.out.println(company4);
//		company4.setEmail("######");
//		adminService.updateCompany(company4);
//		System.out.println(company4);
//		
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	Coupon coupon1 = new Coupon(0,Category.ELECTRICITY, "ccc", "ccc", LocalDate.parse("2015-02-01"),
//			LocalDate.parse("2023-11-06"), 200, 200, "ccc");
//	Coupon coupon2 = new Coupon(0,Category.FOOD, "mmm", "mmm", LocalDate.parse("2019-11-01"),
//			LocalDate.parse("2022-11-01"), 500, 100, "mmm");
//	
//	CompanyService companyService = ctx.getBean(CompanyService.class);
//	companyService.login("google@com", "55");
//	try {
//		companyService.addCoupon(coupon1);
//		companyService.addCoupon(coupon2);
//	} catch (CouponException e) {
//		e.printStackTrace();
//	}
//	
//	try {
//		Customer customer  =  companyService.getOneCustomer(1);
//		System.out.println(company);
//		
//		System.out.println(companyService.getCompanyCoupons(c.getId()));
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	adminService.deleteCompany(3);
//	
//	System.out.println(adminService.getAllCompanies());
//	
//	Customer customer1 = new Customer(0,"tif", "tuf", "tiftuf@shtrudel", "pai314");
//	adminService.addCustomer(customer1);
//	Customer customer2 = new Customer(0,"mo", "she", "aaa@al", "5678");
//	adminService.addCustomer(customer2);
//	Customer customer3 = new Customer(0,"zenika", "aster", "zzz@zzz", "1234");
//	adminService.addCustomer(customer3);
//	
//	Customer customer2;
//	try {
//		customer2 = adminService.getOneCustomer(1);
//		customer2.setEmail("@@@@@@!");
//		customer2.setPassword("hakuna matata!!!");
//		adminService.updateCustomer(customer2);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	adminService.deleteCustomer(1);
//	
//	adminService.getAllCustomers();
	
	
	//*************************************************************************** CompanyService	
	
//	System.out.println(companyService.login("######", "99"));
//	
//	try {
//		Coupon coupon3 = companyService.getOneCoupon(3);
//		coupon3.setAmount(777);
//		coupon3.setDescription("car ilon");
//		companyService.updateCoupon(coupon3);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//	companyService.deleteCoupon(3);
//	
//	try {
//		companyService.getCompanyCoupons();/*------------------ not work*/
//	} catch (CouponException e) {
//		e.printStackTrace();
//	} 
//	List <Coupon> CompanyCoupons = companyService.getCompanyCoupons();
//	System.out.println("===========CompanyCoupons===========");
//	for (Coupon coupon : CompanyCoupons) {
//		System.out.println(coupon);
//	}
//	System.out.println("====================================");
//	
//	List<Coupon> CompanyCoupons;
//	try {
//		CompanyCoupons = companyService.getCompanyCoupons(Category.FOOD);
//	} catch (CouponException e) {
//		e.printStackTrace();
//	}
//	try {
//		CompanyCoupons = companyService.getCompanyCoupons(90);
//	} catch (CouponException e) {
//		e.printStackTrace();
//	}
//	try {
//		System.out.println(companyService.getCompanyDetails());
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	
	
//*************************************************************************** CustomerService	
//	CustomerService customerService = ctx.getBean(CustomerService.class);
	
//	try {
//		System.out.println(customerService.login("tiftuf@shtrudel", "pai314"));
//	} catch (CouponException e) {
//		e.printStackTrace();
//	}
//	try {
//		customerService.getCustomerCoupons();
//		//System.out.println(allCoupon);
//	} catch (CouponException e) {
//		e.printStackTrace();
//	}
//	customerService.getCustomerCoupons(Category.ELECTRICITY);
	
//	System.out.println("Shutdown in 15 seconds");
//	Thread.sleep(15_000);
//	((ConfigurableApplicationContext) ctx).close();


}
