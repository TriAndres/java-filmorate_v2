package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
@Slf4j
public class FilmController {
    private final FilmService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film saveFilm(@Valid @RequestBody Film film) {
        log.info("createFilm(film)");
        return service.save(film);
    }

    @GetMapping("/{id}")
    public Optional<Film> findByIdFilm(@Valid @PathVariable long id) {
        log.info("findByIdFilm(id)");
        return service.findById(id);
    }

    @GetMapping
    public Collection<Film> findAllFilm() {
        log.info("findAllFilm()");
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteByIdFilm(@Valid @PathVariable long id) {
        log.info("deleteByIdFilm(id)");
        service.deleteById(id);
    }
}