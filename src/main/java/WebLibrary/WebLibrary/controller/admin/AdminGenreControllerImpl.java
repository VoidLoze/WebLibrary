package WebLibrary.WebLibrary.controller.admin;

import WebLibrary.WebLibrary.servises.GenreService;
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
import webLibraryView.library.controllers.admin.AdminGenreController;
import webLibraryView.library.input.genre.CreateGenreForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.genre.GenreCreateViewModel;
@Controller
@RequestMapping("/admin/genre")
public class AdminGenreControllerImpl implements AdminGenreController {
    private final GenreService genreService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public AdminGenreControllerImpl(GenreService genreService) {
        this.genreService = genreService;
    }


    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return null;
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var baseViewModel = createBaseViewModel("Создание жанра");
        var viewModel = new GenreCreateViewModel(baseViewModel);
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateGenreForm("", ""));
        return "genre/create";
    }

    @Override
    @PostMapping("/create")
    public String createGenre(@Valid @ModelAttribute("form") CreateGenreForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new GenreCreateViewModel(
                    createBaseViewModel("Создание жанра")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "genre/create";
        }

        var id = genreService.create(form.nameOfGenre(),form.description());
        LOG.log(Level.INFO, "Admin create genre"+id);
        return "redirect:/genre/" + id;
    }
}
