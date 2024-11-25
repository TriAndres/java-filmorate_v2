package ru.practicum.filmorate_v2.storige.user;

import ru.practicum.filmorate_v2.model.User;

import java.util.Map;

public interface UserStorage {
    User save(User user);

    User findById(long userId);

    Map<Long, User> findAll();

    void deleteById(long id);
}
