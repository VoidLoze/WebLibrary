package WebLibrary.WebLibrary.dto;

import java.io.Serializable;

public class RateDTO implements Serializable {
    private int id;
    private int rate;
    private int book;

    public RateDTO(int id, int rate, int book) {
        this.id = id;
        this.rate = rate;
        this.book = book;
    }
    protected RateDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }
}
