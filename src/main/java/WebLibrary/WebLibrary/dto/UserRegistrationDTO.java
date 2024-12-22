package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class UserRegistrationDTO implements Serializable {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private int age;
    private String confirmPassword;

    public UserRegistrationDTO( String email, String password, String name, String surname, String phoneNumber, int age,String confirmPassword){
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.confirmPassword = confirmPassword;
    }

    public UserRegistrationDTO() {}

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}