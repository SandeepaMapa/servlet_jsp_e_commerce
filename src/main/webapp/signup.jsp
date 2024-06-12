<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
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
            var warningText = document.getElementById("warningText");
            var warningEmptyText = document.getElementById("warningEmptyText");
            var submitButton = document.querySelector("button[type='submit']");

            if (password === "") {
                warningEmptyText.style.display = "block";
                submitButton.disabled = true;
            } else {
                warningEmptyText.style.display = "none";
            }

            if (password !== confirmPassword || password === "" || confirmPassword === "") {
                warningText.style.display = "block";
                submitButton.disabled = true;
            } else {
                warningText.style.display = "none";
                submitButton.disabled = false;
            }
        }
    </script>
</head>
<body>

<div class="container">
    <h2 class="mt-5 mb-3">Sign Up</h2>
    <form action="${pageContext.request.contextPath}/register" method="post" oninput="validatePasswords()">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" maxlength="60" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" maxlength="25" required>
            <small id="warningEmptyText" class="form-text warning">Password cannot be empty</small>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" maxlength="25" required>
            <small id="warningText" class="form-text warning">Passwords do not match</small>
        </div>

        <button type="submit" class="btn btn-primary" disabled>Submit</button>
    </form>
    <div class="mt-3">
        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
</div>

</body>
</html>
