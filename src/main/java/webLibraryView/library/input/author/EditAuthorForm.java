package webLibraryView.library.input.author;

public record EditAuthorForm(
        int id,
        String firstName,
        String lastName,
        String dateOfBorn
) {
}
