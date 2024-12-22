package webLibraryView.library.input.order;

import java.time.LocalDate;

public record CreateOrderForm(
        LocalDate orderDate,
        Integer readerId,
        Integer bookId
) {
}
