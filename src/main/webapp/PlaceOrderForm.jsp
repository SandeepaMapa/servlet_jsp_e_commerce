<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.model.Productt, com.example.dao.ProducttDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function updateTotal() {
            const price = parseFloat(document.getElementById('price').value);
            const quantity = parseInt(document.getElementById('quantity').value);
            const total = price * quantity;
            document.getElementById('total').value = total.toFixed(2);
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h1>Place Order</h1>
    <%
        // Get productId from request parameter
        String productId = request.getParameter("productId");

        // Fetch product details using ProducttDAO
        ProducttDAO producttDAO = new ProducttDAO();
        Productt product = producttDAO.selectProduct(productId);

        if (product != null) {
    %>
    <div class="card">
        <div class="card-body">
            <h5 class="card-title"><%= product.getTitle() %></h5>
            <p class="card-text">Price: $<%= product.getPrice() %></p>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/order/placeOrder" method="post" enctype="multipart/form-data">
        <input type="hidden" id="price" value="<%= product.getPrice() %>">
        <input type="hidden" name="productId" value="<%= productId %>">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" class="form-control" id="quantity" name="quantity" min="1" value="1" onchange="updateTotal()" required>
        </div>
        <div class="form-group">
            <label for="total">Total</label>
            <input type="text" class="form-control" id="total" name="total" readonly value="<%= product.getPrice() %>">
        </div>
        <div class="form-group">
            <label for="file">Upload Payment Receipt (PDF,PNG, OR JPEG)</label>
            <input type="file" class="form-control-file" id="file" name="file" accept=".pdf, .png, .jpeg, .jpg" required>
        </div>
        <button type="submit" class="btn btn-primary">Place Order</button>
    </form>
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
