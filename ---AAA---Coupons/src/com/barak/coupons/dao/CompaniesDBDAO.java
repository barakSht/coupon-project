package com.barak.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.barak.coupons.beans.Category;
import com.barak.coupons.beans.Company;
import com.barak.coupons.beans.Coupon;
import com.barak.coupons.exception.CouponException;

import connectionPool.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {


	@Override
	public boolean isCompanyExists(String Email, String password) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select email, password from companies where email ='" + Email + "'and password ='" + password +"'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
			else {
				return false; 
			}
		} catch (SQLException e) {
			throw new CouponException("Company not exists " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void addCompany(Company company) throws CouponException   {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into companies values(0, ?, ?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, company.getId());
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("addCompany method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void updateCompany(Company company) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update Companies set Name=?, Email=?, Password=? where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// initialize the PreparedStatement parameters
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.setInt(4, company.getId());
			// execute
			pstmt.executeUpdate();
			System.out.println("company update successfully");
		} catch (SQLException e) {
			throw new CouponException("updateCompany method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public void deleteCompany(int companyId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from Companies where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// initialize the PreparedStatement parameters
			pstmt.setInt(1, companyId);
			// execute
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException("deleteCompany method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}

	@Override
	public Company getOneCompany(int companyId) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			Company company = new Company();
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company.setId (rs.getInt(1));
				company.setName (rs.getString(2));
				company.setEmail (rs.getString(3));
				company.setPassword (rs.getString(4));
				//System.out.println("company found");
				return company;
			}
		}
		catch (SQLException e) {
			throw new CouponException("getOneCompany method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return null;
	}

	// helper method for AdminFacade, method addCompany
	public boolean isCompanyNameExists(String name) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select name from companies where name ='" + name + "'" ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
			else {
				return false; 
			}
		} catch (SQLException e) {
			throw new CouponException("isCompanyNameExists method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	// helper method for AdminFacade, method addCompany
	public boolean isCompanyEmailExists(String email) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql  = "select email from companies where email ='" + email + "'" ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
			else {
				return false; 
			}
		} catch (SQLException e) {
			throw new CouponException("isCompanyEmailExists method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	
	// helper method for CompanyFacade, method login
	public Company getCompanyByEmailAndPassword(String email, String password) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where email = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			Company company = new Company();
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company.setId (rs.getInt(1));
				company.setName (rs.getString(2));
				company.setEmail (rs.getString(3));
				company.setPassword (rs.getString(4));
				return company;
			}
		}
		catch (SQLException e) {
			throw new CouponException("getCompanyByEmailAndPassword method - failed " ,e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return null;
	}
	
	public void updateCompanyFoeAdminFacade(Company company) throws CouponException {
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update Companies set  email=? , password=? where id = ? and name = ?;";
			PreparedStatement pstmt = con.prepareStatement(sql);
			// initialize the PreparedStatement parameters
			pstmt.setString(1, company.getEmail());
			pstmt.setString(2, company.getPassword());
			pstmt.setInt(3, company.getId());
			pstmt.setString(4, company.getName());
			// execute
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponException (" ***** Error Message ***** update company failed. update only email and password",e);
		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
	}
	
	public List<Company> getAllCompanies() throws  CouponException {
		List<Company> companies = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setId (rs.getInt(1));
				company.setName (rs.getString(2));
				company.setEmail (rs.getString(3));
				company.setPassword (rs.getString(4));
				companies.add(company);
				}
			//System.out.println(companies);
			}
		 catch (SQLException e) {
			 throw new CouponException("getAllCompanies method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return companies;
	}
	
	public List<Coupon> getCompanyCoupons( int companyId) throws  CouponException {
		List<Coupon> coupons = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where CompanyId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
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
			 throw new CouponException("getCompanyCoupons method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return coupons;
	}
	
	public List<Coupon> getCompanyCoupons( int companyId , Category caterory) throws  CouponException {
		List<Coupon> coupons = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where CompanyId = ? and categoryId = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
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
			 throw new CouponException("getCompanyCoupons method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
		}
		return coupons;
	}
	
	public List<Coupon> getCompanyCoupons( int companyId , double maxPrice) throws  CouponException {
		List<Coupon> coupons = new ArrayList<>();
		Connection con = null;
		try  {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where CompanyId = ? and price < ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, companyId);
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
			}
		 catch (SQLException e) {
			 throw new CouponException("getCompanyCoupons method - failed " ,e);
			 		}
		finally {
			ConnectionPool.getInstance().restoreConnection(con);
			
		}
		return coupons;
	}
}
