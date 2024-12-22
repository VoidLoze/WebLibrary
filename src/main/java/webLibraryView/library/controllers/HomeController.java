package webLibraryView.library.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface HomeController extends BaseController {
    @GetMapping()
    String index(Model model);
}
