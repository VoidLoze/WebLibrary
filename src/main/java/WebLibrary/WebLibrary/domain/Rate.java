package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;
@Entity
@Table(name = "rate")
public class Rate extends BaseEntity{
    private int rate;
    private Books book;

    @Column(name = "rate")
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) { // Изменено на double
        this.rate = rate;
    }


    @ManyToOne
    @JoinColumn(name = "book")
    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
    protected Rate(){}

    public Rate(int rate, Books book) {
        this.rate = rate;
        this.book = book;
    }
}
