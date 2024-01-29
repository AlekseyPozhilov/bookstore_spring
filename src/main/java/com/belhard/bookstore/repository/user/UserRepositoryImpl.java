package com.belhard.bookstore.repository.user;

import com.belhard.bookstore.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    public static final String FIND_BY_EMAIL = "SELECT u FROM users u WHERE u.email = :email";
    public static final String GET_ALL = "from User";
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<User> findById(Long key) {
        return Optional.ofNullable(manager.find(User.class, key));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = manager.createQuery(FIND_BY_EMAIL, User.class);
        query.setParameter("email", email);

        return query.getResultList().stream().findFirst();
    }

    @Override
    public User create(User user) {
        if (user.getId() != null) {
            manager.merge(user);
        } else {
            manager.persist(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return manager.createQuery(GET_ALL, User.class).getResultList();
    }

    @Override
    public User update(User user) {
        if (user.getId() != null) {
            manager.merge(user);
            return manager.merge(user);
        }
        return null;
    }
    @Override
    public boolean delete(Long key) {
        User user = manager.find(User.class, key);
        if (user != null) {
            manager.remove(user);
            return true;
        }
        return false;
    }
}
