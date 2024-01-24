package com.belhard.bookstore.dao.order;

import com.belhard.bookstore.dto.order.OrderDto;
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
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Repository
public class OrderDaoImpl implements OrderDao {
    private static final String FIND_BY_ID = "SELECT id, user_id, total_cost, status FROM orders WHERE id = ";
    private static final String FIND_ALL = "SELECT id, user_id, total_cost, status FROM orders";
    private static final String INSERT = "INSERT INTO orders (user_id, total_cost, status) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET user_id = :user_id, total_cost = :total_cost, status = :status WHERE id = :id";
    private static final String DELETE = "DELETE FROM orders WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderDto findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, this::mapRow, id);
    }

    public List<OrderDto> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, this::mapRow);
        } catch (DataAccessException e) {
            return null;
        }

    }

    public boolean delete(Long id) {
        int rowsUpdated = jdbcTemplate.update(DELETE, id);
        return rowsUpdated == 1;

    }

    public OrderDto create(OrderDto order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getUserId().toString());
            statement.setString(2, order.getTotalCost().toString());
            statement.setString(3, order.getStatus().toString());
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        long id = (long) keys.get("id");
        return findById(id);
    }

    public OrderDto update(OrderDto order) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", order.getUserId());
        parameters.put("totalCost", order.getTotalCost());
        parameters.put("status", order.getStatus());
        namedParameterJdbcTemplate.update(UPDATE, parameters);
        return findById(order.getId());
    }

    private OrderDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderDto order = new OrderDto();
        order.setId(resultSet.getLong("id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setTotalCost(resultSet.getBigDecimal("totalCost"));
        order.setStatus(OrderDto.Status.valueOf(resultSet.getString("status")));
        return order;
    }
}
