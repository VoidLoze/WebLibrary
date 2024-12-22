package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Friend;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.FriendRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class FriendRepositoryImpl extends BaseRepository<Friend> implements FriendRepository {
    @PersistenceContext
    private EntityManager entityManager;



    public FriendRepositoryImpl() {
        super(Friend.class);
    }

    @Override
    public boolean existsByReaderIdAndFriendReaderId(int readerId, int friendReaderId) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(f) FROM Friend f WHERE f.reader.id = :readerId AND f.friendReader.id = :friendReaderId", Long.class);

        query.setParameter("readerId", readerId);
        query.setParameter("friendReaderId", friendReaderId);

        Long count = query.getSingleResult();

        return count > 0;
    }

    @Override
    public List<Friend> findAllFriendsByReader(int reader) {
        // Create a TypedQuery with JOIN FETCH to avoid lazy loading issues
        TypedQuery<Friend> query = entityManager.createQuery(
                "SELECT f FROM Friend f JOIN FETCH f.reader r WHERE r.id = :readerId", Friend.class);
        query.setParameter("readerId", reader); // Set the reader's ID in the query parameter

        // Execute the query and return the result list
        return query.getResultList();
    }

    @Override
    public int countByReaderId(int readerId) {
        String jpql = "SELECT COUNT(f) FROM Friend f WHERE f.reader.id = :readerId"; // Предполагаем, что у вас есть связь с сущностью Reader
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("readerId", readerId)
                .getSingleResult();
        return count != null ? count.intValue() : 0; // Преобразуем Long в int
    }



}
