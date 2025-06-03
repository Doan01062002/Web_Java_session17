package com.example.session17.repository;

import com.example.session17.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllPaginated(int page, int size);
    long countAll();
    Product findById(int id);
    void update(Product product);
    void save(Product product);
    void delete(Product product);
}