package com.belhard.bookstore.service.order;

import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDto findById(Long id);
    List<OrderDto> findAll();
    void delete(Long id);
    OrderDto create(OrderDto dto);
    OrderDto update(OrderDto order);
}
