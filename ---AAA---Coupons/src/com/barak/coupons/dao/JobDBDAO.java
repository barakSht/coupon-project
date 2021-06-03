package com.barak.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.barak.coupons.exception.CouponException;
import com.barak.coupons.logic.CompanyFacade;

import connectionPool.ConnectionPool;

public class JobDBDAO {

	public void job() throws CouponException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "Select id from  coupons where endDate < date(now())";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				CompanyFacade companyFacade = new CompanyFacade();
				companyFacade.deleteCoupon(id);
			}
		} catch (Exception e) {
			throw new CouponException("returning connection failed", e);
		} finally {
			try {
				ConnectionPool.getInstance().restoreConnection(con);

//				Thread.sleep(3000);
			} catch (Exception e) {
				throw new CouponException("returning connection failed", e);
			}
		}
	}

}
