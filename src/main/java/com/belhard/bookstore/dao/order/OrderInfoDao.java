package com.belhard.bookstore.dao.order;

import com.belhard.bookstore.dto.order.OrderInfoDto;

import java.util.List;


public interface OrderInfoDao {
    OrderInfoDto create(OrderInfoDto orderInfo);
    OrderInfoDto findById(Long id);
    List<OrderInfoDto> findByOrderId(Long orderId);
    List<OrderInfoDto> findAll();
    OrderInfoDto update(OrderInfoDto order);
    boolean delete(Long id);
}
