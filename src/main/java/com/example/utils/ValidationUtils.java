package com.example.utils;

import com.example.model.Productt;

public class ValidationUtils {

    // Validate that a string is not null and not empty
    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate that a string is a valid email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }

    // Validate that a string is a valid double (price)
    public static boolean isValidPrice(String price) {
        try {
            Double.parseDouble(price);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate the product fields (individual strings)
    public static boolean isValidProduct(String id, String title, String description, String price) {
        return isNonEmpty(id) && isNonEmpty(title) && isNonEmpty(description) && isValidPrice(price);
    }

    // Validate the product fields (Productt object)
    public static boolean isValidProduct(Productt product) {
        if (product == null) {
            return false;
        }
        return isValidProduct(product.getProductId(), product.getTitle(), product.getDescription(), product.getPrice());
    }

    // Validate the admin fields
    public static boolean isValidAdmin(String username, String email, String password) {
        return isNonEmpty(username) && isValidEmail(email) && isNonEmpty(password);
    }
}
