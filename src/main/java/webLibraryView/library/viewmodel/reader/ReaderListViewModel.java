package webLibraryView.library.viewmodel.reader;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record ReaderListViewModel(
        BaseViewModel base,
        List<ReaderViewModel> readers,
        Integer totalPages
) {
}
