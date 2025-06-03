package com.example.session17.controller;

import com.example.session17.model.Customer;
import com.example.session17.service.CustomerService;
import com.example.session17.service.OrderService;
import com.example.session17.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model) {
        Customer loggedInUser = (Customer) session.getAttribute("loggedInUser");
        if (loggedInUser == null || !"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {
            return "redirect:/login";
        }

        // Thống kê người dùng
        model.addAttribute("totalCustomers", customerService.countAllCustomers());
        model.addAttribute("activeCustomers", customerService.countActiveCustomers());
        model.addAttribute("inactiveCustomers", customerService.countInactiveCustomers());

        // Thống kê sản phẩm
        model.addAttribute("totalProducts", productService.countAllProducts());
        model.addAttribute("inStockProducts", productService.countInStockProducts());
        model.addAttribute("outOfStockProducts", productService.countOutOfStockProducts());

        // Thống kê đơn hàng
        model.addAttribute("totalOrders", orderService.countAllOrders());
        model.addAttribute("orderStatusCounts", orderService.countOrdersByStatus());

        // Thống kê doanh thu
        model.addAttribute("totalRevenue", orderService.getTotalRevenue());

        return "admin";
    }
}