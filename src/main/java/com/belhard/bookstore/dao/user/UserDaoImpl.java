package com.belhard.bookstore.dao.user;

import com.belhard.bookstore.entity.User;
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
public class UserDaoImpl implements UserDao {
    public static final String INSERT_QUERY = "INSERT INTO users (firstName, lastName, email, dateOfBirth, gender, phoneNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users WHERE id = ?";
    public static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    public static final String SELECT_BY_EMAIL_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users WHERE email = ?";
    public static final String SELECT_ALL_QUERY = "SELECT id, firstName, lastName, email, dateOfBirth, gender, phoneNumber, password FROM users";
    private static final String UPDATE_NAMED_SCRIPT = "UPDATE users SET firstName = :firstName, lastName = :lastName, email = :email, dateOfBirth = :dateOfBirth, gender = :gender, phoneNumber = :phoneNumber, password = :password WHERE id = :id";
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User create(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getDateOfBirth());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getPassword());
            return statement;
        }, keyHolder);
        Map<String, Object> keys = keyHolder.getKeys();
        long id = (long) keys.get("id");
        return read(id);
    }

    @Override
    public User read(Long id) {
        return jdbcTemplate.queryForObject(SELECT_QUERY, this::mapRow, id);
    }

    @Override
    public User update(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("firstName", user.getFirstName());
        parameters.put("lastName", user.getLastName());
        parameters.put("email", user.getEmail());
        parameters.put("dateOfBirth", user.getDateOfBirth());
        parameters.put("gender", user.getGender());
        parameters.put("phoneNumber", user.getPhoneNumber());
        parameters.put("password", user.getPassword());
        namedParameterJdbcTemplate.update(UPDATE_NAMED_SCRIPT, parameters);
        return read(user.getId());
    }

    @Override
    public boolean delete(Long id) {
        int rowsUpdated = jdbcTemplate.update(DELETE_QUERY, id);
        return rowsUpdated == 1;
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_BY_EMAIL_QUERY, this::mapRow, email);
    }

    @Override
    public List<User> getAll() throws SQLException {
        try {
            return jdbcTemplate.query(SELECT_ALL_QUERY, this::mapRow);
        } catch (DataAccessException e) {
            return null;
        }
    }

    private User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));
        user.setDateOfBirth(resultSet.getString("dateOfBirth"));
        user.setGender(resultSet.getString("gender"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
