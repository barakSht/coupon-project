package com.barak.coupons.dao;


import com.barak.coupons.beans.Coupon;
import com.barak.coupons.exception.CouponException;

public interface CouponDAO {
public void addCoupon(Coupon coupon) throws CouponException;
public void updateCoupon(Coupon coupon) throws CouponException;
public void deleteCoupon(int couponId) throws CouponException;
public Coupon getOneCoupon(int couponId) throws CouponException;
public void addCouponPurchase(int customerId, int couponId) throws CouponException;
public void deleteCouponPurchase(int customerId, int couponId) throws CouponException;
}
