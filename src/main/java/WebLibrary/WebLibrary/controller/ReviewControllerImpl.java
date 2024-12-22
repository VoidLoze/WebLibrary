package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.domain.Review;
import WebLibrary.WebLibrary.dto.BookDTO;
import WebLibrary.WebLibrary.servises.BookService;
import WebLibrary.WebLibrary.servises.ReaderService;
import WebLibrary.WebLibrary.servises.ReviewService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.review.ReviewController;
import webLibraryView.library.input.review.CreateReviewForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.reviews.ReviewCreateViewModel;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;
    private final ReaderService readerService;
    private final BookService bookService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    public ReviewControllerImpl(ReviewService reviewService, ReaderService readerService, BookService bookService) {
        this.reviewService = reviewService;
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var viewModel = new ReviewCreateViewModel(
                createBaseViewModel("Создание отзыва"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<Reader> currentUser = readerService.findByEmail(currentUsername);
        Reader readers = currentUser.orElse(null);

        List<BookDTO> books = bookService.findAll();
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateReviewForm("",0,0,0));
        model.addAttribute("books", books);
        model.addAttribute("readers", readers);
        return "review/create";
    }

    @Override
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("form") CreateReviewForm form,
                         BindingResult bindingResult,
                         Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<Reader> currentUserOptional = readerService.findByEmail(currentUsername);

        if (currentUserOptional.isEmpty()) {
            model.addAttribute("error", "User not found. Please log in."); //Or redirect
            return "review/create";
        }
        Reader currentUser = currentUserOptional.get();
        int readers = currentUser.getId();
        if (bindingResult.hasErrors()) {


            List<BookDTO> books = bookService.findAll();
            var viewModel = new ReviewCreateViewModel(
                    createBaseViewModel("Создание отзыва")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            model.addAttribute("readers", readers);
            model.addAttribute("books", books);
            return "review/create";
        }
        try {
            int reviewId = reviewService.create(form.description(), form.rate(),readers, form.bookId());
            LOG.log(Level.INFO, "create order"+reviewId);
            return "redirect:/book";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "review/create";
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "review/create";
        }
    }

    @GetMapping("/top")
    public String top(Model model) {
        var viewModel = new ReviewCreateViewModel(
                createBaseViewModel("Создание отзыва"));
        List<Books> topBooks = reviewService.findTop3BooksByAverageRating();
        model.addAttribute("topBooks", topBooks);
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "open top");
        return "review/reviewTop"; // Thymeleaf template name
    }

    @GetMapping("/{id}")
    public String listReview(int id, Model model) {
        var viewModel = new ReviewCreateViewModel(
                createBaseViewModel("Отзывы книги"));
        List<Review> reviews = reviewService.findByBookId(id);
        model.addAttribute("reviews", reviews);
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "Open review list");
        return "review/list"; // Thymeleaf template name
    }


//    @Override
//    @GetMapping("/{id}")
//    public String details(int id, Model model) {
//        var review = reviewService.find(id);
//        var viewModel = new ReviewDetailsViewModel(
//                createBaseViewModel("Детали отзыва"),
//                new ReviewViewModel(review.getId(),
//                        review.getDescription(),
//                        review.getRate(),
//                        review.getReaderId(),
//                        review.getBookId())
//        );
//        model.addAttribute("model", viewModel);
//        return "review/details";
//    }



    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

//    @Override
//    @GetMapping()
//    public String listReview(ReviewSearchForm form, Model model) {
//        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
//        var page = form.page() != null ? form.page() : 1;
//        var size = form.size() != null ? form.size() : 3;
//        form = new ReviewSearchForm(searchTerm, page, size);
//
//        List<ReviewDTO> allReviews = reviewService.findAll();
//
//        List<ReviewDTO> filteredReview = allReviews.stream()
//                .filter(r -> r.getDescription().toLowerCase().contains(searchTerm.toLowerCase()))
//                .collect(Collectors.toList());
//
//        int totalReview = filteredReview.size();
//        int start = (page - 1) * size;
//        int end = Math.min(start + size, totalReview);
//        List<ReviewDTO> paginatedReviews = filteredReview.subList(start, end);
//
//        var reviewViewModels = paginatedReviews.stream()
//                .map(f -> new ReviewViewModel(f.getId(),f.getDescription(),f.getRate(),f.getReaderId(),f.getBookId()))
//                .toList();
//
//        var viewModel = new ReviewListViewModel(
//                createBaseViewModel("Список отзывов"),
//                reviewViewModels,
//                (int) Math.ceil((double) totalReview / size)
//        );
//
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", form);
//        return "review/list";
//    }



//    @Override
//    @GetMapping("/top")
//    public String top(Model model) {
//        // Получаем лучшие книги по рейтингу
//        List<BookDTO> topBooks = bookService.findTopBooks(); // Предполагается, что у вас есть метод findTopBooks в BookService
//
//        List<TopReviewViemModel> topBookViewModels = new ArrayList<>();
//
//        for (BookDTO book : topBooks) {
//            // Получаем последние 3 отзыва для каждой книги
//            List<ReviewDTO> latestReviews = reviewService.findLatestReviewsByBookId(book.getId(), 3); // Предполагается, что у вас есть метод findLatestReviewsByBookId в ReviewService
//
//            Book book = new Book();
//
//            var topReviewViemModel viewModel = new TopReviewViemModel();
//            viewModel.setBookId(book.getId());
//            viewModel.setLatestReviews(latestReviews.stream()
//                    .map(r -> new ReviewViewModel(r.getId(), r.getDescription(), r.getRate(), r.getReader(), r.getBookId()))
//                    .toList());
//
//            topBookViewModels.add(viewModel);
//        }
//
//        var viewModel = new TopBooksViewModel(
//                createBaseViewModel("Топ книг"),
//                TopReviewViemModel
//        );
//
//        model.addAttribute("model", viewModel);
//        return "review/top"; // Предполагается, что у вас есть шаблон review/top.html
//    }

//    @Override
//    @GetMapping("/top")
//    public String top(@PathVariable int bookId, int limit) {
//        return reviewService.findLatestReviewsByBookId(bookId, limit).toString();
//    }

}
