package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmDoesNotExistException;
import ru.yandex.practicum.filmorate.exception.UserDoesNotExistException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storige.film.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storige.user.InMemoryUserStorage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmService {
    private final InMemoryFilmStorage filmStorage;
    private final InMemoryUserStorage userStorage;

    public Film save(Film film) {
        film.setId(getNextId());
        filmStorage.save(film);
        return film;
    }

    public Film updateFilm(Film newFilm) {
        if (newFilm.getId() == null) {
            log.error("Не указан id");
            throw new ValidationException("Id должен быть указан");
        }
        if (filmStorage.findAll().containsKey(newFilm.getId())) {
            Film oldFilm = filmStorage.findById(newFilm.getId());
            oldFilm.setName(newFilm.getName());
            oldFilm.setDescription(newFilm.getDescription());
            oldFilm.setReleaseDate(newFilm.getReleaseDate());
            oldFilm.setDuration(newFilm.getDuration());
            filmStorage.save(oldFilm);
            return oldFilm;
        }
        log.error("Фильм с id = {} не найден", newFilm.getId());
        throw new FilmDoesNotExistException("Фильм с id = " + newFilm.getId() + " не найден");

    }

    public Optional<Film> findById(long userId) {
        if (filmStorage.findAll().containsKey(userId)) {
            return Optional.ofNullable(filmStorage.findById(userId));
        }
        log.error("Фильм с id = {} не найден", userId);
        throw new FilmDoesNotExistException("Фильм с id = " + userId + " не найден");
    }

    public Collection<Film> findAll() {
        return filmStorage.findAll().values();
    }

    public void deleteById(long id) {
        filmStorage.deleteById(id);
    }

    public Film addLike(long id, long userId) {
        if (!filmStorage.findAll().containsKey(id)) {
            log.error("Отсутствует фильм");
            throw new FilmDoesNotExistException("Отсутствует фильм");
        }
        if (!userStorage.findAll().containsKey(userId)) {
            log.error("Отсутствует пользователь");
            throw new FilmDoesNotExistException("Отсутствует пользователь");
        }
        Film film = filmStorage.findById(id);
        film.addLike(userId);
        filmStorage.save(film);
        log.info("Лайк добавлен {}", film.getLikes());
        return film;
    }

    public Film deleteLike(long id, long userId) {
        Film film = filmStorage.findById(id);
        User user = userStorage.findById(userId);
        if (film == null || user == null) {
            log.error("Отсутствует пользователь");
            throw new UserDoesNotExistException("Отсутствует пользователь");
        }
        film.deleteLike(userId);
        log.info("Лайк удален {}", film.getLikes());
        return film;
    }

    public List<Film> getPopular(int count) {
        return filmStorage.findAll().values().stream()
                .filter(film -> film.getLikes() != null)
                .sorted((f1, f2) -> Integer.compare(f2.getLikes().size(), f1.getLikes().size()))
                .limit(count)
                .collect(Collectors.toList());
    }

    private Long getNextId() {
        long currentMaxId = filmStorage.findAll().keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}