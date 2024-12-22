package webLibraryView.library.input.order;

import java.time.LocalDate;

public record EditOrderForm(
        int id,
        LocalDate orderDate,
        Integer readerId,
        Integer bookId
) {
}
