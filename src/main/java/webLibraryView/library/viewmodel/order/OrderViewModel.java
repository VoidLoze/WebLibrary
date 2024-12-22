package webLibraryView.library.viewmodel.order;

import java.time.LocalDate;

public record OrderViewModel(
        int id,
        LocalDate orderDate,
        Integer readerId,
        Integer bookId
) {
}
