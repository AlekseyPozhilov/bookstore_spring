package com.belhard.bookstore.data.repository.order;

import com.belhard.bookstore.data.entity.Order;

import java.util.List;

public interface OrderRepository {
    Order findById(Long key);
    Order create(Order order);
    List<Order> findAll();
    Order update(Order order);
    boolean delete(Long id);
}
