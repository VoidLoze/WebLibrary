package webLibraryView.library.controllers.book;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.Book.BookSearchForm;

@RequestMapping("/books")
public interface BookController extends BaseController {


    @GetMapping
    String listBooks(
            @ModelAttribute("form") BookSearchForm form,
            Model model
    );

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );
}
