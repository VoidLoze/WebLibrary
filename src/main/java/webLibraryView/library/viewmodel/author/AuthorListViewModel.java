package webLibraryView.library.viewmodel.author;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record AuthorListViewModel(
        BaseViewModel base,
        List<AuthorViewModel> author,
        Integer totalPages
) {
}
