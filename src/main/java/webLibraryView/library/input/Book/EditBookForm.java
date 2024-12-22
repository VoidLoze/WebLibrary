package webLibraryView.library.input.Book;

import jakarta.validation.constraints.NotBlank;

public record EditBookForm(
        int id,
        @NotBlank(message = "Название обязательно") String name,
        @NotBlank (message = "Автор обязателен")Integer authorId,
        @NotBlank (message = "Жанр обязателен")Integer genreId,
        @NotBlank (message = "Год выпуска обязателен")String yearRealise
) {}
