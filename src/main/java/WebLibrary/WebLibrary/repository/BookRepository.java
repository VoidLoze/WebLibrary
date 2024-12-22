package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Books;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<Books> findById(int bookId);
    Books save(Books book);
    List<Books> findAll();
    List<Books> findByIdIsIn(List<Integer> ids);
//    @Query("SELECT b FROM Book b ORDER BY b.rating DESC")
//    List<BookDTO> findTopBooks();
}
