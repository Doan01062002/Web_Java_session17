package com.example.session17.controller;

import com.example.session17.model.Customer;
import com.example.session17.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class CustomerAdminController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listUsers(@RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "10") int pageSize,
                            Model model) {
        List<Customer> customers = customerService.getAllCustomers(page, pageSize);
        long totalCustomers = customerService.countAllCustomers();
        long totalPages = (totalCustomers + pageSize - 1) / pageSize;

        model.addAttribute("customers", customers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        return "admin-users";
    }

    @GetMapping("/toggle-status")
    public String toggleUserStatus(@RequestParam("customerId") int customerId) {
        customerService.toggleCustomerStatus(customerId);
        return "redirect:/admin/users?statusToggled=true";
    }
}