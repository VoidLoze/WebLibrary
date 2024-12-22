package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.ReaderDTO;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
    ReaderDTO find(int id);
    int create(String firstName, String lastName, int age,String phoneNumber);
    List<ReaderDTO> findAll();
    void editReader(int id, String name, String lastName, int age, String phoneNumber);

    Optional<Reader> findByEmail(String email);
    Reader findByEmailReader(String email);
    List<Reader> findAllReadersExcept(int loggedInReaderId);
//    Reader getCurrentUser(Principal principal);
    ReaderDTO getUserProfile(String username);
}
