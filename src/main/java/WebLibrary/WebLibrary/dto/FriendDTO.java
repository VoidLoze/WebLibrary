package WebLibrary.WebLibrary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class FriendDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
     // Читатель, который добавлен в друзья
    private String friendReader;

    public FriendDTO(int id, String firstName, String lastName, String friendReader) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friendReader = friendReader;
    }

    public String getFriendReader() {
        return friendReader;
    }

    public void setFriendReader(String friendReader) {
        this.friendReader = friendReader;
    }

    protected FriendDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotNull
    @Size(min = 2, max = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotNull
    @Size(min = 2, max = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
