package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO find(int Id);
    int create(String name, int authorId, int genreId, String yearRealise);
    List<BookDTO> findAll();
    void editBook(int id,String name, int authorId, int genreId, String yearRealise);
//    List<BookDTO> findTopBooks();
}
