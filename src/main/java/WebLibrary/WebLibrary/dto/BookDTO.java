package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {
    private int id;
    private String name;
    private int genreId;
    private String yearRealise;
    private int authorId;

    public BookDTO(int id, String name, int genreId, String yearRealise, int authorId) {
        this.id = id;
        this.name = name;
        this.genreId = genreId;
        this.yearRealise = yearRealise;
        this.authorId = authorId;
    }


    protected BookDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getYearRealise() {
        return yearRealise;
    }

    public void setYearRealise(String yearRealise) {
        this.yearRealise = yearRealise;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

}
