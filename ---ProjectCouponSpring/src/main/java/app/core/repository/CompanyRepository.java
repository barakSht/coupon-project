package app.core.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.core.entities.Company;
import app.core.entities.Coupon;


public interface CompanyRepository extends JpaRepository<Company, Integer>{

	
	Company findByEmailAndPassword(String email, String password);
	
	Optional<Company> findByName (String name);
	
	Optional<Company> findByEmail (String email);
	
	Optional<Company> findByPassword (String password);

	
	
	
}
