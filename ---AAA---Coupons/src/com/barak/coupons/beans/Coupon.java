package com.barak.coupons.beans;

import java.time.LocalDate;

public class Coupon {
	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	
	

	public Coupon( int companyId, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, int amount, double price, String image) {
		super();
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}



	public Coupon() {
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCompanyId() {
		return companyId;
	}



	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}
}
//CREATE TABLE `coupons` (
//		  `id` int NOT NULL AUTO_INCREMENT,
//		  `companyId` int DEFAULT NULL,
//		  `categoryId` int DEFAULT NULL,
//		  `title` varchar(255) DEFAULT NULL,
//		  `description` varchar(255) DEFAULT NULL,
//		  `startDate` date DEFAULT NULL,
//		  `endDate` date DEFAULT NULL,
//		  `amount` int DEFAULT NULL,
//		  `price` double DEFAULT NULL,
//		  `image` varchar(255) DEFAULT NULL,
//		  PRIMARY KEY (`id`),
//		  KEY `companyId_idx` (`companyId`),
//		  KEY `categoryId_idx` (`categoryId`),
//		  CONSTRAINT `categoryId` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`),
//		  CONSTRAINT `companyId` FOREIGN KEY (`companyId`) REFERENCES `companies` (`id`)
//		) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=cp1250

