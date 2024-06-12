<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Product List</h2>
        
        
       <table class="table table-striped">
    <thead>
        <tr>
            <th>Product ID</th>
            <th>Description</th>
            <th>Product Image 1</th>
            <th>Product Image 2</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.description}</td>
                <td>${product.productImage1}</td>
                <td>${product.productImage2}</td>
                <td><fmt:formatNumber value="${product.price}" type="currency"/></td>
                <td>
                    <a href="edit?id=${product.productId}" class="btn btn-primary">Edit</a>
                    <a href="delete?id=${product.productId}" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
    </div>
</body>
</html>
