package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl extends BaseRepository<Books> implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public BookRepositoryImpl() {
        super(Books.class);
    }

    public List<Books> findByIdIsIn(List<Integer> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>(); // Return empty list if ids is empty
        }
        // Construct JPQL string carefully (avoid SQL injection)
        String jpql = "SELECT b FROM Books b WHERE b.id IN :ids";
        TypedQuery<Books> query = entityManager.createQuery(jpql, Books.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }
}
