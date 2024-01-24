package com.belhard.bookstore.Repository.order;

import com.belhard.bookstore.dao.order.OrderDao;
import com.belhard.bookstore.dataMapper.DataMapper;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final DataMapper dataMapper;

    @Override
    public Order findByID(Long id) {
        return dataMapper.toEntity(orderDao.findById(id));
    }

    @Override
    public Order create(Order order) {
        OrderDto dto = dataMapper.orderReadDto(order);
        OrderDto created = orderDao.create(dto);
        return dataMapper.toEntity(created);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll().stream().map(dataMapper::toEntity).collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        OrderDto dto = dataMapper.orderReadDto(order);
        OrderDto updated = orderDao.update(dto);
        return dataMapper.toEntity(updated);
    }

    @Override
    public boolean delete(Long id) {
        return orderDao.delete(id);
    }
}
