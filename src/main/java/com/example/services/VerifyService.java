// Service
package com.example.services;

import com.example.dao.CustomerDAO;
import com.example.model.CustomerBean;

import java.sql.SQLException;

public class VerifyService {
    private final CustomerDAO customerDAO;

    public VerifyService() {
        this.customerDAO = new CustomerDAO();
    }

    public void verifyCustomer(String token) throws SQLException {
        CustomerBean customer = customerDAO.getPendingCustomerByToken(token);
        if (customer != null) {
            System.out.println("Customer IS pASSESD TO VERIFY SERVICE");
            customerDAO.registerCustomer(customer);
            customerDAO.deletePendingCustomer(token);
        }
        else {
            System.out.println("Customer Null");
        }

    }
}
