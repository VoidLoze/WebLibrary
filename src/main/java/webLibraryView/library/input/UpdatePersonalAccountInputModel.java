package webLibraryView.library.input;

public record UpdatePersonalAccountInputModel(
        String name,
        String surname,
        String phoneNumber,
        String login,
        String password
) {}
