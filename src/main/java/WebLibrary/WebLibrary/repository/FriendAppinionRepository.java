package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.FriendAppinion;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendAppinionRepository {
    Optional<FriendAppinion> findById(int friendAppinionId);
}
