package webLibraryView.library.controllers.reader;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.reader.CreateReaderForm;
import webLibraryView.library.input.reader.EditReaderForm;
import webLibraryView.library.input.reader.ReaderSearchForm;

@RequestMapping("/reader")
public interface ReaderController extends BaseController {
    @GetMapping("/create")
    String createForm(Model model);

    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") CreateReaderForm form,
            BindingResult bindingResult,
            Model model);

    @GetMapping
    String listReader(ReaderSearchForm form, Model model);

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );

    @GetMapping("/{id}/edit")
    String editForm(
            @PathVariable int id,
            Model model
    );
    @PostMapping("/{id}/edit")
    String editReader(
            @PathVariable int id,
            @Valid @ModelAttribute("form") EditReaderForm form,
            BindingResult bindingResult,
            Model model
    );

}
