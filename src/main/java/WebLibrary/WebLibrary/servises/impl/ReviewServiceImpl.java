package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.domain.Review;
import WebLibrary.WebLibrary.dto.ReviewDTO;
import WebLibrary.WebLibrary.repository.BookRepository;
import WebLibrary.WebLibrary.repository.ReviewRepository;
import WebLibrary.WebLibrary.servises.ReviewService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@EnableCaching
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final EntityManager entityManager;
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public ReviewServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository, ModelMapper modelMapper, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    @CacheEvict(cacheNames = "review", allEntries = true)
    public int create(String description, int rate, int readerId,int bookId) {
        Reader reader = new Reader();
        reader.setId(readerId);

        Books book = new Books();
        book.setId(bookId);



        Review review = new Review(description, rate, reader, book);
        Review createdReview = reviewRepository.save(review);
        return createdReview.getId();

    }

    @Override
//    @Cacheable(value = "review", key = "'allReview'")
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map((review) -> modelMapper.map(review, ReviewDTO.class)).toList();
    }

    @Override
    public ReviewDTO find(int id) {
        Optional<Review> review = reviewRepository.findById(id);
        return modelMapper.map(review, ReviewDTO.class);
    }

//    @Override
//    public List<ReviewDTO> top() {
//        return null;
//    }

    @Override
    public List<Books> findTop3BooksByAverageRating() {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT b.id, AVG(r.rate) FROM Review r JOIN r.book b GROUP BY b.id ORDER BY AVG(r.rate) DESC", Object[].class);
        query.setMaxResults(3);
        List<Object[]> results = query.getResultList();

        // Extract book IDs.
        List<Integer> topBookIds = results.stream()
                .map(arr -> ((Number) arr[0]).intValue())
                .toList();

        // If there are top book IDs, fetch the books and calculate average ratings.
        List<Books> topBooks = new ArrayList<>();
        if (!topBookIds.isEmpty()) {
            topBooks = bookRepository.findByIdIsIn(topBookIds);
            for (Books book : topBooks) {
                book.setAverageRating(calculateAverageRating(book.getId()));
            }
        }
        return topBooks;
    }

    // Method to calculate average rating for a book
    private double calculateAverageRating(Integer bookId) {
        TypedQuery<Double> query = entityManager.createQuery(
                "SELECT AVG(r.rate) FROM Review r WHERE r.book.id = :bookId", Double.class);
        query.setParameter("bookId", bookId);
        try{
            return query.getSingleResult();
        } catch (NoResultException e){
            return 0.0; //Or handle the exception in a more appropriate way.
        }
    }
    @Override
    public List<Review> findByBookId(Integer bookId) {
        if (bookId == null) {
            return new ArrayList<>(); //Return empty list if bookId is null
        }

        TypedQuery<Review> query = entityManager.createQuery(
                "SELECT r FROM Review r WHERE r.book.id = :bookId", Review.class);
        query.setParameter("bookId", bookId);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>(); // Handle case where no reviews are found
        } catch (Exception e) {
            // Log the exception for debugging purposes
            // Handle exception more robustly in production (e.g., throw a custom exception, return a default value, or display a user-friendly error message)
            return new ArrayList<>();
        }
    }


    @Override
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    public List<Books> findTop3BooksByAverageRatingByFriends(Reader currentUser) {
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT b.id, AVG(r.rate) FROM Review r " +
                        "JOIN r.book b " +
                        "JOIN r.reader reviewReader " +
                        "JOIN Friend f ON (f.reader = reviewReader AND f.friendReader = :currentUser) " +
                        "WHERE reviewReader != :currentUser " +
                        "GROUP BY b.id ORDER BY AVG(r.rate) DESC",
                Object[].class);

        query.setParameter("currentUser", currentUser);
        query.setMaxResults(3);

        List<Object[]> results = query.getResultList();

        // Извлечь идентификаторы книг
        List<Integer> topBookIds = results.stream()
                .map(arr -> ((Number) arr[0]).intValue())
                .toList();

        // Если есть идентификаторы лучших книг, извлечь книги и вычислить средние оценки
        List<Books> topBooks = new ArrayList<>();
        if (!topBookIds.isEmpty()) {
            topBooks = bookRepository.findByIdIsIn(topBookIds);
            for (Books book : topBooks) {
                book.setAverageRating(calculateAverageRating(book.getId()));
            }
        }

        return topBooks;
    }

}



