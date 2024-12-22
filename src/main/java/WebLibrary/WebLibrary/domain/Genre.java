package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class Genre extends BaseEntity{
    private String nameOfGenre;
    private String description;

    @Column(name = "nameOfGenre", nullable = false)
    public String getNameOfGenre() {
        return nameOfGenre;
    }

    public void setNameOfGenre(String nameOfGenre) {
        this.nameOfGenre = nameOfGenre;
    }
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre (){}

    public Genre(String nameOfGenre, String description) {
        this.nameOfGenre = nameOfGenre;
        this.description = description;
    }
}
