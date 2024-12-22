package WebLibrary.WebLibrary.controller.admin;

import WebLibrary.WebLibrary.dto.ReviewDTO;
import WebLibrary.WebLibrary.servises.ReviewService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.admin.AdminReviewController;
import webLibraryView.library.input.review.ReviewSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.reviews.ReviewListViewModel;
import webLibraryView.library.viewmodel.reviews.ReviewViewModel;

import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/admin/review")
public class AdminReviewControllerImpl implements AdminReviewController {
    private final ReviewService reviewService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public AdminReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    @GetMapping()
    public String listReview(ReviewSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new ReviewSearchForm(searchTerm, page, size);

        List<ReviewDTO> allReviews = reviewService.findAll();

        List<ReviewDTO> filteredReview = allReviews.stream()
                .filter(r -> r.getDescription().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        int totalReview = filteredReview.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalReview);
        List<ReviewDTO> paginatedReviews = filteredReview.subList(start, end);

        var reviewViewModels = paginatedReviews.stream()
                .map(f -> new ReviewViewModel(f.getId(),f.getDescription(),f.getRate(),f.getReaderId(),f.getBookId()))
                .toList();

        var viewModel = new ReviewListViewModel(
                createBaseViewModel("Список отзывов"),
                reviewViewModels,
                (int) Math.ceil((double) totalReview / size)
        );
        List<ReviewDTO> reviews = reviewService.findAll(); // Получаем все отзывы
        model.addAttribute("reviews", reviews); // Добавляем список отзывов в модель
        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        LOG.log(Level.INFO, "Admin open review list");
        return "review/adminList";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        LOG.log(Level.INFO, "Admin delete review"+id);
        return "redirect:/admin/review";
    }
}
