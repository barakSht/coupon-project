package com.barak.coupons.loginManager;


import com.barak.coupons.exception.CouponException;
import com.barak.coupons.logic.AdminFacade;
import com.barak.coupons.logic.ClientFacade;
import com.barak.coupons.logic.CompanyFacade;
import com.barak.coupons.logic.CustomerFacade;

public class LoginManager {
	// 1. singleton - the instance
		private static LoginManager instance = new LoginManager();

	// 2. singleton - the instance
	private LoginManager() {
		super();
	}

	// 3. singleton - the getter
	public static LoginManager getInstance() {
		return instance;
	}
	
	public ClientFacade login(String email , String password , ClientType clientType) throws CouponException {
		AdminFacade adminFacade = new AdminFacade();
		if(clientType == ClientType.Administrator) {
			if (adminFacade.login(email, password) ) {
				return adminFacade;
			}
		}
		
		CompanyFacade companyFacade = new CompanyFacade();
		if(clientType == ClientType.Company) {
			if (companyFacade.login(email, password) ) {
				return companyFacade;
			}
		}
		
		CustomerFacade customerFacade = new CustomerFacade();
		if(clientType == ClientType.Customer) {
			if (customerFacade.login(email, password)  ) {
				return customerFacade;
			}
		}
		
		return null;
		
	}
}

