package webLibraryView.library.viewmodel.rate;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record RateListViewModel(
        BaseViewModel base,
        List<RateViewModel> rates,
        Integer totalPages
) {
}
