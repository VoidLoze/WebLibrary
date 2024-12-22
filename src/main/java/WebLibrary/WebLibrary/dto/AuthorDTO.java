package WebLibrary.WebLibrary.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String DateOfBorn;

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
    @NotNull
    @Size(min = 2, max = 50)
    public String getDateOfBorn() {
        return DateOfBorn;
    }

    public void setDateOfBorn(String dateOfBorn) {
        DateOfBorn = dateOfBorn;
    }

    public AuthorDTO(int id, String firstName, String lastName, String dateOfBorn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        DateOfBorn = dateOfBorn;
    }

    protected AuthorDTO(){}
}
