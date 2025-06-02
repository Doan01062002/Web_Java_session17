package com.example.session17.repository;

import com.example.session17.model.Customer;

public interface CustomerRepository {
    void save(Customer customer);
    void update(Customer customer);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, int customerId);
    Customer findByUsername(String username);
    Customer findById(int id);
}