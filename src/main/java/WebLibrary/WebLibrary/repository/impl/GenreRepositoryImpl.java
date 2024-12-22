package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Genre;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.GenreRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryImpl extends BaseRepository<Genre> implements GenreRepository {
    public GenreRepositoryImpl() {
        super(Genre.class);
    }
}
