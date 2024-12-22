package webLibraryView.library.viewmodel.reviews;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record ReviewListViewModel(
        BaseViewModel base,
        List<ReviewViewModel> review,
        Integer totalPages
) {
}
