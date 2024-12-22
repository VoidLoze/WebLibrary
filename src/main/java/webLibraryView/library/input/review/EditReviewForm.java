package webLibraryView.library.input.review;

public record EditReviewForm(
        int id,
        String description,
        int rate,
        Integer readerId,
        Integer bookId
) {
}
