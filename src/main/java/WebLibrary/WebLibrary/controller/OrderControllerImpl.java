package WebLibrary.WebLibrary.controller;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.BookDTO;
import WebLibrary.WebLibrary.dto.ReaderDTO;
import WebLibrary.WebLibrary.servises.BookService;
import WebLibrary.WebLibrary.servises.OrderService;
import WebLibrary.WebLibrary.servises.ReaderService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webLibraryView.library.controllers.order.OrderController;
import webLibraryView.library.input.order.CreateOrderForm;
import webLibraryView.library.viewmodel.BaseViewModel;
import webLibraryView.library.viewmodel.order.OrderCreateViewModel;
import webLibraryView.library.viewmodel.order.OrderDetailsViewModel;
import webLibraryView.library.viewmodel.order.OrderViewModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;
    private final ReaderService readerService;
    private final BookService bookService;
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    public OrderControllerImpl(OrderService orderService, ReaderService readerService, BookService bookService) {
        this.orderService = orderService;
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var viewModel = new OrderCreateViewModel(createBaseViewModel("Создание заказа"));
        List<ReaderDTO> readers = readerService.findAll();
        List<BookDTO> books = bookService.findAll();

        Reader currentUser = getCurrentUser().orElse(null); // Или  getCurrentUser().orElseGet(() -> new Reader()); - если нужно значение по умолчанию


        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CreateOrderForm(LocalDate.now(), 0, 0));
        model.addAttribute("books", books);
        model.addAttribute("readers", readers);
        model.addAttribute("currentUser", currentUser);

        //  КЛЮЧЕВОЕ ИЗМЕНЕНИЕ:  Добавляем ID текущего пользователя в модель
        model.addAttribute("currentReaderId", currentUser != null ? currentUser.getId() : null);

        return "order/create";
    }
    private Optional<Reader> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Предположим, что у вас есть метод для получения ReaderDTO по имени пользователя
        return readerService.findByEmail(authentication.getName());
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("form") CreateOrderForm form,
                         BindingResult bindingResult,
                         Model model,
                         @RequestParam(required = false, value = "currentReaderId") Integer currentReaderId) {


        if (bindingResult.hasErrors()) {
            // Improved Error Handling: Avoid unnecessary database calls.
            Reader currentUser = getCurrentUser().orElse(null);
            List<BookDTO> books = bookService.findAll(); //Only books are needed for display
            var viewModel = new OrderCreateViewModel(createBaseViewModel("Создание заказа"));
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            model.addAttribute("books", books);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("currentReaderId", currentReaderId); //Pass it back for display
            return "order/create";
        }

        // Use currentReaderId instead of form.readerId()
        var id = orderService.create(form.orderDate(), currentReaderId, form.bookId());
        LOG.log(Level.INFO, "make order" + id);
        return "redirect:/order/" + id;
    }


//    @Override
//    @GetMapping()
//    public String listOrders(@ModelAttribute("form") OrderSearchForm form, Model model) {
//        var searchTerm = form.searchTerm() != null ? form.searchTerm() : "";
//        var page = form.page() != null ? form.page() : 1;
//        var size = form.size() != null ? form.size() : 3;
//        form = new OrderSearchForm(searchTerm, page, size);
//
//        List<OrderDTO> allOrders = orderService.findAll();
//
//        List<OrderDTO> filteredOrder = allOrders.stream()
//                .filter(r -> r.getOrderDate().datesUntil().contains(searchTerm.toLowerCase()))
//                .collect(Collectors.toList());
//
//        int totalOrder = filteredOrder.size();
//        int start = (page - 1) * size;
//        int end = Math.min(start + size, totalOrder);
//        List<OrderDTO> paginatedReaders = filteredOrder.subList(start, end);
//
//        var orderViewModels = paginatedReaders.stream()
//                .map(f -> new OrderViewModel(f.getId(),f.getOrderDate(),f.getReaderId(),f.getBookId()))
//                .toList();
//
//        var viewModel = new OrderListViewModel(
//                createBaseViewModel("Список заказов"),
//                orderViewModels,
//                (int) Math.ceil((double) totalOrder / size)
//        );
//
//        model.addAttribute("model", viewModel);
//        model.addAttribute("form", form);
//        return "order/list";
//    }

    @Override
    @GetMapping("/{id}")
    public String details(int id, Model model) {
        var order = orderService.find(id);
        var viewModel = new OrderDetailsViewModel(
                createBaseViewModel("Детали заказа"),
                new OrderViewModel(order.getId(),
                        order.getOrderDate(),
                        order.getReaderId(),
                        order.getBookId())
        );
        model.addAttribute("model", viewModel);
        LOG.log(Level.INFO, "order"+id+"details");
        return "order/details";
    }
}
