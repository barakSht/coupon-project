package app.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exception.CouponException;
import app.core.repository.CompanyRepository;
import app.core.repository.CouponRepository;

@Service
@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyService implements ClientService{
	private Integer companyId;
	
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CouponRepository couponRepo;
	
	
	public boolean login(String email, String password) throws CouponException {
		Optional<Company> optEmail = companyRepo.findByEmail(email);

		Optional<Company> optpass = companyRepo.findByPassword(password);

		if (optEmail.isPresent() && optpass.isPresent()) {
			Company company1 = optEmail.get();
			Company company2 = optpass.get();
			if (company1.getId() == company2.getId()) {
				this.companyId = company1.getId();
				System.out.println("company login successfully");
				return true;
			} else {
				System.out.println("company email or company password are not match");
				return false;
			}

		} else {
			System.out.println("company email or company password are not match");
			return false;

		}
	}

	
	public Coupon addCoupon (Coupon coupon) throws CouponException    {
		Optional<Coupon> couponTitle = couponRepo.findByTitle(coupon.getTitle());
		if (couponTitle.isPresent()) {
			throw new CouponException("addCoupon method - failed ");
			
		}else {
			Company company = new Company();
			Optional<Company> opt = companyRepo.findById(companyId);
			if (opt.isPresent()) {
				 company = opt.get();
				 coupon.setCompanyId(company);
				 couponRepo.save(coupon);
				 System.out.println("coupon add successfully");
				 return coupon;
			}
			throw new CouponException("company not found: " + companyId);
		}
	}
	
	public Coupon updateCoupon (Coupon coupon) throws CouponException {
		Optional<Coupon> findcoupon = couponRepo.findById(coupon.getId());
		if (!findcoupon.isPresent()) {
			throw new CouponException("coupon not found");
			
		}
		else {
			Coupon newCoupon = findcoupon.get();
			
			newCoupon.setAmount(coupon.getAmount());
			newCoupon.setCompanyId(coupon.getCompanyId());
			newCoupon.setDescription(coupon.getDescription());
			newCoupon.setEndDate(coupon.getEndDate());
			newCoupon.setImage(coupon.getImage());
			newCoupon.setPrice(coupon.getPrice());
			newCoupon.setStartDate(coupon.getStartDate());
			newCoupon.setTitle(coupon.getTitle());
			
			couponRepo.saveAndFlush(newCoupon);
			System.out.println("coupon update successfully");
			return newCoupon;
		}
	}
	
	
	public void deleteCoupon(Integer couponId) throws CouponException {
		couponRepo.deleteById(couponId);
		System.out.println("coupon delete successfully");
	}
	
	
	public List<Coupon> getCompanyCoupons()  throws CouponException  {
		List<Coupon> list = companyRepo.getOne(companyId).getCoupons();
		System.out.println(list);
		if (list != null) {
			System.out.println("===============Company Coupons===============");
			for (Coupon coupon : list) {
				System.out.println(coupon);
			}
			System.out.println("=============================================");
			return list;
		}
		return null;

	}
	

	public List<Coupon> getCompanyCoupons(Category caterory) throws CouponException  {
		try {
			List<Coupon> list = couponRepo.findByCompanyIdAndCategory(companyId, caterory.ordinal());
			if (list != null) {
				System.out.println("===============Company Coupons===============");
				for (Coupon coupon : list) {
					System.out.println(coupon);
				}
				System.out.println("=============================================");
				return list;
			}else {
			return null;
			}
		} catch (Exception e) {
			throw new CouponException("getCompanyCoupons method failed",e);

		}
	}
	
	public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponException  {
		try {
			List<Coupon> list = couponRepo.findByCompanyIdAndPrice(companyId, maxPrice);
			if (list != null) {
				System.out.println("===============Company Coupons===============");
				for (Coupon coupon : list) {
					System.out.println(coupon);
				}
				System.out.println("=============================================");
				return list;
			}else {
			return null;
			}
		} catch (Exception e) {
			throw new CouponException("getCompanyCoupons method failed",e);

		}
	}
	
	
	public Company getCompanyDetails() throws  CouponException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("company not found: " + companyId);
	}
	
	public Coupon getOneCoupon(Integer id) throws  CouponException {
		Optional<Coupon> opt = couponRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CouponException("coupon not found: " + id);
	}
	
}
