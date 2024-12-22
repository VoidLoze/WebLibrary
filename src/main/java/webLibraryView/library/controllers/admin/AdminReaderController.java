package webLibraryView.library.controllers.admin;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.input.reader.CreateReaderForm;
import webLibraryView.library.input.reader.EditReaderForm;

@RequestMapping("/admin/reader")
public interface AdminReaderController extends AdminBaseController{
    @PostMapping("/new")
    void createBook(CreateReaderForm inputModel);

    @PatchMapping("/update")
    void updateBook(EditReaderForm inputModel);

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
