package com.belhard.bookstore.service.order;

import com.belhard.bookstore.Repository.order.OrderRepository;
import com.belhard.bookstore.dto.book.BookDto;
import com.belhard.bookstore.dto.order.OrderDto;
import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.entity.Order;
import com.belhard.bookstore.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findByID(id);
        if (order == null) {
            throw new RuntimeException("No book with id = " + id);
        }
        return orderReadDto(order);
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(OrderServiceImpl::orderReadDto).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting order: {}", id);
        orderRepository.delete(id);
    }

    @Override
    public OrderDto create(OrderDto dto) {
        log.debug("Creating order: {}", dto);

        Order order = toEntity(dto);
        Order created = orderRepository.create(order);

        log.debug("Order created: {}", order);

        return orderReadDto(created);
    }

    @Override
    public OrderDto update(OrderDto dto) {
        log.debug("Updating order: {}", dto);

        OrderDto orderDto = findById(dto.getId());
        orderDto.setTotalCost(dto.getTotalCost());
        orderDto.setStatus(dto.getStatus());;

        Order order = toEntity(orderDto);
        Order edited = orderRepository.update(order);

        log.debug("Order updated: {}", orderDto);

        return orderReadDto(edited);
    }

    public static OrderDto orderReadDto(Order orderEntity) {
        OrderDto dto = new OrderDto();
        dto.setId(orderEntity.getId());
        dto.setTotalCost(orderEntity.getTotalCost());
        Order.Status status = orderEntity.getStatus();
        if (status != null) {
            dto.setStatus(OrderDto.Status.valueOf(status.toString()));
        }
        return dto;
    }

    public static Order toEntity(OrderDto dto) {
        Order orderEntity = new Order();
        orderEntity.setId(dto.getId());
        orderEntity.setTotalCost(dto.getTotalCost());
        OrderDto.Status status = dto.getStatus();
        if (status != null) {
            orderEntity.setStatus(Order.Status.valueOf(status.toString()));
        }
        return orderEntity;
    }
}
