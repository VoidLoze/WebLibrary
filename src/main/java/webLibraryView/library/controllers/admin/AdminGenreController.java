package webLibraryView.library.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.genre.CreateGenreForm;


@RequestMapping("/admin/genre")
public interface AdminGenreController extends AdminBaseController{
    @GetMapping("/create")
    String createForm(Model model);

    @PostMapping("/create")
    String createGenre(@Valid @ModelAttribute("form") CreateGenreForm form,
                      BindingResult bindingResult,
                      Model model);

}
