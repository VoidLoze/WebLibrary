package webLibraryView.library.viewmodel.order;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record OrderListViewModel(
        BaseViewModel base,
        List<OrderViewModel> orders,
        Integer totalPages
) {
}
