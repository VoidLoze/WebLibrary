package webLibraryView.library.viewmodel.reviews;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record ReviewTopListViewModel(
        BaseViewModel base,
        List<TopBooksViewModel> reviews,
        Integer totalPages
) {
}
