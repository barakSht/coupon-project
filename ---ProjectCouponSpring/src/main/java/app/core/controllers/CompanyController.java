package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.exception.CouponException;
import app.core.services.CompanyService;
import app.core.session.Session;
import app.core.session.SessionContext;


@RestController
@RequestMapping("/Company")
public class CompanyController extends ClientController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private SessionContext sessionContext;
	
	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = companyService.login(email, password);

		if (b == false) {
			throw new CouponException( "login failed") ;
		}	
			Session session = sessionContext.createSession();
			session.setAttribute(session.token, companyService);
			return session.token;
		
	} catch (CouponException e) {
		throw new CouponException() ;
	}
}
	
	@PostMapping("/addCoupon")
	public ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			return ResponseEntity.ok().body(companyService.addCoupon(coupon));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("addCoupon method failed");
		} catch (CouponException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			return ResponseEntity.ok().body(companyService.updateCoupon(coupon));
			}
			return ResponseEntity.badRequest().body("updateCoupon method failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("/deletCoupon")
	public ResponseEntity<String> deletCoupon(@RequestHeader String token, @RequestParam Integer couponId) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			companyService.deleteCoupon(couponId);
			return ResponseEntity.ok("coupon with id =" + couponId + " deleted");
			}
			return ResponseEntity.badRequest().body("the method deletCoupon failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body("the method deletCoupon failed");
		}
	}

	@GetMapping("/getCompanyCoupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token ) {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyCoupons());
		}
			return ResponseEntity.badRequest().body("the method getCompanyCoupons failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getAllCouponsOfCompanyCaterory")
	public ResponseEntity<?> getAllCouponsOfCompanyCaterory(@RequestHeader String token, Category caterory) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyCoupons(caterory));
		}
			return ResponseEntity.badRequest().body("the method getAllCouponsOfCompanyCaterory failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getCompanyCouponsMaxPrice")
	public ResponseEntity<?> getCompanyCouponsMaxPrice(@RequestHeader String token, double maxPrice) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyCoupons(maxPrice));
		}
			return ResponseEntity.badRequest().body("the method getCompanyCouponsMaxPrice failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getOneCoupon")
	public ResponseEntity<?> getOneCoupon(@RequestHeader String token, Integer id) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getOneCoupon(id));
		}
			return ResponseEntity.badRequest().body("the method getOneCoupon failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader String token) {

		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return ResponseEntity.ok(companyService.getCompanyDetails());
		}
			return ResponseEntity.badRequest().body("the method getCompanyDetails failed");
		} catch (CouponException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
