package webLibraryView.library.controllers.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import webLibraryView.library.viewmodel.BaseViewModel;


@RequestMapping
public interface AdminBaseController {

    BaseViewModel createBaseViewModel(String title);
}