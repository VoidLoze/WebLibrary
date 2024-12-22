package webLibraryView.library.viewmodel.books;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record BookListViewModel(
        BaseViewModel base,
        List<BookViewModel> book,
        Integer totalPages
) {
}
