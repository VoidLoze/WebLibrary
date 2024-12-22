package webLibraryView.library.input;

public record UserRegistrationInputModel(
        String surname,
        String name,
        String phoneNumber,
        String login,
        String password
) {}
