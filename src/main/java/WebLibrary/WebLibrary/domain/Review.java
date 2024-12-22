package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;
@Entity
@Table(name = "review")
public class Review extends BaseEntity{
    private String description;
    private int rate;
    private Reader readerId;
    private Books book;

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "rate")
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    @ManyToOne
    @JoinColumn(name = "reader", nullable = false)
    public Reader getReaderId() {
        return readerId;
    }

    public void setReaderId(Reader readerId) {
        this.readerId = readerId;
    }

    public Review(String description, int rate, Reader readerId, Books book) {
        this.description = description;
        this.rate = rate;
        this.readerId = readerId;
        this.book = book;
    }
    public Review(){}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review")
    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Review(String description, int rate, Books book) {
        this.description = description;
        this.rate = rate;
        this.book = book;
    }


}
