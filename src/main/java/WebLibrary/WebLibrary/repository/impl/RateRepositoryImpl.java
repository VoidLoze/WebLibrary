package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Rate;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.RateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RateRepositoryImpl extends BaseRepository<Rate> implements RateRepository {
    public RateRepositoryImpl() {
        super(Rate.class);
    }

}
