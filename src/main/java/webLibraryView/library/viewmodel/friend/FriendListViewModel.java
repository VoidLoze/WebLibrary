package webLibraryView.library.viewmodel.friend;

import webLibraryView.library.viewmodel.BaseViewModel;

import java.util.List;

public record FriendListViewModel(
        BaseViewModel base,
        List<FriendViewModel> friendList,
        Integer totalPages,
        String title
) {
}
