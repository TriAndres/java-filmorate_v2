package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;
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
}
