package ru.yandex.practicum.filmorate.storige.user;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.Map;
@Repository
public class InMemoryUserStorage implements UserStorage {

    private final Map<Long, User> userMap = new HashMap<>();
    @Override
    public User save(User user) {
        return userMap.put(user.getId(), user);
    }

    @Override
    public User findById(long userId) {
        return userMap.get(userId);
    }

    @Override
    public Map<Long, User> findAll() {
        return userMap;
    }

    @Override
    public void deleteById(long id) {
        userMap.remove(id);
    }
}
