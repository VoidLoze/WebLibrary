package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friend extends BaseEntity{
    private String firstName;
    private String lastName;
    private Reader reader;
    private Reader friendReader;
    public Friend(Reader reader, String lastName, String firstName, Reader friendReader) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.reader = reader;
        this.friendReader = friendReader;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_reader_id", nullable = false)
    public Reader getFriendReader() {
        return friendReader;
    }

    public void setFriendReader(Reader friendReader) {
        this.friendReader = friendReader;
    }

    @Column(name = "name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Friend(){}
//    public Friend(String firstName, String lastName,Reader reader) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.reader = reader;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader", nullable = false)
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
