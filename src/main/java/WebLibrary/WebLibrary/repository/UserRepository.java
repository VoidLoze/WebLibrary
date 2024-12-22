package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByEmail(String email);
    Optional<Reader> findByFirstName(String username);

}

