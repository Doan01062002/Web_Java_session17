package com.example.session17.service;

import com.example.session17.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getProductsPaginated(int page, int size);
    long countTotalProducts();
    Product getProductById(int id);
    void saveProduct(Product product);
    void deleteProduct(int id);
    long countAllProducts();
    long countInStockProducts();
    long countOutOfStockProducts();
}