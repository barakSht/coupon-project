package com.barak.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.beans.Customer;
import com.barak.coupons.exception.CouponException;

import connectionPool.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {


	@Override
	public boolean isCustomerExists(String Email, String password) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select email, password from customers where email ='" + Email + "'and password ='" + password +"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				//System.out.println("Customer exists");
				return true;
			}
			else {
				return false; 
			}
		} catch (SQLException e) {
			throw new CouponException("Customer not exists " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void addCustomer(Customer customer) throws CouponException   {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into customers values(0,?, ?, ?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("addCustomer failed",e);
		}finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	@Override
	public void updateCustomer(Customer customer) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update customers set firstName=?, lastName=?, email=?, password=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("updateCustomer method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}


	@Override
	public void deleteCustomer(int customerId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from customers where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("deleteCustomer method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public Customer getOneCustomer(int customerId) throws  CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				customer.setId (rs.getInt(1));
				customer.setFirstName (rs.getString(2));
				customer.setLastName (rs.getString(3));
				customer.setEmail (rs.getString(4));
				customer.setPassword (rs.getString(5));
				return customer;
			}
		}
		catch (SQLException e) {
			throw new CouponException("getOneCustomer method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return null;
	}

	// helper method for AdminFacade, method addCustomer
	public boolean isCustomerEmailExists(String email) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select email from customers where email ='" + email + "'" ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				//System.out.println("customer email exists");
				return true;
			}
			else {
				//System.out.println("customer email not exists");
				return false; 
			}
		} catch (SQLException e) {
			throw new CouponException("iscustomerEmailExists method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	// helper method for CustomerFacade, method login
	public Customer getCustomerByEmailAndPassword(String email, String password) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers where email = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			Customer customer = new Customer();
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setId (rs.getInt(1));
				customer.setFirstName (rs.getString(2));
				customer.setLastName (rs.getString(3));
				customer.setEmail (rs.getString(4));
				customer.setPassword (rs.getString(5));
			//	System.out.println("customer found");
				return customer;
			}
		}
		catch (SQLException e) {
			throw new CouponException("getCustomerByEmailAndPassword method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return null;
	}

	// helper method for CustomerFacade, method isCouponPurchase
	public boolean isCouponPurchase(int customerId, int couponId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select * from customersvscoupons where customerId = ? and couponId = ?"   ;
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new CouponException("isCouponPurchase method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	public void deleteCustomerFromCustomersVSCoupons(int customerId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "Delete from customersvscoupons where customerId  = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("deleteCustomer method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		deleteCustomer( customerId);
	}

	public List<Customer> getAllCustomers() throws CouponException{
		List<Customer> customers = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from Customers";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId (rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setEmail (rs.getString(4));
				customer.setPassword (rs.getString(5));
				customers.add(customer);
				}
			}
		 catch (SQLException e) {
			 throw new CouponException("getAllCustomers method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return customers;
	}
	
	public List<Coupon> getCustomerCoupons(int customerId) throws  CouponException {
		List<Coupon> coupons = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons	join customersvscoupons on coupons.id = customersvscoupons.couponId where customersvscoupons.customerId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Coupon coupon = new Coupon();
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
				coupons.add(coupon);
				}
			}
		 catch (SQLException e) {
			 throw new CouponException("getCustomerCoupons method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return coupons;
	}

	public List<Coupon> getCustomerCoupons( int customerId , Category caterory) throws  CouponException {
	List<Coupon> coupons = new ArrayList<>();
	Connection con = null;
	try  {
		con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupons join customersvscoupons on coupons.id = customersvscoupons.couponId where customersvscoupons.customerId = ? and coupons.categoryId = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setInt(2, caterory.ordinal());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Coupon coupon = new Coupon();
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
			coupons.add(coupon);
			}
		//System.out.println(companies);
		}
	 catch (SQLException e) {
		 throw new CouponException("getCustomerCoupons method - failed " ,e);
		 		}
	finally {
		ConnectionPool.getInstance().restoreConnection(con);
	}
	return coupons;
}

	public List<Coupon> getCustomerCoupons( int customerId , double maxPrice) throws  CouponException {
	List<Coupon> coupons = new ArrayList<>();
	Connection con = null;
	try  {
		con = ConnectionPool.getInstance().getConnection();
		String sql = "select * from coupons	join customersvscoupons on coupons.id = customersvscoupons.couponId where customersvscoupons.customerId = ? and price < ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setDouble(2, maxPrice);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Coupon coupon = new Coupon();
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
			coupons.add(coupon);
			}
		//System.out.println(companies);
		}
	 catch (SQLException e) {
		 throw new CouponException("getCustomerCoupons method - failed " ,e);
		 		}
	finally {
		ConnectionPool.getInstance().restoreConnection(con);
	}
	return coupons;
}
	
}
