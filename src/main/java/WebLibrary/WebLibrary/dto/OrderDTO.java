package WebLibrary.WebLibrary.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDTO implements Serializable {
    private int id;
    private LocalDate orderDate;
    private int bookId;
    private int readerId;

    public OrderDTO(int id, LocalDate orderDate, String returnDate, int bookId, int readerId) {
        this.id = id;
        this.orderDate = orderDate;
        this.bookId = bookId;
        this.readerId = readerId;
    }
    protected OrderDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
}
