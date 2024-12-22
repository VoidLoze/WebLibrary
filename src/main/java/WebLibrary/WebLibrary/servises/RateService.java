package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.RateDTO;

import java.util.List;

public interface RateService {
    RateDTO find(int id);
    int create(int rate, int bookId);
    List<RateDTO> findAll();
}
