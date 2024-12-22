package webLibraryView.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.SignInInputModel;


@RequestMapping("/sign-in")
public interface SignInController {
    @GetMapping
    String getSignInPage();

    @PostMapping
    String signIn(SignInInputModel inputModel);

}
