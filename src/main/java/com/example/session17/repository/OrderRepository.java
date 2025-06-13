package com.example.session17.repository;

import com.example.session17.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    Order save(Order order);
    void update(Order order);
    Order findByIdAndCustomerId(int orderId, int customerId);
    List<Order> findByCustomerId(int customerId, int page, int size);
    long countByCustomerId(int customerId);
    Order findById(int orderId);
    List<Order> findAllPaginated(int page, int size);
    long countAll();
    long countByStatus(String status);
    double getTotalRevenue();
}