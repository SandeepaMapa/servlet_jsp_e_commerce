<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        // Store initial values in JavaScript variables
        var initialTitle = "${product.title}";
        var initialDescription = "${product.description}";
        var initialPrice = "${product.price}";

        // Function to reset form fields to initial values
        function resetForm() {
            document.getElementById("title").value = initialTitle;
            document.getElementById("description").value = initialDescription;
            document.getElementById("price").value = initialPrice;
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Edit Product</h2>
    <form action="update" method="post" enctype="multipart/form-data">
        <input type="hidden" id="productId" name="productId" value="${product.productId}">
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" maxlength="128" value="${product.title}" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" id="description" name="description" maxlength="650" required>${product.description}</textarea>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="text" class="form-control" id="price" name="price" maxlength="8" value="${product.price}" required>
        </div>
        <div class="form-group">
            <label>Product Image 1</label><br>

            <img src="data:image/jpeg;base64,${product.productImage1Base64}" alt="Product Image 1" style="max-width: 200px;">
        </div>
        <div class="form-group">
            <label>Product Image 2</label><br>
            <img src="data:image/jpeg;base64,${product.productImage2Base64}" alt="Product Image 2" style="max-width: 200px;">
        </div>
        <div class="form-group">
            <label>Product Image 3</label><br>
            <img src="data:image/jpeg;base64,${product.productImage3Base64}" alt="Product Image 3" style="max-width: 200px;">
        </div>
        <button type="submit" class="btn btn-primary mb-4">Update</button>
        <button type="button" class="btn btn-secondary mb-4" onclick="resetForm()">Reset</button>
    </form>
</div>
</body>
</html>