package WebLibrary.WebLibrary.repository;

import WebLibrary.WebLibrary.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {
    Optional<Order> findById(int orderId);
    Order save(Order order);
    List<Order> findAll();
}
