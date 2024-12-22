package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Author;
import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Genre;
import WebLibrary.WebLibrary.dto.BookDTO;
import WebLibrary.WebLibrary.repository.BookRepository;
import WebLibrary.WebLibrary.servises.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDTO find(int id) {
        Optional<Books> book = bookRepository.findById(id);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    @CacheEvict(cacheNames = "books", allEntries = true)
    public int create(String name, int authorId, int genreId, String yearRealise) {
        Author author = new Author();
        author.setId(authorId);

        Genre genre = new Genre();
        genre.setId(genreId);


        Books book = new Books(name, genre, yearRealise, author);
        Books createdBook = bookRepository.save(book);
        return createdBook.getId();
    }

    @Override
    @Cacheable(value = "books", key = "'allBooks'")
    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map((book) -> modelMapper.map(book, BookDTO.class)).toList();
    }

    @Override
    public void editBook(int id, String name, int authorId, int genreId, String yearRealise) {
        Author author = new Author();
        author.setId(authorId);

        Genre genre = new Genre();
        genre.setId(genreId);

        var book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
        book.setId(id);
        book.setName(name);
        book.setAuthorId(author);
        book.setGenreId(genre);
        book.setYearRealise(yearRealise);
    }
}
//        public List<BookDTO> findTopBooks() {
//            // Здесь вы можете реализовать логику для получения лучших книг по рейтингу
//            return bookRepository.findTopBooks();
//        }



//    public List<BookDTO> findTopBooks() {
//        // Здесь вы можете реализовать логику для получения лучших книг по рейтингу
//        return bookRepository.findTopBooks();
//    }














//package WebLibrary.WebLibrary.servises.impl;
//
//        import WebLibrary.WebLibrary.domain.*;
//        import WebLibrary.WebLibrary.dto.BookDTO;
//        import WebLibrary.WebLibrary.repository.BookRepository;
//        import WebLibrary.WebLibrary.servises.BookService;
//        import org.modelmapper.ModelMapper;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.stereotype.Service;
//
//        import java.util.List;
//        import java.util.Optional;
//
//@Service
//public class BookServiceImpl implements BookService {
//    @Autowired
//    private final BookRepository bookRepository;
//    @Autowired
//    private final ModelMapper modelMapper;
//
//    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
//        this.bookRepository = bookRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public BookDTO find(int id) {
//        Optional<Book> book = bookRepository.findById(id);
//        return modelMapper.map(book, BookDTO.class);
//    }
//    @Override
//    public int create(String name, int authorId, int genreId, String yearRealise){
//        Author author = new Author();
//        author.setId(authorId);
//
//        Genre genre = new Genre();
//        genre.setId(genreId);
//
//
//        Book book = new Book(name, genre, yearRealise, author);
//        Book createdBook = bookRepository.save(book);
//        return createdBook.getId();
//    }
//    @Override
//    public List<BookDTO> findAll() {
//        return bookRepository.findAll().stream().map((book) -> modelMapper.map(book, BookDTO.class)).toList();
//    }
//    @Override
//    public void editBook(int id,String name, int authorId, int genreId, String yearRealise) {
//        Author author = new Author();
//        author.setId(authorId);
//
//        Genre genre = new Genre();
//        genre.setId(genreId);
//
//        var book = bookRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Книга не найдена"));
//        book.setId(id);
//        book.setName(name);
//        book.setAuthorId(author);
//        book.setGenreId(genre);
//        book.setYearRealise(yearRealise);
//    }