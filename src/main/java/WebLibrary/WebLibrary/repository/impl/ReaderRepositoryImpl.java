package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.ReaderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderRepositoryImpl extends BaseRepository<Reader> implements ReaderRepository {
    public ReaderRepositoryImpl() {
        super(Reader.class);
    }

}
