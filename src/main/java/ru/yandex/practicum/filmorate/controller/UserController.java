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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveFilm(@Valid @RequestBody User user) {
        log.info("saveFilm(user)");
       return service.save(user);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User newUser) {
        log.info("updateUser(newUser)");
        return service.updateUser(newUser);
    }

    @GetMapping("/{id}")
    public Optional<User> findByIdUser(@Valid @PathVariable long id) {
        log.info("findByIdUser(id)");
        return service.findById(id);
    }

    @GetMapping
    public Collection<User> findAll() {
        log.info("findAll()");
        return service.findAll();
    }

    @GetMapping("/{id}")
    public void deleteByIdUser(@PathVariable long id) {
        service.deleteById(id);
    }

    @PutMapping(value = "/{id}/friends/{friendId}")
    public User addFriend(@NotNull @PathVariable long id,
                          @NotNull @PathVariable long friendId) {
        return service.addFriend(id, friendId);
    }

    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public User removeFriend(@NotNull @PathVariable long id,
                             @NotNull @PathVariable long friendId) {
        return service.removeFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public Collection<User> getAllFriends(@NotNull @PathVariable long id) {
        return service.getAllFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public Collection<User> getMutualFriends(@NotNull @PathVariable long id,
                                       @NotNull @PathVariable long otherId) {
        return service.getMutualFriends(id, otherId);
    }
}
