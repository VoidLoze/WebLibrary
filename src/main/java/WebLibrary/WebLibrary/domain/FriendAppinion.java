package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;
@Entity
@Table(name = "friendOpinion")
public class FriendAppinion extends BaseEntity{
    private String appinion;
    private Friend friend;
    private Books bookId;

    @Column(name = "appinion")
    public String getAppinion() {
        return appinion;
    }

    public void setAppinion(String appinion) {
        this.appinion = appinion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend", nullable = false)
    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book", nullable = false)
    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public FriendAppinion(){}

    public FriendAppinion(String appinion, Friend friend, Books bookId) {
        this.appinion = appinion;
        this.friend = friend;
        this.bookId = bookId;
    }
}
