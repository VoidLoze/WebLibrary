package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private int id;
    private String description;
    private int rate;
    private int readerId;
    private int bookId;
    public ReviewDTO(int id, String description, int rate, int readerId, int bookId) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.readerId = readerId;
        this.bookId = bookId;
    }

    public ReviewDTO(int id, String description, int rate, int bookId) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.bookId = bookId;
    }

    protected ReviewDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
