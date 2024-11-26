package ru.yandex.practicum.filmorate.storige.film;

import org.springframework.stereotype.Repository;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> filmMap = new HashMap<>();

    @Override
    public Film save(Film film) {
        filmMap.put(film.getId(), film);
        return film;
    }

    @Override
    public Film findById(Long userId) {
        return filmMap.get(userId);
    }

    @Override
    public Map<Long, Film> findAll() {
        return filmMap;
    }

    @Override
    public void deleteById(Long id) {
        filmMap.remove(id);
    }
}
