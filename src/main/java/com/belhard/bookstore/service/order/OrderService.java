package com.belhard.bookstore.service.order;

import com.belhard.bookstore.data.dto.order.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto findById(Long id);
    List<OrderDto> findAll();
    void delete(Long id);
    OrderDto create(OrderDto dto);
    OrderDto update(OrderDto order);
}
