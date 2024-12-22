package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.dto.RateDTO;
import WebLibrary.WebLibrary.servises.RateService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.rate.RateController;
import webLibraryView.library.input.rate.CreateRateForm;
import webLibraryView.library.input.rate.RateSearchForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.rate.RateCreateViewModel;
import webLibraryView.library.viewmodel.rate.RateDetailsViewModel;
import webLibraryView.library.viewmodel.rate.RateListViewModel;
import webLibraryView.library.viewmodel.rate.RateViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rate")
public class RateControllerImpl implements RateController {
    private final RateService rateService;

    public RateControllerImpl(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var viewModel = new RateCreateViewModel(
                createBaseViewModel("Создание рейтинга"));
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateRateForm(0, 0));
        return "rate-create";
    }

    @Override
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("form") CreateRateForm form,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new RateCreateViewModel(
                    createBaseViewModel("Создание рейтинга")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "rate-create";
        }

        var id = rateService.create(form.rate(),form.book());
        return "redirect:/rate/" + id;
    }

    @Override
    @GetMapping()
    public String listRates(@ModelAttribute("form") RateSearchForm form, Model model) {
        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
        var page = form.page() != null ? form.page() : 1;
        var size = form.size() != null ? form.size() : 3;
        form = new RateSearchForm(searchTerm, page, size);

        List<RateDTO> allRates = rateService.findAll();

        List<RateDTO> filteredRate = allRates.stream()
                .filter(r -> r.getRate()>0)
                        .sorted(Comparator.comparingDouble(RateDTO::getRate).reversed())
                .collect(Collectors.toList());

        int totalRate = filteredRate.size();
        int start = (page - 1) * size;
        int end = Math.min(start + size, totalRate);
        List<RateDTO> paginatedRates = filteredRate.subList(start, end);

        var rateViewModels = paginatedRates.stream()
                .map(f -> new RateViewModel(f.getBook(), f.getId(), f.getRate()))
                .toList();

        var viewModel = new RateListViewModel(
                createBaseViewModel("Список рейтингов"),
                rateViewModels,
                (int) Math.ceil((double) totalRate / size)
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        return "rate-list";
    }

    @Override
    @GetMapping("/{id}")
    public String details(int id, Model model) {
        var rate = rateService.find(id);
        var viewModel = new RateDetailsViewModel(
                createBaseViewModel("Детали о рейтинге"),
                new RateViewModel(rate.getId(),
                        rate.getBook(),
                        rate.getRate())
        );
        model.addAttribute("model", viewModel);
        return "rate-details";
    }
}
