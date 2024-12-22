package webLibraryView.library.viewmodel.reviews;

import webLibraryView.library.viewmodel.BaseViewModel;

public record ReviewDetailsViewModel(
        BaseViewModel base,
        ReviewViewModel review
) {
}
