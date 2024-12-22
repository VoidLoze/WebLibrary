package webLibraryView.library.controllers.admin;


import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.input.Book.CreateBookForm;
import webLibraryView.library.input.Book.EditBookForm;

@RequestMapping("/admin/book")
public interface AdminBookController extends AdminBaseController{
    @GetMapping("/admin/create")
    String createForm(Model model);

    @PostMapping("/admin/create")
    String createBook(@Valid @ModelAttribute("form") CreateBookForm form,
                    BindingResult bindingResult,
                    Model model);

    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable int id,
            Model model
    );
    @PostMapping("/{id}/edit")
    String editBook(
            @PathVariable int id,
            @Valid @ModelAttribute("form") EditBookForm form,
            BindingResult bindingResult,
            Model model
    );
}
