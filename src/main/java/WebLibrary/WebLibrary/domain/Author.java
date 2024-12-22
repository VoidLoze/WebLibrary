package WebLibrary.WebLibrary.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;




@Entity
@Table(name = "author")
public class Author extends BaseEntity{
    private String firstName;

    private String lastName;

    private String dateOfBorn;


    public Author(String firstName, String lastName, String dateOfBorn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBorn = dateOfBorn;
    }

    public Author() {}

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
    @Column(name = "dateOfBorn", nullable = false)
    public String getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(String dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }
}
