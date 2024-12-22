package webLibraryView.library.controllers.genre;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.genre.GenreSearchForm;

@RequestMapping("/genres")
public interface GenreController extends BaseController {

    @GetMapping
    String listGenres(
            @ModelAttribute("form") GenreSearchForm form,
            Model model
    );

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );
}
