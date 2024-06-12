<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .fade-out {
      transition: opacity 1s ease-out;
      opacity: 0;
    }
  </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Products
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Spices</a>
          <a class="dropdown-item" href="#">Sweets</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Contact Us</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About Us</a>
      </li>
      <c:choose>
        <c:when test="${not empty sessionScope.username}">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="nav-item">
            <a class="nav-link" href="AdminDashboard.jsp">Login</a>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</nav>

<div class="container mt-5">
  <% String welcomeMessage = (String) request.getAttribute("welcomeMessage"); %>
  <% if (welcomeMessage != null) { %>
  <div id="welcome-alert" class="alert alert-success mt-3" role="alert">
    <%= welcomeMessage %>
  </div>
  <% } %>
  <div class="row">
    <c:forEach var="product" items="${productList}">
      <div class="col-md-4">
        <div class="card mb-4 shadow-sm">
          <img src="data:image/jpeg;base64,${product.productImage1Base64}" class="card-img-top" alt="Product Image">
          <div class="card-body">
            <h5 class="card-title">${product.title}</h5>
            <p class="card-text">Price: $${product.price}</p>
            <div class="d-flex justify-content-between align-items-center">
              <div class="btn-group">
                <a href="product-details.jsp?productId=${product.productId}" class="btn btn-sm btn-outline-secondary">View More</a>
              </div>
              <small class="text-muted">9 mins</small>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  setTimeout(function() {
    var alert = document.getElementById('welcome-alert');
    if (alert) {
      alert.classList.add('fade-out');
      setTimeout(function() {
        alert.remove();
      }, 1000);
    }
  }, 6000);
</script>

</body>
</html>
