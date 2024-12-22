package webLibraryView.library.viewmodel.order;

import webLibraryView.library.viewmodel.BaseViewModel;

public record OrderDetailsViewModel(
        BaseViewModel base,
        OrderViewModel order
) {
}
