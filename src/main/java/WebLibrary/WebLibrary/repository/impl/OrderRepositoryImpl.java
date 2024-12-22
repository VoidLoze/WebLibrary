package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.Order;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends BaseRepository<Order> implements OrderRepository {
    public OrderRepositoryImpl() {
        super(Order.class);
    }
}
