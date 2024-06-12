package com.example.mapper;

import java.io.IOException;
import java.io.InputStream;

import com.example.model.ProductRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class ProductRequestMapper {

    public static ProductRequest mapToProductRequest(HttpServletRequest request) throws IOException, ServletException {
        String id = request.getParameter("productId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        Part productImage1Part = request.getPart("productImage1");
        Part productImage2Part = request.getPart("productImage2");
        Part productImage3Part = request.getPart("productImage3");

        byte[] productImage1 = convertPartToBytes(productImage1Part);
        byte[] productImage2 = convertPartToBytes(productImage2Part);
        byte[] productImage3 = convertPartToBytes(productImage3Part);

        return new ProductRequest(id, title, description, price, productImage1, productImage2, productImage3);
    }

    private static byte[] convertPartToBytes(Part part) throws IOException {
        if (part == null || part.getSize() == 0) {
            return null;
        }
        try (InputStream inputStream = part.getInputStream()) {
            return inputStream.readAllBytes();
        }
    }
}
