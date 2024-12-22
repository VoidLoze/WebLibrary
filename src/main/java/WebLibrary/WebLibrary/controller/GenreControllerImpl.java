package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.dto.GenreDTO;
import WebLibrary.WebLibrary.servises.GenreService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.genre.GenreController;
import webLibraryView.library.input.genre.GenreSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.genre.GenreDetailsViewModel;
import webLibraryView.library.viewmodel.genre.GenreListViewModel;
import webLibraryView.library.viewmodel.genre.GenreViewModel;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/genre")
public class GenreControllerImpl implements GenreController {
    private final GenreService genreService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public GenreControllerImpl(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }


    @Override
    @GetMapping()
    public String listGenres(@ModelAttribute("form") GenreSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new GenreSearchForm(searchTerm, page, size);

        List<GenreDTO> allGenres = genreService.findAll();

        List<GenreDTO> filteredGenre = allGenres.stream()
                .filter(r -> r.getNameOfGenre().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        int totalGenre = filteredGenre.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalGenre);
        List<GenreDTO> paginatedGenres = filteredGenre.subList(start, end);

        var genreViewModels = paginatedGenres.stream()
                .map(f -> new GenreViewModel(f.getId(),f.getNameOfGenre(),f.getDescription()))
                .toList();

        var viewModel = new GenreListViewModel(
                createBaseViewModel("Список жанров"),
                genreViewModels,
                (int) Math.ceil((double) totalGenre / size)
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        LOG.log(Level.INFO, "Open genre list");
        return "/genre/list";
    }

    @Override
    @GetMapping("/{id}")
    public String details(int id, Model model) {
        var genre = genreService.find(id);
        var viewModel = new GenreDetailsViewModel(
                createBaseViewModel("Детали жанра"),
                new GenreViewModel(genre.getId(),
                        genre.getNameOfGenre(),
                        genre.getDescription())
        );
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "Open genre details" + id);
        return "/genre/details";
    }
}
