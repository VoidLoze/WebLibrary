package WebLibrary.WebLibrary.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.controllers.HomeController;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.home.HomeViewModel;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    @Override
    @GetMapping("/")
    public String index(Model model) {
        var viewModel = new HomeViewModel(createBaseViewModel("Главная страница"));
        LOG.log(Level.INFO, "Open home page");
        model.addAttribute("model", viewModel);


        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
