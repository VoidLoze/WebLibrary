package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.FriendDTO;

import java.util.List;


public interface FriendService {
    FriendDTO find(int id);
    List<FriendDTO> findAllFriend();
    int addFriend(int readerId, int friendReaderId);

    List<FriendDTO> findAllFriendsByReader(int readerId);

    void deleteFriend(Integer id);

}
