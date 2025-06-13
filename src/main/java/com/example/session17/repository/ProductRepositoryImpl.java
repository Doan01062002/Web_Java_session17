package com.example.session17.repository;

import com.example.session17.model.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAllPaginated(int page, int size) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Product", Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(p.id) FROM Product p", Long.class)
                .uniqueResult();
    }

    @Override
    public Product findById(int id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public void update(Product product) {
        sessionFactory.getCurrentSession().merge(product);
    }

    @Override
    public void save(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public long countByStockGreaterThan(int stock) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(p.id) FROM Product p WHERE p.stock > :stock", Long.class)
                .setParameter("stock", stock)
                .uniqueResult();
    }

    @Override
    public long countByStock(int stock) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(p.id) FROM Product p WHERE p.stock = :stock", Long.class)
                .setParameter("stock", stock)
                .uniqueResult();
    }
}