package webLibraryView.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.input.UpdatePersonalAccountInputModel;


@RequestMapping("/personal-account")
public interface PersonalAccountController {
    @GetMapping
    String getPersonalAccountPage();

    @PatchMapping("/update")
    String updatePersonalAccount(UpdatePersonalAccountInputModel inputModel);

}

