package WebLibrary.WebLibrary.servises;

import WebLibrary.WebLibrary.dto.OrderDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderDTO find(int id);
    int create(LocalDate orderDate, int readerId, int bookId);
    List<OrderDTO> findAll();
}
