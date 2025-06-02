package com.example.session17.repository;

import com.example.session17.model.ProductCart;

import java.util.List;

public interface ProductCartRepository {
    ProductCart saveOrUpdate(ProductCart productCart);
    ProductCart findByCustomerIdAndProductId(int customerId, int productId);
    List<ProductCart> findByCustomerId(int customerId);
    void delete(ProductCart productCart);
    ProductCart findByIdAndCustomerId(int cartId, int customerId);
    void deleteAllByCustomerId(int customerId);
}