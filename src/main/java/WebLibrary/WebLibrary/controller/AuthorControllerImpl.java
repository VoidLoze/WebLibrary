package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.dto.AuthorDTO;
import WebLibrary.WebLibrary.servises.AuthorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.author.AuthorController;
import webLibraryView.library.input.author.AuthorSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.author.AuthorDetailsViewModel;
import webLibraryView.library.viewmodel.author.AuthorListViewModel;
import webLibraryView.library.viewmodel.author.AuthorViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/author")
public class AuthorControllerImpl implements AuthorController {
    private final AuthorService authorService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public AuthorControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

    @Override
    @GetMapping("/list")
    public String listAuthors(@ModelAttribute("form") AuthorSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new AuthorSearchForm(searchTerm, page, size);

        List<AuthorDTO> allAuthors = authorService.findAll();

        List<AuthorDTO> filteredAuthor = allAuthors.stream()
                .filter(r -> r.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        int totalAuthor = filteredAuthor.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalAuthor);
        List<AuthorDTO> paginatedAuthors = filteredAuthor.subList(start, end);

        var authorViewModels = paginatedAuthors.stream()
                .map(f -> new AuthorViewModel(f.getId(),f.getFirstName(),f.getLastName(),f.getDateOfBorn()))
                .toList();

        var viewModel = new AuthorListViewModel(
                createBaseViewModel("Список авторов"),
                authorViewModels,
                (int) Math.ceil((double) totalAuthor / size)
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        LOG.log(Level.INFO, "Open list author");
        return "author/list";
    }

    @Override
    @GetMapping("/{id}")
    public String details(int id, Model model) {
        var author = authorService.find(id);
        var viewModel = new AuthorDetailsViewModel(
                createBaseViewModel("Детали автора"),
                new AuthorViewModel(author.getId(),
                        author.getFirstName(),
                        author.getLastName(),
                        author.getDateOfBorn())
        );
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "Open author details");
        return "author/details";
    }
}
