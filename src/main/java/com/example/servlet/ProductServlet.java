package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.model.ProductRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.model.Productt;
import com.example.services.ProductService;
import com.example.mapper.ProductRequestMapper;
import com.example.mapper.ProductResponseMapper;

@WebServlet("/")
@MultipartConfig
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductService productService;

    public void init() {
        productService = new ProductService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    ProductResponseMapper.forwardToForm(request, response);
                    break;
                case "/insertproduct":
                    insertProduct(request, response);
                    break;
                case "/delete":
                    deleteProduct(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduct(request, response);
                    break;
                case "/list":
                    listProducts(request, response);
                    break;
                case "/editsuccess":
                    listAfterEdit(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Productt> productList = productService.getAllProducts();

        ProductResponseMapper.forwardToList(request, response, productList);
    }

    private void listAfterEdit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Productt> productList = productService.getAllProducts();
        request.setAttribute("successMessage", "Product updated successfully!");
        ProductResponseMapper.forwardToList(request, response, productList);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Productt existingProduct = productService.getProductById(id);
        ProductResponseMapper.forwardToEditForm(request, response, existingProduct);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            ProductRequest productRequest = ProductRequestMapper.mapToProductRequest(request);
            Productt product = new Productt(productRequest.getId(), productRequest.getTitle(), productRequest.getDescription(),
                    productRequest.getProductImage1(), productRequest.getProductImage2(), productRequest.getProductImage3(), productRequest.getPrice());

            productService.addProduct(product);
            ProductResponseMapper.forwardWithMessage(request, response, "Product added successfully!", "product-form.jsp");
        } catch (IllegalArgumentException | SQLException e) {
            ProductResponseMapper.forwardWithMessage(request, response, "Invalid input data. Please try again.", "error.jsp");
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            ProductRequest productRequest = ProductRequestMapper.mapToProductRequest(request);
            Productt product = new Productt(productRequest.getId(), productRequest.getTitle(), productRequest.getDescription(), productRequest.getPrice());

            productService.updateProduct(product);
           ProductResponseMapper.redirect(response, "editsuccess");

        } catch (IllegalArgumentException | SQLException e) {
            ProductResponseMapper.redirect(response, "error.jsp");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        try {
            productService.deleteProduct(id);
            ProductResponseMapper.redirect(response, "list");
        } catch (SQLException e) {
            e.printStackTrace();
            ProductResponseMapper.redirect(response, "error.jsp");
        }
    }
}
