package webLibraryView.library.viewmodel.author;

import webLibraryView.library.viewmodel.BaseViewModel;

public record AuthorDetailsViewModel(
        BaseViewModel base,
        AuthorViewModel author
) {
}
