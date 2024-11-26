package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storige.film.InMemoryFilmStorage;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService {
    private final InMemoryFilmStorage filmStorage;

    public Film save(Film film) {
        return filmStorage.save(film);
    }

    public Optional<Film> findById(long userId) {
        return Optional.ofNullable(filmStorage.findById(userId));
    }

    public Collection<Film> findAll() {
        return filmStorage.findAll().values();
    }

    public void deleteById(long id) {
        filmStorage.deleteById(id);
    }
}
