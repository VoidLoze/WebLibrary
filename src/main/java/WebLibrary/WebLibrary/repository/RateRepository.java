package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Rate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository {
    Optional<Rate> findById(int rateId);
    Rate save(Rate rate);
    List<Rate> findAll();
}
