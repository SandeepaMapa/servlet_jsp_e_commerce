package com.example.mapper;

import com.example.model.OrderBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.commons.io.IOUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.UUID;

public class OrderRequestMapper {

    public static OrderBean mapToOrder(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String orderId = UUID.randomUUID().toString(); // Generate unique order ID
            String email = (String) session.getAttribute("username");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String item = request.getParameter("productId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String status = "Pending"; // Set status to pending by default

            // Get payment slip
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            Blob paymentSlip = null;
            if (fileContent.available() > 0) {
                try {
                    paymentSlip = new SerialBlob(IOUtils.toByteArray(fileContent));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            String totalStr = request.getParameter("total");

            // Create OrderBean object
            OrderBean order = new OrderBean();
            order.setOrderId(orderId);
            order.setEmail(email);
            order.setName(name);
            order.setAddress(address);
            order.setItem(item);
            order.setQuantity(quantity);
            order.setPaymentSlip(paymentSlip);
            order.setStatus(status);
            order.setTotal(totalStr);

            return order;
        } else {
            throw new IllegalArgumentException("User is not logged in");
        }
    }
}
