package webLibraryView.library.input.review;


import jakarta.validation.constraints.Min;

public record ReviewSearchForm(
        String searchTerm,
        @Min(value = 0, message = "Страница должна быть больше 0")
        Integer page,
        @Min(value = 1, message = "Размер страницы должен быть больше 0")
        Integer size
) {
}
