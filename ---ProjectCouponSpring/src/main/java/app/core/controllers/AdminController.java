package app.core.controllers;

import java.util.List;

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
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exception.CouponException;
import app.core.services.AdminService;
import app.core.session.Session;
import app.core.session.SessionContext;
import io.swagger.annotations.ResponseHeader;
@RestController
@RequestMapping("/admin")
public class AdminController extends ClientController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private SessionContext sessionContext;
	
	@GetMapping("/login")
	@Override
	public String login(String email, String password) throws CouponException {
		try {
			boolean b = adminService.login(email, password);

		if (b == false) {
			throw new CouponException( "login failed") ;
		}	
			Session session = sessionContext.createSession();
			session.setAttribute(session.token, adminService);
			return session.token;
		
	} catch (CouponException e) {
		throw new CouponException() ;
	}
}

	@PostMapping("/add company")
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				
			Company company2 = adminService.addCompany(company);
			if (company2 != null) {
				return ResponseEntity.ok().body(company2);
			} 
			
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("check your email or name and change them");
		} catch (CouponException e) {
			throw e;
		}
	}
	
	@GetMapping("/get company")
	public ResponseEntity<?> getCompany(@RequestHeader String token,@RequestParam Integer companyId) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			Company company = adminService.getOneCompany(companyId);
			if(company != null) {
				return ResponseEntity.ok(company);
			}
			
			}
			return ResponseEntity.badRequest().body("company not found");
			
		} catch (CouponException e) {
			throw e;
		}
	}
	
	@PutMapping("/update company")
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			Company company1 = adminService.updateCompany(company);
			if(company1 != null) {
				return ResponseEntity.ok(company1);
			}
			}
			return ResponseEntity.badRequest().body("company not update");
		} catch (Exception e) {
			throw new CouponException(e);
		}
	}
	
	@DeleteMapping("/delete company")
	public void deleteCompany(@RequestHeader String token, @RequestParam Integer companyId) throws CouponException {
		try {Session session = sessionContext.getSession(token);
		if (session != null) {
			adminService.deleteCompany(companyId);
		}
		} catch (CouponException e) {
			throw new CouponException("your company not delete!");
		}
	}

	@GetMapping("/getAllCompanies")
	public List<Company> getAllCompanies(@RequestHeader String token) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
				return adminService.getAllCompanies();
		}	
		} catch (Exception e) {
		}
		return null;
	}
	
	@PostMapping("/add customer")
	public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			Customer customer2 = adminService.addCustomer(customer);
			if (customer2 != null) {
				return ResponseEntity.ok().body(customer2);
			} 
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("check your email or name and change them");

		} catch (CouponException e) {
			throw e;
		}
	}
	
	@PutMapping("/update customer")
	public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			Customer customer1 = adminService.updateCustomer(customer);
			if(customer1 != null) {
				return ResponseEntity.ok(customer1);
			}
			}
			return ResponseEntity.badRequest().body("customer not update");
		} catch (Exception e) {
			throw new CouponException(e);
		}
	}
	
	@DeleteMapping("/delete_Customer")
	public void deleteCustomer(@RequestHeader String token, @RequestParam Integer customerId) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			adminService.deleteCustomer(customerId);
			}
		} catch (CouponException e) {
			throw new CouponException("your customer not delete!");
		}
	}
	
	@GetMapping("/getAllCustomer")
	public List<Customer> getAllCustomer(@RequestHeader String token) throws CouponException {
		try {
			Session session = sessionContext.getSession(token);
			if (session != null) {
			List<Customer> customers = adminService.getAllCustomer();
			return customers;
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "getAllCustomer Method failed");
		} catch (CouponException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
		}
	}
}
