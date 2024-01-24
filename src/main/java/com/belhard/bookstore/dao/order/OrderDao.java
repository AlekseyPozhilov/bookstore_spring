package com.belhard.bookstore.dao.order;

import com.belhard.bookstore.dto.order.OrderDto;

import java.util.List;

public interface OrderDao {
    OrderDto findById(Long id);
    List<OrderDto> findAll();
    boolean delete(Long id);
    OrderDto create(OrderDto order);
    OrderDto update(OrderDto order);
}
