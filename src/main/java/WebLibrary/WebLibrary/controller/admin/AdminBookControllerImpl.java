package WebLibrary.WebLibrary.controller.admin;

import WebLibrary.WebLibrary.dto.AuthorDTO;
import WebLibrary.WebLibrary.dto.GenreDTO;
import WebLibrary.WebLibrary.servises.AuthorService;
import WebLibrary.WebLibrary.servises.BookService;
import WebLibrary.WebLibrary.servises.GenreService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.admin.AdminBookController;
import webLibraryView.library.input.Book.CreateBookForm;
import webLibraryView.library.input.Book.EditBookForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.books.BookCreateViewModel;
import webLibraryView.library.viewmodel.books.BookEditViewModel;

import java.util.List;

@Controller
@RequestMapping("/admin/book")
public class AdminBookControllerImpl implements AdminBookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public AdminBookControllerImpl(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

    @GetMapping("/create")
    @Override
    public String createForm(Model model) {
        var viewModel = new BookCreateViewModel(
                createBaseViewModel("Создание книги"));
        List<AuthorDTO> authors = authorService.findAll();
        List<GenreDTO> genres = genreService.findAll();

        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateBookForm("", 0, 0,""));
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "book/create";
    }

    @PostMapping("/create")
    @Override
    public String createBook(@Valid @ModelAttribute("form") CreateBookForm form, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            List<AuthorDTO> authors = authorService.findAll();
            List<GenreDTO> genres = genreService.findAll();
            var viewModel = new BookCreateViewModel(
                    createBaseViewModel("Создание книги")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            model.addAttribute("authors", authors);
            model.addAttribute("genres", genres);
            return "book/create";
        }

        var id = bookService.create(form.name(),form.authorId(),form.genreId(), form.yearRealise());
        LOG.log(Level.INFO, "Admin create book"+id);
        return "redirect:/book/" + id;
    }
    @Override
    @GetMapping("/{id}/edit")
    public String editForm(int id, Model model) {
        var book = bookService.find(id);
        var viewModel = new BookEditViewModel(
                createBaseViewModel("Редактирование книги")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new EditBookForm(book.getId(),book.getName(), book.getAuthorId(), book.getGenreId(), book.getYearRealise()));
        return "book/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String editBook(@PathVariable int id, @Valid @ModelAttribute("form") EditBookForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new BookEditViewModel(
                    createBaseViewModel("Редактирование книги")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "book/edit";
        }

        bookService.editBook(form.id(), form.name(), form.authorId(), form.genreId(),form.yearRealise());
        return "redirect:/books/" + id;
    }
}
