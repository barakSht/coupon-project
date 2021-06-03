package com.barak.coupons.enums;

public enum UserType {
	ADMIN("Adimn"),
	CUSTOMER("Customer"),
	COMPANY("Company");
	
	private String name;
	
	UserType(String type){
		this.name = type;
	}
	
	public String getName() {
		return this.name;
	}
	
	
}
