package com.example.session17.service;

import com.example.session17.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer register(Customer customer);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    boolean isEmailTakenByOtherUser(String email, int currentUserId);
    Customer login(String username, String password);
    void updateCustomer(Customer customer);
    Customer findById(int id);
    List<Customer> getAllCustomers(int page, int size);
    long countAllCustomers();
    void toggleCustomerStatus(int customerId);
}