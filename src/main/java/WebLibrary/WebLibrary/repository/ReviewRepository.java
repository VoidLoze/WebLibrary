package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository {
    Optional<Review> findById(int reviewId);
    Review save(Review review);
    void deleteById(int id);
    List<Review> findAll();
//    @Query("SELECT b FROM Book b ORDER BY b.rating DESC+ SELECT r FROM Review r WHERE r.book.id = :bookId ORDER BY r.id DESC")
//    List<ReviewDTO> findLatestByBookId(@Param("bookId") int bookId, int pageable);
    List<Review> findTop3ReviewsByBookId(int bookId);
    List<Review> findByBookId( Integer bookId);

}
