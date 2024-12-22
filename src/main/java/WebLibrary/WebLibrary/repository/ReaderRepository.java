package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Reader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository {
    Optional<Reader> findById(int readerId);
    Reader save(Reader reader);
    List<Reader> findAll();
}
