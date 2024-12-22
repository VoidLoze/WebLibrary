package WebLibrary.WebLibrary.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books extends BaseEntity{
    private String name;
    private Genre genreId;
    private String yearRealise;
    private Author authorId;

    private double averageRating;

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Books(String name) {
        this.name = name;
    }

    public Books(String name, Genre genreId, String yearRealise, Author authorId){
        this.name = name;
        this.genreId = genreId;
        this.yearRealise = yearRealise;
        this.authorId = authorId;
    }

    public Books(){}

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre", nullable = false)
    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }
    @Column(name = "yearRealise", nullable = false)
    public String getYearRealise() {
        return yearRealise;
    }

    public void setYearRealise(String yearRealise) {
        this.yearRealise = yearRealise;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    public Author getAuthorId(){
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }
}
