package com.example.services;

import java.sql.SQLException;
import java.util.List;
import com.example.dao.AdminDAO;
import com.example.model.AdminBean;
import com.example.utils.ValidationUtils;
import org.mindrot.jbcrypt.BCrypt;

public class AdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    public List<AdminBean> getAllAdmins() throws SQLException {
        return adminDAO.getAllAdmins();
    }

    public void addAdmin(AdminBean admin) throws SQLException {
        if (ValidationUtils.isValidAdmin(admin.getUsername(), admin.getEmail(), admin.getPassword())) {
            // Hash the password using bcrypt
            admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            adminDAO.insertAdmin(admin);
        } else {
            throw new IllegalArgumentException("Invalid input data");
        }
    }

    public void updateAdmin(AdminBean admin) throws SQLException {
        if (ValidationUtils.isValidAdmin(admin.getUsername(), admin.getEmail(), admin.getPassword())) {
            // Hash the password using bcrypt
            admin.setPassword(BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt()));
            adminDAO.updateAdmin(admin);
        } else {
            throw new IllegalArgumentException("Invalid input data");
        }
    }

    public void deleteAdmin(String username) throws SQLException {
        adminDAO.deleteAdminById(username);
    }
}
