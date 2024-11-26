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
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Long userId) {
        return userMap.get(userId);
    }

    @Override
    public Map<Long, User> findAll() {
        return userMap;
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }
}
