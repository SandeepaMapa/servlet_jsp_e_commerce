<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .alert-custom {
            position: fixed;
            top: 10px;
            left: 50%;
            transform: translateX(-50%);
            z-index: 1050;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mt-5 mb-3">Login</h2>

    <!-- Display error message if present -->
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null) { %>
    <div class="alert alert-danger" role="alert">
        <%= errorMessage %>
    </div>
    <% } %>

    <!-- Alert message for email notification -->
    <div id="alert-message" class="alert alert-success alert-dismissible fade show alert-custom" role="alert" style="display: none;">
        <span id="alert-text"></span>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
    <div class="mt-3">
        <p>New User? <a href="signup.jsp">Click here to sign up!</a></p>
    </div>
</div>

<!-- Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
    function getQueryParam(param) {
        let urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    window.onload = function() {
        let message = getQueryParam('message');
        if (message) {
            let alertMessage = document.getElementById('alert-message');
            document.getElementById('alert-text').innerText = message;
            alertMessage.style.display = 'block';

            // Fade away the alert after 6 seconds

        }
    };
</script>
</body>
</html>
