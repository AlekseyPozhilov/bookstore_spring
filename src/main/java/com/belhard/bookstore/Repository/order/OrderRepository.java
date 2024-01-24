package com.belhard.bookstore.Repository.order;

import com.belhard.bookstore.entity.Order;

import java.util.List;

public interface OrderRepository {
    Order findByID(Long id);
    Order create(Order order);
    List<Order> findAll();
    Order update(Order order);
    boolean delete(Long id);
}
