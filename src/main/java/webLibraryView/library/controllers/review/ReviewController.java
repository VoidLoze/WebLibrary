package webLibraryView.library.controllers.review;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.review.CreateReviewForm;

@RequestMapping("/review")
public interface ReviewController extends BaseController {

    @GetMapping("/create")
    String createForm(Model model);

    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") CreateReviewForm form,
            BindingResult bindingResult,
            Model model);

    @GetMapping("/{id}")
    String listReview(
            @PathVariable int id,Model model
    );


//    @GetMapping("/{id}")
//    String details(
//            @PathVariable int id,
//            Model model
//    );

    @GetMapping("/top")
    String top(Model model);

}
