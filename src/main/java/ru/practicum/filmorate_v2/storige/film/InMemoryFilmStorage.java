package ru.practicum.filmorate_v2.storige.film;

import org.springframework.stereotype.Repository;
import ru.practicum.filmorate_v2.model.Film;

import java.util.Map;
@Repository
public class InMemoryFilmStorage implements FilmStorage {
    @Override
    public Film save(Film film) {
        return null;
    }

    @Override
    public Film findById(long userId) {
        return null;
    }

    @Override
    public Map<Long, Film> findAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
