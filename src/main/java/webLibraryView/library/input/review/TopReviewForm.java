package webLibraryView.library.input.review;

public record TopReviewForm(
        Integer bookId,
        String description,
        int rate
) {
}
