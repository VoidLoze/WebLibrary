package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.dto.ReaderDTO;
import WebLibrary.WebLibrary.servises.ReaderService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.reader.ReaderController;
import webLibraryView.library.input.reader.CreateReaderForm;
import webLibraryView.library.input.reader.EditReaderForm;
import webLibraryView.library.input.reader.ReaderSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.reader.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reader")
public class ReaderControllerImpl implements ReaderController {
    private final ReaderService readerService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public ReaderControllerImpl(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/create")
    @Override
    public String createForm(Model model) {
        var viewModel = new ReaderCreateViewModel(
                createBaseViewModel("Создание читателя"));
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateReaderForm(" "," ",0, " "));
        return "reader/create";
    }

    @Override
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("form") CreateReaderForm form, BindingResult bindingResult,
                         Model model) {
            if (bindingResult.hasErrors()) {
                var viewModel = new ReaderCreateViewModel(
                        createBaseViewModel("Создание читателя")
                );
                model.addAttribute("model", viewModel);
                model.addAttribute("form", form);
                return "reader/create";
            }

            var id = readerService.create(form.firstName(),form.lastName(),form.age(),form.phoneNumber());
        LOG.log(Level.INFO, "create reader"+id);
            return "redirect:/reader/" + id;
    }

    @GetMapping()
    @Override
    public String listReader(ReaderSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new ReaderSearchForm(searchTerm, page, size);

        List<ReaderDTO> allReaders = readerService.findAll();

        List<ReaderDTO> filteredReader = allReaders.stream()
                .filter(r -> r.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        int totalReader = filteredReader.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalReader);
        List<ReaderDTO> paginatedReaders = filteredReader.subList(start, end);

        var reviewViewModels = paginatedReaders.stream()
                .map(f -> new ReaderViewModel(f.getId(),f.getFirstName(),f.getLastName(),f.getAge(),f.getPhoneNumber()))
                .toList();

        var viewModel = new ReaderListViewModel(
                createBaseViewModel("Список читателей"),
                reviewViewModels,
                (int) Math.ceil((double) totalReader / size)
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        LOG.log(Level.INFO, "Open reader list");
        return "reader/list";
    }

    @GetMapping("/{id}")
    @Override
    public String details(int id, Model model) {
        var reader = readerService.find(id);
        var viewModel = new ReaderDetailsViewModel(
                createBaseViewModel("Детали читателя"),
                new ReaderViewModel(reader.getId(),
                        reader.getFirstName(),
                        reader.getLastName(),
                        reader.getAge(),
                        reader.getPhoneNumber())
        );
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "Open reader details" + id);
        return "reader/details";    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }


    @Override
    @GetMapping("/{id}/edit")
    public String editForm(int id, Model model) {
        var reader = readerService.find(id);
        var viewModel = new ReaderEditViewModel(
                createBaseViewModel("Редактирование книги")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new EditReaderForm(reader.getId(),reader.getFirstName(), reader.getLastName(), reader.getAge(), reader.getPhoneNumber()));

        return "reader/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String editReader(@PathVariable int id,
                             @Valid @ModelAttribute("form") EditReaderForm form,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new ReaderEditViewModel(
                    createBaseViewModel("Редактирование аккаунта")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            LOG.log(Level.INFO, "edit reader"+id);
            return "reader/edit";
        }

        readerService.editReader(form.id(), form.name(), form.lastName(), form.age(),form.phoneNumber());
        LOG.log(Level.INFO, "edit reader"+id);
        return "redirect:/books/" + id;
    }
}
