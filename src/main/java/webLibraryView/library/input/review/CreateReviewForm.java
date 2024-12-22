package webLibraryView.library.input.review;

public record CreateReviewForm(
        String description,
        int rate,
        Integer readerId,
        Integer bookId
) {
}
