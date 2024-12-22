package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository {
    Optional<Genre> findById(int genreId);
    Genre save(Genre genre);
    List<Genre> findAll();
}
