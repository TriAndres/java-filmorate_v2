package ru.yandex.practicum.filmorate.storige.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

public interface UserStorage {
    User save(User user);

    User findById(long userId);

    Map<Long, User> findAll();

    void deleteById(long id);
}
