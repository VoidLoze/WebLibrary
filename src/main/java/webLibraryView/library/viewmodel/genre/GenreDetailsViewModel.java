package webLibraryView.library.viewmodel.genre;

import webLibraryView.library.viewmodel.BaseViewModel;

public record GenreDetailsViewModel(
        BaseViewModel base,
        GenreViewModel genre
) {
}
