package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
     AuthorDTO find(int id);
     int create(String firstName, String lastName, String dateOfBorn);
     List<AuthorDTO> findAll();
}
