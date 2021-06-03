package com.barak.coupons.beans;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<>();
	
	
	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}


	public Customer() {
		super();
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Coupon> getCoupons() {
		return coupons;
	}


	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	public boolean addCoupons(Coupon coupon) {
		return coupons.add(coupon);
	}
	
	public boolean deleteCoupons(Coupon coupon) {
		return coupons.remove(coupon);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", coupons=" + coupons + "]";
	}
	
	
}
//CREATE TABLE `customers` (
//		  `id` int NOT NULL AUTO_INCREMENT,
//		  `firstName` varchar(255) DEFAULT NULL,
//		  `lastName` varchar(255) DEFAULT NULL,
//		  `email` varchar(255) DEFAULT NULL,
//		  `password` varchar(255) DEFAULT NULL,
//		  PRIMARY KEY (`id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

//---------------------------------------------------------------------------------------------

//CREATE TABLE `customersvscoupons` (
//		  `customerId` int NOT NULL,
//		  `couponId` int NOT NULL,
//		  PRIMARY KEY (`customerId`,`couponId`),
//		  KEY `couponId_idx` (`couponId`),
//		  CONSTRAINT `couponId` FOREIGN KEY (`couponId`) REFERENCES `coupons` (`id`),
//		  CONSTRAINT `customerId` FOREIGN KEY (`customerId`) REFERENCES `customers` (`id`)
//		) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci