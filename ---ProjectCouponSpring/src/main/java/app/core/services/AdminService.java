package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exception.CouponException;
import app.core.repository.CompanyRepository;
import app.core.repository.CustomerRepository;

@Service
@Transactional
public class AdminService implements ClientService {
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	public AdminService() {
		super();
	}
	

	
	
	public AdminService(@Autowired CompanyRepository companyRepo,@Autowired  CustomerRepository customerRepo) {
		super();
		this.companyRepo = companyRepo;
		this.customerRepo = customerRepo;
	}



	public boolean login(String Email, String Password) throws CouponException{ 
		String currectEmail = "admin@admin";
		String currectPassword = "admin";
		if (Email.equals(currectEmail)  && Password.equals(currectPassword)) {
			System.out.println("Admin login successfully");
			return true;
		}
		System.out.println("Admin NOT login successfully");
		return false;
	}
	
	public Company addCompany (Company company) throws CouponException {
		 System.out.println(companyRepo);
		 System.out.println(company);
		Optional<Company> companyNameExists = companyRepo.findByName(company.getName());
		Optional<Company> companyEmailExists = companyRepo.findByEmail(company.getEmail());
			 
			 if (companyNameExists.isPresent()) {
				 throw new CouponException("company Name Exists, please change name");
			 }
			 if (companyEmailExists.isPresent()) {
				 throw new CouponException("company Email Exists, please change email");
			 }
			 companyRepo.save(company);
			 return company;
	}
	
	public Company updateCompany(Company company)  throws CouponException {
		Optional<Company> findcompany = companyRepo.findById(company.getId());
		
		
			Company newCompany = new Company();
			newCompany = findcompany.get();
			newCompany.setEmail(company.getEmail());
			newCompany.setPassword(company.getPassword());
			System.out.println("company update successfully");
			return company;
		
	}
	
	public void deleteCompany(int companyId) throws CouponException {
		companyRepo.deleteById(companyId);
		System.out.println("company delete successfully");
		
	}
	
	
	public List<Company> getAllCompanies()  {
		return companyRepo.findAll();
	}
	
	
	
	public Customer addCustomer(Customer customer)  throws CouponException  {
		Optional<Customer> findCustomer = customerRepo.findByEmail(customer.getEmail());
		if (findCustomer.isPresent()) {
			throw new CouponException("customer Email Exists, please change email");
		 } 
		else {
			customerRepo.save(customer);
			//System.out.println("customer add successfully");
		}
		return customer;
	}
	
	public Customer updateCustomer(Customer customer) throws CouponException {
		Optional<Customer> findCustomer = customerRepo.findById(customer.getId());
		if (!findCustomer.isPresent()) {
			throw new CouponException("Customer not found");
			
		}
		else {
			Customer newCustomer = new Customer();
			newCustomer = findCustomer.get();
			newCustomer.setFirstName(customer.getFirstName());
			newCustomer.setLastName(customer.getLastName());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setPassword(customer.getPassword());
			//customerRepo.saveAndFlush(newCustomer);
			return newCustomer;
		}
	}
	
	public void deleteCustomer(Integer customerId) throws CouponException {
		customerRepo.deleteById(customerId);
		System.out.println("customer delete successfully");
	}
	
	public List<Customer> getAllCustomer() throws CouponException{
		List<Customer> c = customerRepo.findAll();
		System.out.println(c);
		return c;
	
	}
	
	public Customer getOneCustomer(Integer id) throws CouponException {
		Optional<Customer> opt = customerRepo.findById(id);
		System.out.println(opt);
		if (opt.isPresent()) {
			return opt.get();
		}

		throw new CouponException("customer not found: " + id);
	}
	
	public Company getOneCompany(Integer id) throws CouponException {
		Optional<Company> opt = companyRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("company not found: " + id);
	}
}

