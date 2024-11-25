package ru.practicum.filmorate_v2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.filmorate_v2.service.FilmService;

@RestController
@RequestMapping("/film")
@RequiredArgsConstructor
@Slf4j
public class FilmController {
    private final FilmService service;
}
