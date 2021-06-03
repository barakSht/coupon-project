package com.barak.coupons.dao;


import com.barak.coupons.beans.Customer;
import com.barak.coupons.exception.CouponException;

public interface CustomersDAO {
public boolean isCustomerExists (String Email , String password) throws CouponException;
public void addCustomer(Customer customer) throws CouponException;
public void updateCustomer(Customer customer) throws CouponException;
public void deleteCustomer(int customerId) throws CouponException;
public Customer getOneCustomer(int customerId) throws CouponException;

}
