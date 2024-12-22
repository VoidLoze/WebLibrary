package webLibraryView.library.controllers.author;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.author.AuthorSearchForm;

@RequestMapping("/authors")
public interface AuthorController extends BaseController {

    @GetMapping
    String listAuthors(
            @ModelAttribute("form") AuthorSearchForm form,
            Model model
    );

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );
}