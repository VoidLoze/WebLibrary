package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository {
    Optional<Author> findById(int authorId);
    Author save(Author author);
    List<Author> findAll();
}
