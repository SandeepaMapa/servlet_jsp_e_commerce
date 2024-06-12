<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .fade-out {
            transition: opacity 1s ease-out;
            opacity: 0;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="AdminDashboard.jsp">Back to Site</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
                </li>
            </ul>
        </div>
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
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="https://static.vecteezy.com/system/resources/previews/006/617/809/non_2x/four-food-items-vector.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Add Product</h5>
                    <a href="new" class="btn btn-primary">Add Product</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="https://static.vecteezy.com/system/resources/previews/020/437/389/original/admin-icon-vector.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Create a New Admin</h5>
                    <a href="add-admin.jsp" class="btn btn-primary">Add Admin</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card">
                <img src="https://static.vecteezy.com/system/resources/previews/006/617/809/non_2x/four-food-items-vector.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">View Products</h5>
                    <a href="list" class="btn btn-primary">List Products</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Logout Confirmation Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutModalLabel">Confirm Logout</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to logout?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Logout</a>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    setTimeout(function() {
        var alert = document.getElementById('welcome-alert');
        if (alert) {
            alert.classList.add('fade-out');
            setTimeout(function() {
                alert.remove();
            }, 1000); // 1 second to match the CSS transition
        }
    }, 6000);
</script>

</body>
</html>
