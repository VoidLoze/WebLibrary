package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Friend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository {
    Optional<Friend> findById(int friendId);
    Friend save(Friend friend);
    List<Friend> findAll();
    boolean existsByReaderIdAndFriendReaderId(int readerId, int friendReaderId);
    List<Friend> findAllFriendsByReader(int reader);
    void deleteById(int id);
    int countByReaderId(int readerId);
}
