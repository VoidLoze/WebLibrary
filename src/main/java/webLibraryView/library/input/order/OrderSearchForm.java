package webLibraryView.library.input.order;

import jakarta.validation.constraints.Min;

public record OrderSearchForm(
        String searchTerm,
        @Min(value = 0, message = "Страница должна быть больше 0")
        Integer page,
        @Min(value = 1, message = "Размер страницы должен быть больше 0")
        Integer size
) {
}
