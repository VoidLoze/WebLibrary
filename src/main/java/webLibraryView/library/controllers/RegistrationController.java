package webLibraryView.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.UserRegistrationInputModel;
import webLibraryView.library.viewmodel.RegistrationViewModel;

@RequestMapping("/register")
public interface RegistrationController {
    @GetMapping
    RegistrationViewModel getRegistrationPage();

    @PostMapping
    String register(UserRegistrationInputModel registrationInput);
}
