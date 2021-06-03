package com.barak.coupons.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.beans.Customer;
import com.barak.coupons.exception.CouponException;

import connectionPool.ConnectionPool;

public class CouponDBDAO implements CouponDAO {




	@Override
	public void addCoupon(Coupon coupon) throws CouponException   {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into coupons values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(coupon);
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, coupon.getCategory().ordinal());
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(6, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("addCoupon method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update coupons set CompanyId=?, categoryId=?, Title=? , Description=? , StartDate=? , EndDate=? , Amount=? , Price=? , Image=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// initialize the PreparedStatement parameters
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, coupon.getCategory().ordinal());
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(6, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());
			pstmt.setInt(10, coupon.getId());
			// execute
			pstmt.executeUpdate();
			//System.out.println("coupon update successfully");
		} catch (SQLException e) {
			throw new CouponException("updateCoupon method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void deleteCoupon(int couponId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from coupons where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("deleteCoupon method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public Coupon getOneCoupon(int couponId) throws  CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			Coupon coupon = new Coupon ();
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				coupon.setId (rs.getInt(1));
				coupon.setCompanyId (rs.getInt(2));
				int ord = rs.getInt(3);
				for (Category c : Category.values()) {
					if (c.ordinal() == ord) {
						coupon.setCategory (c);
					}
				}
				coupon.setTitle (rs.getString(4));
				coupon.setDescription (rs.getString(5));
				coupon.setStartDate(rs.getDate(6).toLocalDate());
				coupon.setEndDate(rs.getDate(7).toLocalDate());
				coupon.setAmount (rs.getInt(8));
				coupon.setPrice (rs.getDouble(9));
				coupon.setImage (rs.getString(10));
				//System.out.println("coupon found");
			}
			return coupon;
		}
		catch (SQLException e) {
			throw new CouponException("deleteCoupon method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) throws CouponException {
		Coupon coupon = new Coupon();
		Customer customer = new Customer();
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();

		try {
			coupon = getOneCoupon(couponId);
			customer = customersDBDAO.getOneCustomer(customerId);
		} catch ( CouponException e) {
			e.printStackTrace();
		}
		customer.addCoupons(coupon);

		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into customersvscoupons values( ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, coupon.getId());
			pstmt.executeUpdate();
			System.out.println("coupon No. " + coupon.getId() + " buy by Customer No. " + customer.getId());
		} catch (SQLException e) {
			throw new CouponException("addCouponPurchase method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void deleteCouponPurchase(int customerId, int couponId) throws CouponException {
		Coupon coupon = new Coupon();
		Customer customer = new Customer();
		CustomersDBDAO customersDBDAO = new CustomersDBDAO();

		try {
			coupon = getOneCoupon(couponId);
			customer = customersDBDAO.getOneCustomer(customerId);
		} catch ( CouponException e) {
			throw new CouponException("deleteCouponPurchase method failed",e);
		}
		customer.deleteCoupons(coupon);
		//System.out.println(customer);

		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from customersvscoupons where customerId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// initialize the PreparedStatement parameters
			pstmt.setInt(1, customer.getId());
			// execute
			pstmt.executeUpdate();
			System.out.println("coupon No. " + coupon.getId() + " that buy by Customer No. " + customer.getId() + " has been deleted");
		} catch (SQLException e) {
			throw new CouponException("deleteCouponPurchase method failed",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	// helper method for CompanyFacade, method addCoupon
	public boolean isTitleExists(String title , int companyId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select title from coupons where companyId = '" + companyId + "'" + " and title = '" + title + "'"  ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs.next();

		} catch (SQLException e) {
			throw new CouponException("isTitleExists method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	// helper method for deleteCompany method
		public void deleteFromCustomersVsCoupons(int companyId) throws CouponException {
			Connection con = null;
			try  {
				con = ConnectionPool.getInstance().getConnection();
				String sql = "Delete from customersvscoupons where couponId in (Select id from coupons where companyId = ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// initialize the PreparedStatement parameters
				pstmt.setInt(1, companyId);
				// execute
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CouponException("deleteFromCustomersVsCoupons method - failed " ,e);
			}
			finally {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}

		// helper method for deleteCompany method
		public void deleteCouponForeAdminFacade(int companyId) throws CouponException {
			Connection con = null;
			try  {
				con = ConnectionPool.getInstance().getConnection();
				String sql = "delete from coupons where companyId = ? ";
				PreparedStatement pstmt = con.prepareStatement(sql);
				// initialize the PreparedStatement parameters
				pstmt.setInt(1, companyId);
				// execute
				pstmt.executeUpdate();
				//System.out.println("coupon delete successfully");
			} catch (SQLException e) {
				throw new CouponException("deleteCoupon method - failed " ,e);
			}
			finally {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		
		public void updateCouponForCompanyFacade (Coupon coupon) throws CouponException {
			Connection con = null;
			try  {
				con = ConnectionPool.getInstance().getConnection();
				String sql = "update coupons set  categoryId=?, Title=? , Description=? , StartDate=? , EndDate=? , Amount=? , Price=? , Image=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, coupon.getCategory().ordinal());
				pstmt.setString(2, coupon.getTitle());
				pstmt.setString(3, coupon.getDescription());
				pstmt.setDate(4, Date.valueOf(coupon.getStartDate()));
				pstmt.setDate(5, Date.valueOf(coupon.getEndDate()));
				pstmt.setInt(6, coupon.getAmount());
				pstmt.setDouble(7, coupon.getPrice());
				pstmt.setString(8, coupon.getImage());
				pstmt.setInt(9, coupon.getId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CouponException("updateCoupon method - failed " ,e);
			}
			finally {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		
		// helper method for deleteCompany method inCompany Facade
		public void deleteFromCustomersVsCouponsForCompanyFacade(int couponId) throws CouponException {
			Connection con = null;
			try  {
				con = ConnectionPool.getInstance().getConnection();
				String sql = "Delete from customersvscoupons where couponId = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, couponId);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new CouponException("deleteFromCustomersVsCoupons method - failed " ,e);
			}
			finally {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
}

