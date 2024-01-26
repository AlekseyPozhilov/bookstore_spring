package com.belhard.bookstore.Repository.order;

import com.belhard.bookstore.dao.book.BookDao;
import com.belhard.bookstore.dao.order.OrderDao;
import com.belhard.bookstore.dao.order.OrderInfoDao;
import com.belhard.bookstore.dao.user.UserDao;
import com.belhard.bookstore.dataMapper.DataMapper;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.dto.order.OrderInfoDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.entity.Order;
import com.belhard.bookstore.entity.OrderInfo;
import com.belhard.bookstore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao orderDao;
    private final OrderInfoDao orderInfoDao;
    private final UserDao userDao;
    private final BookDao bookDao;
    private final DataMapper dataMapper;

    @Override
    public Order findByID(Long id) {
        OrderDto orderDto = orderDao.findById(id);
        return combineOrder(orderDto);
    }

    @Override
    public Order create(Order order) {
        OrderDto dto = dataMapper.orderReadDto(order);
        OrderDto created = orderDao.create(dto);
        order.getItems().forEach(orderInfo -> {
            OrderInfoDto orderInfoDto = dataMapper.orderInfoReadDto(orderInfo);
            orderInfoDao.create(orderInfoDto);
        });
        return combineOrder(created);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll().stream().map(this::combineOrder).collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        OrderDto dto = dataMapper.orderReadDto(order);
        OrderDto updated = orderDao.update(dto);
        orderInfoDao.findByOrderId(order.getId()).forEach(oldOrderInfo -> orderInfoDao.delete(oldOrderInfo.getId()));
        order.getItems().forEach(orderInfo -> {
            OrderInfoDto orderInfoDto = dataMapper.orderInfoReadDto(orderInfo);
            orderInfoDao.create(orderInfoDto);
        });
        return combineOrder(updated);
    }

    @Override
    public boolean delete(Long id) {
        if (!orderDao.delete(id)) {
            return false;
        }
        orderInfoDao.findByOrderId(id).forEach(orderInfoDto -> orderInfoDao.delete(orderInfoDto.getId()));
        return true;
    }

    private Order combineOrder(OrderDto orderDto) {
        Order order = dataMapper.toEntity(orderDto);
        User user = dataMapper.toEntity(userDao.read(orderDto.getUserId()));
        order.setUser(user);
        List<OrderInfoDto> detailsDto = orderInfoDao.findByOrderId(orderDto.getId());
        List<OrderInfo> details = new ArrayList<>();
        detailsDto.forEach(dto -> {
            OrderInfo entity = dataMapper.toEntity(dto);
            Book book = dataMapper.toEntity(bookDao.findById(dto.getBookId())); //?
            entity.setBook(book);
            details.add(entity);
        });
        order.setItems(details);
        return order;
    }
}
