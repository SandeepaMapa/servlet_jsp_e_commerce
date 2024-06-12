package com.example.model;

public class ProductRequest {
    private String id;
    private String title;
    private String description;
    private String price;
    private byte[] productImage1;
    private byte[] productImage2;
    private byte[] productImage3;

    // Constructor
    public ProductRequest(String id, String title, String description, String price, byte[] productImage1, byte[] productImage2, byte[] productImage3) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
