package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Order;
import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.OrderDTO;
import WebLibrary.WebLibrary.repository.OrderRepository;
import WebLibrary.WebLibrary.servises.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO find(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return modelMapper.map(order, OrderDTO.class);    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public int create(LocalDate orderDate, int readerId, int bookId) {
        Reader reader = new Reader();
        reader.setId(readerId);

        Books book = new Books();
        book.setId(bookId);

        Order order = new Order(orderDate, book, reader);
        Order createdOrder = orderRepository.save(order);
        return createdOrder.getId();
    }

    @Override
    @Cacheable(value = "orders", key = "'allOrders'")
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map((order) -> modelMapper.map(order, OrderDTO.class)).toList();
    }
}
