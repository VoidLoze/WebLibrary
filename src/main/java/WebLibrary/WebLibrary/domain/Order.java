package WebLibrary.WebLibrary.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    private LocalDate orderDate;
    private Books bookId;
    private Reader readerId;

    @Column(name = "orderDate", nullable = false)
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToOne
    @JoinColumn(name = "book", nullable = false)
    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }
    @ManyToOne
    @JoinColumn(name = "reader", nullable = false)
    public Reader getReaderId() {
        return readerId;
    }

    public void setReaderId(Reader readerId) {
        this.readerId = readerId;
    }

    public Order(LocalDate orderDate, Books bookId, Reader readerId) {
        this.orderDate = orderDate;
        this.bookId = bookId;
        this.readerId = readerId;
    }

    protected Order(){}
}
