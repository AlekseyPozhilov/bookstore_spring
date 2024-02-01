package com.belhard.bookstore.service.order;

import com.belhard.bookstore.data.repository.order.OrderRepository;
import com.belhard.bookstore.data.dto.order.OrderDto;
import com.belhard.bookstore.data.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new RuntimeException("No book with id = " + id);
        }
        return mapToDto(order);
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDto) //
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting order: {}", id);

        orderRepository.delete(id);

        log.debug("Order deleted: {}", id);
    }

    @Override
    public OrderDto create(OrderDto dto) {
        log.debug("Creating order: {}", dto);
        Order order = mapToEntity(dto);
        Order created = orderRepository.create(order);
        log.debug("Order created: {}", created);
        return mapToDto(created);
    }

@Override
public OrderDto update(OrderDto dto) {
    log.debug("Updating order: {}", dto);

    Order order = orderRepository.findById(dto.getId());

    order.setTotalCost(dto.getTotalCost());
    OrderDto.Status status = dto.getStatus();
    if (status != null) {
        order.setStatus(Order.Status.valueOf(status.toString()));
    }

    Order edited = orderRepository.update(order);

    log.debug("Order updated: {}", edited);

    return mapToDto(edited);
}

private OrderDto mapToDto(Order order) {
    OrderDto dto = new OrderDto();
    dto.setId(order.getId());
    dto.setTotalCost(order.getTotalCost());
    Order.Status status = order.getStatus();
    dto.setItems(order.getItems());
    if (status != null) {
        dto.setStatus(OrderDto.Status.valueOf(status.toString()));
    }
    return dto;
}

    private Order mapToEntity(OrderDto dto) {
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
