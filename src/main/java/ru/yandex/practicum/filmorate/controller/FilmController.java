package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;
import java.util.List;
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

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film newFilm) {
        log.info("updateFilm(newFilm)");
        return service.updateFilm(newFilm);
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

    @PutMapping(value = "/{id}/like/{userId}")
    public Film addLike(@NotNull @PathVariable long id,
                        @NotNull @PathVariable long userId) {
        return service.addLike(id, userId);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public Film deleteLike(@NotNull @PathVariable long id,
                           @NotNull @PathVariable long userId) {
        return service.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(
            @RequestParam(required = false, defaultValue = "10") int count) {
        return service.getPopular(count);
    }
}