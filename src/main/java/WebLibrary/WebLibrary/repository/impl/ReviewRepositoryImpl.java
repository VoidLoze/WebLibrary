package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Review;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends BaseRepository<Review> implements ReviewRepository {

    @PersistenceContext
    private EntityManager entityManager;
    public ReviewRepositoryImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> findTop3ReviewsByBookId(int bookId) {
        return entityManager.createQuery(
                        "SELECT b.id, AVG(r.rate) AS average_rating FROM Review r JOIN r.book b GROUP BY b.id ORDER BY average_rating DESC", Review.class)
                .setParameter("bookId", bookId) // Устанавливаем параметр bookId
                .setMaxResults(3) // Ограничиваем количество результатов до 3
                .getResultList();
    }

    @Override
    public List<Review> findByBookId(Integer bookId) {
        return entityManager.createQuery(
                        "SELECT r FROM Review r WHERE r.book.id = :bookId", Review.class)
                .setParameter("bookId", bookId) // Устанавливаем параметр bookId

                .getResultList();
    }
}
