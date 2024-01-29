package com.belhard.bookstore.repository.order;

import com.belhard.bookstore.entity.Book;
import com.belhard.bookstore.entity.Order;
import com.belhard.bookstore.entity.OrderInfo;
import com.belhard.bookstore.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {
    public static final String GET_ALL = "from Order";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Order findById(Long id) {
        Order order = manager.find(Order.class, id);
        return combineOrder(order);
    }

    @Override
    public Order create(Order order) {
        manager.persist(order);
        order.getItems().forEach(orderItem -> manager.persist(orderItem));
        return combineOrder(order);
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = manager.createQuery(GET_ALL, Order.class)
                .getResultList();
        return orders.stream()
                .map(this::combineOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        order.getItems().forEach(orderItem -> manager.persist(orderItem));
        return combineOrder(order);
    }

    @Override
    public boolean delete(Long id) {
        Order order = manager.find(Order.class, id);
        if (order != null) {
            manager.remove(order);
            return true;
        }
        return false;
    }

    private Order combineOrder(Order order) {
        order.setUser(manager.find(User.class, order.getUser().getId()));
        List<OrderInfo> items = order.getItems()
                .stream()
                .map(orderItem -> {
                    orderItem.setBook(manager.find(Book.class, orderItem.getBook().getId()));
                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setItems(items);
        return order;
    }
}
