package webLibraryView.library.controllers;

import webLibraryView.library.viewmodel.BaseViewModel;

public interface BaseController {
    BaseViewModel createBaseViewModel(String title);
}
