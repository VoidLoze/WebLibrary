package webLibraryView.library.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.author.CreateAuthorForm;

@RequestMapping("/admin/author")
public interface AdminAuthorController extends AdminBaseController{
    @GetMapping("/admin/create")
    String createForm(Model model);

    @PostMapping("/admin/create")
    String createAuthor(
            @Valid @ModelAttribute("form") CreateAuthorForm form,
            BindingResult bindingResult,
            Model model);
}
