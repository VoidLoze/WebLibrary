package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Author;
import WebLibrary.WebLibrary.repository.AuthorRepository;
import WebLibrary.WebLibrary.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl extends BaseRepository<Author> implements AuthorRepository {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }
}
