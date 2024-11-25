package ru.practicum.filmorate_v2.storige.film;

import ru.practicum.filmorate_v2.model.Film;

import java.util.Map;

public interface FilmStorage {
    Film save(Film film);

    Film findById(long userId);

    Map<Long, Film> findAll();

    void deleteById(long id);
}
