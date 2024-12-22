package WebLibrary.WebLibrary.domain;

import java.util.List;

public class BookWithReviews {
    private Books book;
    private List<Review> reviews;

    public BookWithReviews(Books book, List<Review> reviews) {
        this.book = book;
        this.reviews = reviews;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}