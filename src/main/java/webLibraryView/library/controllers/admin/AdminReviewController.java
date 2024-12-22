package webLibraryView.library.controllers.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.review.ReviewSearchForm;

@RequestMapping("/admin/review")
public interface AdminReviewController extends AdminBaseController{

    @GetMapping
    String listReview(
            @ModelAttribute("form") ReviewSearchForm form,
            Model model
    );

//    @GetMapping("/{id}/delete")
//    String delete(Integer id);
}
