package com.example.services;

import java.sql.SQLException;
import java.util.List;
import com.example.dao.ProducttDAO;
import com.example.model.Productt;
import com.example.utils.ValidationUtils;

public class ProductService {

    private ProducttDAO producttDAO;

    public ProductService() {
        this.producttDAO = new ProducttDAO();
    }

    public List<Productt> getAllProducts() {
        return producttDAO.selectAllProducts();
    }

    public Productt getProductById(String id) {
        return producttDAO.selectProduct(id);
    }

    public void addProduct(Productt product) throws SQLException {
        if (ValidationUtils.isValidProduct(product)) {
            producttDAO.insertProduct(product);
        } else {
            throw new IllegalArgumentException("Invalid product data");
        }
    }

    public void updateProduct(Productt product) throws SQLException {
        if (ValidationUtils.isValidProduct(product)) {
            producttDAO.updateProduct(product);
        } else {
            throw new IllegalArgumentException("Invalid product data");
        }
    }

    public void deleteProduct(String id) throws SQLException {
        producttDAO.deleteProduct(id);
    }
}
