package app.core.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	Optional<Coupon> findByTitle (String title);

	Optional<Coupon> findByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndCategory(int companyId, Category caterory);

	@Transactional
	@Query(value = "select * from coupon  where company_id =:companyId and category =:caterory", nativeQuery = true)
	List<Coupon> findByCompanyIdAndCategory(@Param("companyId") Integer companyId, @Param("caterory") int caterory);

	@Transactional
	@Query(value = "select * from coupon  where company_id =:companyId and price <:price", nativeQuery = true)
	List<Coupon> findByCompanyIdAndPrice(@Param ("companyId") Integer companyId, @Param ("price")double maxPrice);

}

