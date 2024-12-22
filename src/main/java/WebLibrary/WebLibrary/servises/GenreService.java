package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO find(int id);
    int create(String nameOfGenre, String description);
    List<GenreDTO> findAll();
}
