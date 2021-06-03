package app.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	Optional<Customer> findByEmail (String email);

	Optional<Customer> findByPassword(String password);
	
//	@Query ("from Customer where Id ==:customerId and category ==:category")
//	List <Coupon> findCouponsCategory( int customerId ,Category caterory);
}
