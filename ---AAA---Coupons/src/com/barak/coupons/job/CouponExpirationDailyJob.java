package com.barak.coupons.job;


import com.barak.coupons.dao.CouponDAO;
import com.barak.coupons.dao.JobDBDAO;
import com.barak.coupons.exception.CouponException;

import connectionPool.ConnectionPool;

public class CouponExpirationDailyJob implements Runnable {
	Thread worker;
	//private CouponDAO couponDao;
	private boolean quit;

	public CouponExpirationDailyJob(CouponDAO couponDao, boolean quit) {
		super();
	//	this.couponDao = couponDao;
		this.quit = quit;
	}

	public CouponExpirationDailyJob() {
		super();
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		ConnectionPool con = null;
		try {
			con = ConnectionPool.getInstance();
			con.closeAllConnections();
		} catch (CouponException e) {
			e.printStackTrace();
		}
		setQuit(true);
	}

	@Override
	public void run() {

		while (!this.quit) {
			try {
				JobDBDAO jobDBDAO = new JobDBDAO();
				jobDBDAO.job();
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

}
