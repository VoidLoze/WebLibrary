package WebLibrary.WebLibrary.dto;

import WebLibrary.WebLibrary.domain.Friend;

import java.io.Serializable;

public class ReaderDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private Friend friendId;

    private String password;

    private String email;

    public ReaderDTO() {
    }

    public ReaderDTO(int id,String firstName, String lastName, int age, String phoneNumber, Friend friendId, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.friendId = friendId;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public ReaderDTO(int id, String firstName, String lastName, int age, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Friend getFriendId() {
        return friendId;
    }

    public void setFriendId(Friend friendId) {
        this.friendId = friendId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}