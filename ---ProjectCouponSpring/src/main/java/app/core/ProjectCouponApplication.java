package app.core;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exception.CouponException;
import app.core.repository.CompanyRepository;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProjectCouponApplication {

	public static void main(String[] args) throws InterruptedException {
		 SpringApplication.run(ProjectCouponApplication.class, args);

		}

}
