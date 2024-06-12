package com.example.model;

import java.sql.Blob;

public class OrderBean {
    private String orderId;
    private String email;
    private String name;
    private String address;
    private String item;
    private int quantity;
    private Blob paymentSlip;
    private String status;
    private String timestamp;

    private String total;

    // Constructor
    public OrderBean() {
    }

    public OrderBean(String orderId, String email, String name, String address, String item, int quantity, Blob paymentSlip, String status, String timestamp, String total) {
        this.orderId = orderId;
        this.email = email;
        this.name = name;
        this.address = address;
        this.item = item;
        this.quantity = quantity;
        this.paymentSlip = paymentSlip;
        this.status = status;
        this.timestamp = timestamp;
        this.total = total;
    }

    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Blob getPaymentSlip() {
        return paymentSlip;
    }

    public void setPaymentSlip(Blob paymentSlip) {
        this.paymentSlip = paymentSlip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
