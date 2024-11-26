package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @GetMapping
    public Collection<User> findAll() {
        log.info("findAll()");
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveFilm(@Valid @RequestBody User user) {
        log.info("saveFilm(user)");
        service.save(user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User newUser) {
        log.info("updateUser(newUser)");
        return service.updateUser(newUser);
    }

    @GetMapping("/{id}")
    public Optional<User> findByIdUser(@Valid @PathVariable Long id) {
        log.info("findByIdUser(/{id})");
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteByIdUser(@Valid @PathVariable Long id) {
        log.info("findAll(/{id})");
        service.deleteById(id);
    }

    @PutMapping(value = "/{id}/friends/{friendId}")
    public User addFriend(@NotNull @PathVariable Long id,
                          @NotNull @PathVariable Long friendId) {
        log.info("addFriend(/{id}/friends/{friendId})");
        return service.addFriend(id, friendId);
    }

    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public User removeFriend(@NotNull @PathVariable Long id,
                             @NotNull @PathVariable Long friendId) {
        log.info("removeFriend(/{id}/friends/{friendId})");
        return service.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public Collection<User> getAllFriends(@NotNull @PathVariable Long id) {
        log.info("getAllFriends(/{id}/friends)");
        return service.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> getMutualFriends(@NotNull @PathVariable Long id,
                                             @NotNull @PathVariable Long otherId) {
        log.info("getMutualFriends(/{id}/friends/common/{otherId})");
        return service.getMutualFriends(id, otherId);
    }
}
