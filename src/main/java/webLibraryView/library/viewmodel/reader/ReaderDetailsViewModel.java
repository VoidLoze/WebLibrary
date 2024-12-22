package webLibraryView.library.viewmodel.reader;

import webLibraryView.library.viewmodel.BaseViewModel;

public record ReaderDetailsViewModel(
        BaseViewModel base,
        ReaderViewModel reader
) {
}
