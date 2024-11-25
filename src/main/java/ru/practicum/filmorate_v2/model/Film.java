package ru.practicum.filmorate_v2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.practicum.filmorate_v2.validator.ReleaseDateValidation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Film {
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String description;
    @NotNull
    @ReleaseDateValidation
    private LocalDate releaseDate;
    @NotNull
    @Positive
    private Integer duration;
    private final Set<Long> likes = new HashSet<>();
}
