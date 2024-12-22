package webLibraryView.library.viewmodel.books;

import webLibraryView.library.viewmodel.BaseViewModel;

public record BookDetailsViewModel(
        BaseViewModel base,
        BookViewModel book
) {
}
