package com.barak.coupons.beans;

import java.util.ArrayList;
import java.util.List;

public class Company {
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<>();
	
	
	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Company(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Company() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}
	
}
//	CREATE TABLE `companies` (
//			  `id` int NOT NULL AUTO_INCREMENT,
//			  `name` varchar(255) DEFAULT NULL,
//			  `email` varchar(255) DEFAULT NULL,
//			  `password` varchar(255) DEFAULT NULL,
//			  PRIMARY KEY (`id`)
//			) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
