package app.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exception.CouponException;
import app.core.services.CustomerService;
import app.core.session.Session;
import app.core.session.SessionContext;
@RestController
@RequestMapping("/customer")
public class CustomerController extends ClientController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SessionContext sessionContext;
	
	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = customerService.login(email, password);

		if (b == false) {
			throw new CouponException( "login failed") ;
		}	
			Session session = sessionContext.createSession();
			session.setAttribute(session.token, customerService);
			return session.token;
		
	} catch (CouponException e) {
		throw new CouponException() ;
	}
}

	@PostMapping("/purchase_Coupon")
	public ResponseEntity<?> purchaseCoupon ( @RequestHeader String token, @RequestParam  Integer couponId) throws Exception {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			Coupon coupon2 = customerService.purchaseCoupon(customerService.getOneCoupon(couponId));
			if (coupon2 != null) {
				return ResponseEntity.ok().body(coupon2);
			}
			} 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The coupon expired. purchaseCoupon Method - failed");
		} catch (CouponException e) {
			throw e;
		}
	}
	
	@GetMapping("/get_Customer_Coupons")
	public ResponseEntity<?> getCustomerCoupons( @RequestHeader String token)  {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerCoupons();
				return ResponseEntity.ok(coupons);
		}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCoupons Method - failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCoupons Method - failed");
		}
		
	}
	@GetMapping("/get_Customer_Coupon_Category")
	public ResponseEntity<?> getCustomerCouponCategory( @RequestHeader String token, @RequestParam Category caterory)  {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerCouponCategory(caterory);
				return ResponseEntity.ok(coupons);
		}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCouponCategory Method - failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCouponCategory Method - failed");
		}
		
	}
	@GetMapping("/get_Customer_Coupon_Max_Price")
	public ResponseEntity<?> getCustomerCouponCategoryMaxPrice( @RequestHeader String token, Integer maxPrice)  {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				List<Coupon> coupons = customerService.getCustomerCouponCategoryMaxPrice(maxPrice);
				return ResponseEntity.ok(coupons);
		}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCouponCategoryMaxPrice Method - failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerCouponCategoryMaxPrice Method - failed");
		}
		
	}
	@GetMapping("/get_Customer_Details")
	public ResponseEntity<?> getCustomerDetails( @RequestHeader String token)  {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Customer customer = customerService.getCustomerDetails();
				return ResponseEntity.ok(customer);
		}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerDetails Method - failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getCustomerDetails Method - failed");
		}
	}
	
	@GetMapping("/get_One_Coupon")
	public ResponseEntity<?> getOneCoupon( @RequestHeader String token, Integer id)  {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				Coupon coupons = customerService.getOneCoupon(id);
				return ResponseEntity.ok(coupons);
		}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getOneCoupon Method - failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "getOneCoupon Method - failed");
		}
	}
}
