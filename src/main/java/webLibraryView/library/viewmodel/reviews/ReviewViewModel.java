package webLibraryView.library.viewmodel.reviews;

public record ReviewViewModel(
        int id,
        String description,
        int rate,
        Integer readerId,
        Integer bookId
) {
}
