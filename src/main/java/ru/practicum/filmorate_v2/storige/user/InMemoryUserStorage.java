package ru.practicum.filmorate_v2.storige.user;

import org.springframework.stereotype.Repository;
import ru.practicum.filmorate_v2.model.User;

import java.util.Map;
@Repository
public class InMemoryUserStorage implements UserStorage {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findById(long userId) {
        return null;
    }

    @Override
    public Map<Long, User> findAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
