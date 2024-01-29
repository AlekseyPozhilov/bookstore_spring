package com.belhard.bookstore.repository.order;

import com.belhard.bookstore.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order findById(Long key);
    Order create(Order order);
    List<Order> findAll();
    Order update(Order order);
    boolean delete(Long id);
}
