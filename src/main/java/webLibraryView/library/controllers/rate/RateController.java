package webLibraryView.library.controllers.rate;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.BaseController;
import webLibraryView.library.input.rate.CreateRateForm;
import webLibraryView.library.input.rate.RateSearchForm;

@RequestMapping("/rates")
public interface RateController extends BaseController {
    @GetMapping("/create")
    String createForm(Model model);

    @PostMapping("/create")
    String create(
            @Valid @ModelAttribute("form") CreateRateForm form,
            BindingResult bindingResult,
            Model model);

    @GetMapping
    String listRates(
            @ModelAttribute("form") RateSearchForm form,
            Model model
    );

    @GetMapping("/{id}")
    String details(
            @PathVariable int id,
            Model model
    );
}
