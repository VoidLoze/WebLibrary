package webLibraryView.library.input.Book;

import jakarta.validation.constraints.NotBlank;

public record CreateBookForm(
        @NotBlank (message = "Название обязательно") String name,
        Integer authorId,
        Integer genreId,
        @NotBlank (message = "Год выпуска обязателен")String yearRealise

) {}
