package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storige.user.InMemoryUserStorage;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final InMemoryUserStorage userStorage;

    public User save(User user) {
        user.setId(getNextId());
        return userStorage.save(user);
    }

    public Optional<User> findById(long userId) {
        return Optional.ofNullable(userStorage.findById(userId));
    }

    public Collection<User> findAll() {
        return userStorage.findAll().values();
    }

    public void deleteById(long id) {
        userStorage.deleteById(id);
    }

    private Long getNextId() {
        long currentMaxId = userStorage.findAll().values()
                .stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
