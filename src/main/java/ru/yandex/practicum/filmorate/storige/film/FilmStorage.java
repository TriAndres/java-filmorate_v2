package ru.yandex.practicum.filmorate.storige.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {
    Film save(Film film);

    Film findById(Long userId);

    Map<Long, Film> findAll();

    void deleteById(Long id);
}
