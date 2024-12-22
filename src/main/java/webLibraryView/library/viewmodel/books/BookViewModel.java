package webLibraryView.library.viewmodel.books;

public record BookViewModel(
        int id,
        String name,
        Integer authorId,
        Integer genreId,
        String yearRealise

) {
}
