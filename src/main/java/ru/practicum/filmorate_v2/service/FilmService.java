package ru.practicum.filmorate_v2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.filmorate_v2.model.Film;
import ru.practicum.filmorate_v2.storige.film.InMemoryFilmStorage;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService {
    private final InMemoryFilmStorage filmStorage;

    public Film save(Film film) {
        return null;
    }

    public Film findById(long userId) {
        return null;
    }

    public Map<Long, Film> findAll() {
        return null;
    }

    public void deleteById(long id) {

    }
}
