package com.example.session17.service;

import com.example.session17.model.Customer;
import com.example.session17.model.Order;
import com.example.session17.model.ProductCart;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order createOrder(Order orderDetails, Customer customer, List<ProductCart> cartItems) throws IllegalArgumentException;
    List<Order> getOrdersByCustomerId(int customerId, int page, int size);
    long countOrdersByCustomerId(int customerId); // Đổi tên cho rõ ràng
    void cancelOrder(int orderId, int customerId) throws IllegalArgumentException;
    Order getOrderByIdAndCustomerId(int orderId, int customerId);
    List<Order> getAllOrders(int page, int size);
    long countAllOrders();
    void updateOrderStatus(int orderId, String status);
    Map<String, Long> countOrdersByStatus();
    double getTotalRevenue();
}