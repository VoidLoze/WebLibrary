package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class FriendAppinionDTO implements Serializable {
    private int id;
    private String appinion;
    private int friend;
    private int bookId;

    public FriendAppinionDTO(int id, String appinion, int friend,int bookId) {
        this.id = id;
        this.appinion = appinion;
        this.friend = friend;
        this.bookId = bookId;
    }
    protected FriendAppinionDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppinion() {
        return appinion;
    }

    public void setAppinion(String appinion) {
        this.appinion = appinion;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
