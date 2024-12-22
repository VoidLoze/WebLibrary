package webLibraryView.library.viewmodel.genre;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record GenreListViewModel(
        BaseViewModel base,
        List<GenreViewModel> genre,
        Integer totalPages
) {
}
