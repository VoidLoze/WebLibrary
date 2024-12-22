package WebLibrary.WebLibrary.controller.admin;

import WebLibrary.WebLibrary.servises.AuthorService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.admin.AdminAuthorController;
import webLibraryView.library.input.author.CreateAuthorForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.author.AuthorCreateViewModel;

@Controller
@RequestMapping("/admin/author")
public class AdminAuthorControllerImpl implements AdminAuthorController {
    private final AuthorService authorService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public AdminAuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var viewModel = new AuthorCreateViewModel(
                createBaseViewModel("Создание автора"));
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateAuthorForm("","",""));
        return "/author/create";
    }

    @Override
    @PostMapping("/create")
    public String createAuthor(@Valid @ModelAttribute("form") CreateAuthorForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new AuthorCreateViewModel(
                    createBaseViewModel("Создание автора")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "/author/create";
        }

        var id = authorService.create(form.firstName(),form.lastName(),form.dateOfBorn());
        LOG.log(Level.INFO, "Admin create author"+id);
        return "redirect:/author/" + id;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
