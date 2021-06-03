package app.core.job;

import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.core.entities.Coupon;
import app.core.repository.CouponRepository;
import app.core.services.JobService;
//@Component
public class CouponExpirationDailyJob {

	private Timer timer = new Timer();
	@Autowired
	private JobService jobService;

	private boolean isCouponExpired(Coupon coupon) {
		return LocalDate.now().isAfter(coupon.getEndDate());
	}

	@PostConstruct
	public void init() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				System.out.println("DailyJob");
				List<Coupon> coupons = jobService.findAllCoupons();
				for (Coupon coupon : coupons) {
					if (isCouponExpired(coupon)) {
						jobService.deleteCoupon(coupon.getId());
					}
				}

			}
		};
		timer.scheduleAtFixedRate(task, 30, 10_000);
	}

	@PreDestroy
	public void destroy() {
		timer.cancel();
		System.out.println("timer for expired coupon removal canceled");
	}

}
