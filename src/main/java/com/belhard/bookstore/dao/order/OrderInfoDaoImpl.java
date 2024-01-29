package com.belhard.bookstore.dao.order;

import com.belhard.bookstore.dto.order.OrderInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
//@Repository
public class OrderInfoDaoImpl implements OrderInfoDao {
    private static final String FIND_BY_ID_SCRIPT = "SELECT o.id, o.price, o.quantity, o.order_id, o.book_id FROM order_item o WHERE id = ?";
    private static final String FIND_BY_ORDER_ID_SCRIPT = "SELECT o.id, o.price, o.quantity, o.order_id, o.book_id FROM order_item o WHERE o.order_id = ?";
    private static final String FIND_ALL_SCRIPT = "SELECT o.id, o.price, o.quantity, o.order_id, o.book_id FROM order_item o";
    private static final String CREATE_SCRIPT = "INSERT INTO order_item (price, quantity, order_id, book_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_NAMED_SCRIPT = "UPDATE order_item SET price = :price, quantity = :quantity, order_id = :order_id, book_id = :book_id WHERE id = :id";
    private static final String DELETE_SCRIPT = "DELETE FROM order_item WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public OrderInfoDto create(OrderInfoDto orderInfo) {
        log.info("Enter method create in OrderItemDaoImpl");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE_SCRIPT,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setBigDecimal(1, orderInfo.getBookPrice());
            statement.setInt(2, orderInfo.getBookQuantity());
            statement.setLong(3, orderInfo.getOrderId());
            statement.setLong(4, orderInfo.getBookId());
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        long id = (long) keys.get("id");
        return findById(id);
    }

    @Override
    public OrderInfoDto findById(Long id) {
        log.info("Enter method findById in OrderItemDaoImpl");
        try {
            return jdbcTemplate.queryForObject(FIND_BY_ID_SCRIPT, this::mapRow, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderInfoDto> findByOrderId(Long orderId) {
        log.info("Enter method findByOrderId in OrderItemDaoImpl");
        try {
            return jdbcTemplate.query(FIND_BY_ORDER_ID_SCRIPT, this::mapRow, orderId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<OrderInfoDto> findAll() {
        log.info("Enter method readAll in OrderItemDaoImpl");
        try {
            return jdbcTemplate.query(FIND_ALL_SCRIPT, this::mapRow);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public OrderInfoDto update(OrderInfoDto order) {
        log.info("Enter method update in OrderItemDaoImpl");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("price", order.getBookPrice());
        parameters.put("quantity", order.getBookQuantity());
        parameters.put("order_id", order.getOrderId());
        parameters.put("book_id", order.getBookId());
        namedParameterJdbcTemplate.update(UPDATE_NAMED_SCRIPT, parameters);
        return findById(order.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.info("Enter method delete in OrderItemDaoImpl");
        int rowsUpdated = jdbcTemplate.update(DELETE_SCRIPT, id);
        return rowsUpdated == 1;
    }

    private OrderInfoDto mapRow(ResultSet resultSet, int rowNum) {
        try{
            log.info("Enter method mapRow in UserDao");
            OrderInfoDto orderInfoDto = new OrderInfoDto();
            orderInfoDto.setId(resultSet.getLong("id"));
            orderInfoDto.setOrderId(resultSet.getLong("order_id"));
            orderInfoDto.setBookId(resultSet.getLong("book_id"));
            orderInfoDto.setBookQuantity(resultSet.getInt("quantity"));
            orderInfoDto.setBookPrice(resultSet.getBigDecimal("price"));
            return orderInfoDto;
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
