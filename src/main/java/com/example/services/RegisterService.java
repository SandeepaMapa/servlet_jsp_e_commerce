// Service
package com.example.services;

import com.example.dao.CustomerDAO;
import com.example.model.CustomerBean;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterService {
    private final CustomerDAO customerDAO;
    private final EmailService emailService;

    public RegisterService() {
        this.customerDAO = new CustomerDAO();
        this.emailService = new EmailService();
    }

    public void registerCustomer(CustomerBean customer) throws SQLException {
        String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());
        String token = UUID.randomUUID().toString();
        customer.setPassword(hashedPassword);
        customerDAO.registerPendingCustomer(customer, token);
        emailService.sendVerificationEmail(customer.getEmail(), token);
    }
}
