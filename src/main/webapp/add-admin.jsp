<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .warning {
            color: red;
            display: none;
        }
    </style>
    <script>
        function validatePasswords() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var passwordWarningText = document.getElementById("passwordWarningText");
            var confirmPasswordWarningText = document.getElementById("confirmPasswordWarningText");
            var submitButton = document.querySelector("button[type='submit']");

            if (password === "") {
                passwordWarningText.style.display = "block";
                submitButton.disabled = true;
            } else {
                passwordWarningText.style.display = "none";
            }

            if (password !== confirmPassword || password === "" || confirmPassword === "") {
                confirmPasswordWarningText.style.display = "block";
                submitButton.disabled = true;
            } else {
                confirmPasswordWarningText.style.display = "none";
                if (password !== "") {
                    submitButton.disabled = false;
                }
            }
        }
    </script>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/AdminDashboard.jsp">Back to Site</a>
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

<div class="container">
    <h2 class="mt-5 mb-3">Add Admin</h2>

    <% String successMessage = request.getParameter("successMessage"); %>
    <% if (successMessage != null) { %>
    <div class="alert alert-success" role="alert">
        <%= successMessage %>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/admin/new" method="post" oninput="validatePasswords()">

        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" maxlength="20" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required maxlength="60">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required maxlength="25">
            <small id="passwordWarningText" class="form-text warning">Password is required</small>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required maxlength="25">
            <small id="confirmPasswordWarningText" class="form-text warning">Passwords do not match</small>
        </div>

        <button type="submit" class="btn btn-primary mb-4" disabled>Submit</button>
    </form>
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

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

