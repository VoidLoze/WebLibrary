package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class GenreDTO implements Serializable {
    private int id;
    private String nameOfGenre;
    private String description;

    public GenreDTO(int id, String nameOfGenre, String description) {
        this.id = id;
        this.nameOfGenre = nameOfGenre;
        this.description = description;
    }
    protected GenreDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfGenre() {
        return nameOfGenre;
    }

    public void setNameOfGenre(String nameOfGenre) {
        this.nameOfGenre = nameOfGenre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
