package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.domain.Review;
import WebLibrary.WebLibrary.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO find(int id);
    int create(String description, int rate, int readerId, int bookId);
    List<ReviewDTO> findAll();
//    List<ReviewDTO> top();
    List<Books> findTop3BooksByAverageRating();
    List<Review> findByBookId(Integer bookId);
    void deleteReview(Integer id);

    List<Books> findTop3BooksByAverageRatingByFriends(Reader currentUser);

}
