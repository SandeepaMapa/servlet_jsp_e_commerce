<%@ page import="java.sql.*, java.util.*, com.example.model.Productt, com.example.dao.ProducttDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Style for the container holding product images */
        .product-images {
            display: flex; /* Display images in a row */
            justify-content: center; /* Center-align images */
            margin-bottom: 20px; /* Add some margin at the bottom */
        }

        /* Style for individual product images */
        .product-image {
            max-width: 150px; /* Limit the width of each image */
            margin-right: 10px; /* Add some space between images */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1>Product Details</h1>
    <%
        // Get productId from request parameter
        String productId = request.getParameter("productId");

        // Fetch product details using ProducttDAO
        ProducttDAO producttDAO = new ProducttDAO();
        Productt product = producttDAO.selectProduct(productId);

        if (product != null) {
    %>
    <!-- Container for product images -->
    <div class="product-images">
        <img src="data:image/jpeg;base64,<%= product.getProductImage1Base64() %>" class="product-image" alt="Product Image 1">
        <img src="data:image/jpeg;base64,<%= product.getProductImage2Base64() %>" class="product-image" alt="Product Image 2">
        <img src="data:image/jpeg;base64,<%= product.getProductImage3Base64() %>" class="product-image" alt="Product Image 3">
    </div>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title"><%= product.getTitle() %></h5>
            <p class="card-text"><%= product.getDescription() %></p>
            <p class="card-text">Price: $<%= product.getPrice() %></p>
            <form action="buyProduct" method="post">
                <input type="hidden" name="productId" value="<%= productId %>">
                <button type="submit" class="btn btn-primary">Buy Product</button>
            </form>
        </div>
    </div>
    <%
    } else {
    %>
    <p>Product not found.</p>
    <%
        }
    %>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
