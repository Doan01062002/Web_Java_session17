package com.example.session17.repository;

import com.example.session17.model.Order;
import jakarta.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order save(Order order) {
        sessionFactory.getCurrentSession().persist(order);
        return order;
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().merge(order);
    }

    @Override
    public Order findById(int orderId) {
        return sessionFactory.getCurrentSession().get(Order.class, orderId);
    }


    @Override
    public Order findByIdAndCustomerId(int orderId, int customerId) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("FROM Order WHERE id = :orderId AND customerId = :customerId", Order.class)
                    .setParameter("orderId", orderId)
                    .setParameter("customerId", customerId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Order> findByCustomerId(int customerId, int page, int size) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Order WHERE customerId = :customerId ORDER BY id DESC", Order.class) // Thêm ORDER BY
                .setParameter("customerId", customerId)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countByCustomerId(int customerId) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(o.id) FROM Order o WHERE o.customerId = :customerId", Long.class)
                .setParameter("customerId", customerId)
                .uniqueResult();
    }

    @Override
    public List<Order> findAllPaginated(int page, int size) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Order ORDER BY id DESC", Order.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public long countAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(o.id) FROM Order o", Long.class)
                .uniqueResult();
    }

    @Override
    public long countByStatus(String status) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(o.id) FROM Order o WHERE o.status = :status", Long.class)
                .setParameter("status", status)
                .uniqueResult();
    }

    @Override
    public double getTotalRevenue() {
        Double total = sessionFactory.getCurrentSession()
                .createQuery("SELECT SUM(o.totalMoney) FROM Order o WHERE o.status = 'DELIVERED'", Double.class)
                .uniqueResult();
        return total != null ? total : 0.0;
    }
}