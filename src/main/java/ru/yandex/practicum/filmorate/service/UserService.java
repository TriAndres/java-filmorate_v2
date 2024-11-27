package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storige.user.InMemoryUserStorage;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final InMemoryUserStorage userStorage;

    public User save(User user) {
        user.setId(getNextId());
        userStorage.save(user);
        return user;
    }

    public User updateUser(User newUser) {
        if (newUser.getId() == null) {
            log.error("Id должен быть указан");
            throw new ValidationException("Id должен быть указан");
        }
        if (userStorage.findAll().containsKey(newUser.getId())) {
            User oldUser = userStorage.findById(newUser.getId());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setLogin(newUser.getLogin());
            oldUser.setName(newUser.getName());
            oldUser.setBirthday(newUser.getBirthday());
            userStorage.save(newUser);
            return oldUser;
        }
        log.error("Пользователь с id = {} не найден", newUser.getId());
        throw new UserDoesNotExistException("Пользователь с id = " + newUser.getId() + " не найден");
    }

    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(userStorage.findById(userId));
    }

    public Collection<User> findAll() {
        return userStorage.findAll().values();
    }

    public void deleteById(Long id) {
        userStorage.deleteById(id);
    }

    public User addFriend(Long id, Long friendId) {
        if (!(userStorage.findAll().containsKey(id) && userStorage.findAll().containsKey(friendId))) {
            log.error("Отсутствует пользователь");
            throw new UserDoesNotExistException("Отсутствует пользователь");
        }
        User user = userStorage.findById(id);
        User userFriend = userStorage.findById(friendId);
        log.info("Пользователи: 1{},2{}", user, userFriend);
        userFriend.getFriends().add(id);
        user.getFriends().add(friendId);
        userStorage.save(userFriend);
        userStorage.save(user);
        log.info("Добавлен пользователь 1:{}, 2:{}", user.getFriends(), userFriend.getFriends());
        return user;
    }

    public User removeFriend(Long id, Long friendId) {
        if (!(userStorage.findAll().containsKey(id) && userStorage.findAll().containsKey(friendId))) {
            throw new UserDoesNotExistException("Отсутствует пользователь");
        }
        User user = userStorage.findById(id);
        User userFriend = userStorage.findById(friendId);
        log.info("Пользователи: 1{},2{}", user, userFriend);
        userFriend.getFriends().remove(id);
        user.getFriends().remove(friendId);
        userStorage.save(userFriend);
        userStorage.save(user);
        log.info("Список друзей после удаления: user: {}, userFriend: {}",
                user.getFriends(), userFriend.getFriends());
        return user;
    }

    public Collection<User> getAllFriends(Long id) {
        User user = userStorage.findById(id);
        if (user == null) {
            throw new UserDoesNotExistException("Такого пользователя нет");
        }
        Set<Long> userFriends = user.getFriends();
        log.info("Список друзей {}", userFriends);

        return userFriends.stream()
                .map(userStorage::findById)
                .collect(Collectors.toList());
    }

    public Collection<User> getMutualFriends(Long id, Long otherId) {
        User user = userStorage.findById(id);
        User userFriend = userStorage.findById(otherId);
        if (user == null || userFriend == null) {
            log.error("Нету одного из пользователей");
            throw new UserDoesNotExistException("Нету одного из пользователей ");
        }
        return user.getFriends().stream()
                .filter(idUser -> userFriend.getFriends().contains(idUser))
                .map(userStorage::findById)
                .collect(Collectors.toList());
    }

    private Long getNextId() {
        long currentMaxId = userStorage.findAll().keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
