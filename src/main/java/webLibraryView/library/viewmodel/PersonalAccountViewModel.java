package webLibraryView.library.viewmodel;

import java.util.Date;

public record PersonalAccountViewModel(
        BaseViewModel base,
        String name,
        String lastName,
        int age,
        String login,
        String password
) {}

class Reader {}

