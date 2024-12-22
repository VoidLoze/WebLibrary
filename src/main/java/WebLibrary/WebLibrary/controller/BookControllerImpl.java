package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.domain.Review;
import WebLibrary.WebLibrary.dto.BookDTO;
import WebLibrary.WebLibrary.servises.BookService;
import WebLibrary.WebLibrary.servises.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.book.BookController;
import webLibraryView.library.input.Book.BookSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.books.BookDetailsViewModel;
import webLibraryView.library.viewmodel.books.BookListViewModel;
import webLibraryView.library.viewmodel.books.BookViewModel;
import webLibraryView.library.viewmodel.reviews.ReviewCreateViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private final BookService bookService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    @Autowired
    private final ReviewService reviewService;

    public BookControllerImpl(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }


    @Override
    @GetMapping()
    public String listBooks(@ModelAttribute("form") BookSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new BookSearchForm(searchTerm, page, size);

        List<BookDTO> allBooks = bookService.findAll();

        List<BookDTO> filteredBook = allBooks.stream()
                .filter(r -> r.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        int totalBook = filteredBook.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalBook);
        List<BookDTO> paginatedBooks = filteredBook.subList(start, end);

        var bookViewModels = paginatedBooks.stream()
                .map(f -> new BookViewModel(f.getId(),f.getName(),f.getAuthorId(),f.getGenreId(),f.getYearRealise()))
                .toList();

        var viewModel = new BookListViewModel(
                createBaseViewModel("Список книг"),
                bookViewModels,
                (int) Math.ceil((double) totalBook / size)
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        LOG.log(Level.INFO, "Open book list");
        return "book/list";
    }
    @GetMapping("/{id}")
    @Override
    public String details(int id, Model model) {
        var book = bookService.find(id);
        var viewModel = new BookDetailsViewModel(
                createBaseViewModel("Детали книги"),
                new BookViewModel(book.getId(),
                        book.getName(),
                        book.getAuthorId(),
                        book.getGenreId(),
                        book.getYearRealise())
        );
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "Open book" + id + "details");
        return "book/details";
    }
    @GetMapping("/{id}/reviews")
    public String showBookReviews(@PathVariable int id, Model model) {
        try {
            List<Review> reviews = reviewService.findByBookId(id);
            var viewModel = new ReviewCreateViewModel(
                    createBaseViewModel("Список")
            );// Make sure this returns a non-null list
            model.addAttribute("reviews", reviews);
            model.addAttribute("model", viewModel);
            model.addAttribute("bookId", id);
            LOG.log(Level.INFO, "Open book" + id + "review");
            return "book/bookReview";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error retrieving reviews: " + e.getMessage());
            return "error";
        }
    }
}
