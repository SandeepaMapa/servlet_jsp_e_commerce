package com.example.model;

import java.util.Base64;

public class Productt {
    private String productId;
    private String title;
    private String description;
    private byte[] productImage1;
    private byte[] productImage2;
    private byte[] productImage3;
    private String price;

    // Constructors
    public Productt() {
    }

    public Productt(String productId, String title, String description, byte[] productImage1, byte[] productImage2, byte[] productImage3, String price) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.price = price;
    }


    public Productt(String productId, String title, String description, String price) {
		super();
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	// Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public byte[] getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(byte[] productImage1) {
        this.productImage1 = productImage1;
    }

    public byte[] getProductImage2() {
        return productImage2;
    }

    public void setProductImage2(byte[] productImage2) {
        this.productImage2 = productImage2;
    }

    public byte[] getProductImage3() {
        return productImage3;
    }

    public void setProductImage3(byte[] productImage3) {
        this.productImage3 = productImage3;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // Method to convert productImage1 to Base64 string
    public String getProductImage1Base64() {
        return Base64.getEncoder().encodeToString(this.productImage1);
    }

    // Method to convert productImage2 to Base64 string
    public String getProductImage2Base64() {
        return Base64.getEncoder().encodeToString(this.productImage2);
    }

    public String getProductImage3Base64() {
        return Base64.getEncoder().encodeToString(this.productImage3);
    }
}
