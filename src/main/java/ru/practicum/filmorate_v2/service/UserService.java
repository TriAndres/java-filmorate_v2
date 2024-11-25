package ru.practicum.filmorate_v2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.filmorate_v2.model.User;
import ru.practicum.filmorate_v2.storige.user.InMemoryUserStorage;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final InMemoryUserStorage userStorage;

    public User save(User user) {
        return null;
    }

    public User findById(long userId) {
        return null;
    }

    public Map<Long, User> findAll() {
        return null;
    }

    public void deleteById(long id) {

    }
}
